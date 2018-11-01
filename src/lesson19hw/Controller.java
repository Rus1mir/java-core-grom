package lesson19hw;

public class Controller {

    public void put(Storage storage, File file) throws Exception {

        String message = "Failed to put file " + file.getId() +
                " to storage " + storage.getId() + " cause ";

        if (!isFileFormatSupported(file, storage))
            throw new Exception(message + "file format is unsupported");

        File[] files = storage.getFiles();

        long space = storage.getStorageSize();
        int cells = 0;
        int index = 0;

        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                index = i;
                cells++;
            } else {
                if (files[i].getId() == file.getId())
                    throw new Exception(message + "duplicate from id was found");
                space -= files[i].getSize();
            }
        }

        if (cells == 0)
            throw new Exception(message + "no free cells");

        if (file.getSize() > space)
            throw new Exception(message + "no free space");

        files[index] = file;
    }

    public void delete(Storage storage, File file) throws Exception {

        File[] files = storage.getFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i] != null &&
                    files[i].getId() == file.getId()) {
                files[i] = null;
                return;
            }
        }

        throw new Exception("Filed to delete file id " + file.getId() +
                " from storage id " + storage.getId() +
                " cause file is not found");
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        String message = "Filed to transfer all files from storage id " +
                storageFrom.getId() + " to storage " + storageTo.getId() + " cause ";

        File[] filesFrom = storageFrom.getFiles();
        File[] filesTo = storageTo.getFiles();

        long space = storageTo.getStorageSize();
        int cells = 0;
        int nullCount = 0;

        for (File file : filesTo) {
            if (file == null) {
                cells++;
            } else {
                space -= file.getSize();
            }
        }

        for (File fileFrom : filesFrom) {

            if (fileFrom != null) {
                cells--;
                space -= fileFrom.getSize();

                for (File fileTo : filesTo) {
                    if (fileTo != null) {
                        if (fileFrom.getId() == fileTo.getId())
                            throw new Exception(message + "duplicate was found");
                    }
                }
                if (!isFileFormatSupported(fileFrom, storageTo))
                    throw new Exception(message + "file format is unsupported");

            } else {
                nullCount++;
            }
        }

        if (nullCount == filesFrom.length)
            throw new Exception(message + "no files to transfer");

        if (cells < 0)
            throw new Exception(message + "no free cells");

        if (space < 0)
            throw new Exception(message + "no free space");


        for (int i = 0, n = 0; i < filesFrom.length; i++) {
            if (filesFrom[i] == null)
                continue;
            for (int j = n; j < filesTo.length; j++) {
                if (filesTo[j] == null) {
                    filesTo[j] = filesFrom[i];
                    filesFrom[i] = null;
                    n = j + 1;
                    break;
                }
            }
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        File file = getFileById(id, storageFrom);
        if (file == null)
            throw new Exception("Transfer file id " + id +
                    " is filed file not found in storage id " + storageFrom.getId());

        try {
            put(storageTo, file);
        } catch (Exception e) {
            throw new Exception("Transfer file id " + id +
                    " is filed " + " cause " + e.getMessage());
        }

        try {
            delete(storageFrom, file);
        } catch (Exception e) {
            throw new Exception("Transfer file id " + id +
                    " is filed " + " cause " + e.getMessage());
        }
    }

    private File getFileById(long id, Storage storage) {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id)
                return file;
        }
        return null;
    }

    private boolean isFileFormatSupported(File file, Storage storage) {
        for (String fileFormat : storage.getFormatsSupported()) {
            if (fileFormat.equals(file.getFormat()))
                return true;
        }
        return false;
    }
}
