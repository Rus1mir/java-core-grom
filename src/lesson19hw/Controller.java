package lesson19hw;

public class Controller {
    public void put(Storage storage, File file) throws Exception {
        if (file == null && storage == null)
            throw new IllegalArgumentException("Arguments are null");

        File[] files = storage.getFiles();

        boolean bool = true;
        for (String format : storage.getFormatsSupported()) {
            bool = bool && !file.getFormat().equals(format);
        }
        if (bool)
            throw new Exception ("Filed to put file id " + file.getId() +
                    " cause no format support for storage id " + storage.getId());

        long space = storage.getStorageSize();
        for (File f : files) {
            if (f != null) {
                space = space - f.getSize();
                if (f.getId() == file.getId())
                    throw new RuntimeException("Filed to put file id " + file.getId() +
                            " cause file with same id is already exist in storage id " + storage.getId());
            }
        }
        if (space < file.getSize())
            throw new Exception("Filed to put file id " + file.getId() +
                    " cause no free space in storage id " + storage.getId());

        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                files[i] = file;
                storage.setFiles(files);
                return;
            }
        }
        throw new Exception("Filed to put file id " + file.getId() +
                " cause no free cells in storage id " + storage.getId());
    }

    public void delete(Storage storage, File file) {

        File[] files = storage.getFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i] != null &&
                    files[i].getId() == file.getId()) {
                files[i] = null;
                storage.setFiles(files);
                return;
            }
        }
        throw new RuntimeException("Delete file id " + file.getId() +
                " filed, cause file is not found in " + storage.getId());
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {

        File[] filesFrom = storageFrom.getFiles();

        //Check for empty storage from
        if (filesFrom.length == freeCellsCount(storageFrom))
            throw new RuntimeException("Transfer from storage id " + storageFrom.getId() +
                    " filed cause no files to transfer in source storage");

        //Check for free cells in storage to
        if (filesFrom.length - freeCellsCount(storageFrom) > freeCellsCount(storageTo))
            throw new RuntimeException("Transfer from storage id " + storageFrom.getId() +
                    " filed cause number of free cells in destination storage id " + storageTo.getId() + " is not enough");

        //Check for free space in storage to
        if (storageFrom.getStorageSize() - freeSpaceCalc(storageFrom) > freeSpaceCalc(storageTo))
            throw new RuntimeException("Transfer from storage id " + storageFrom.getId() +
                    " failed cause free space in destination storage id " + storageTo.getId() + " is not enough");

        //Check for file formats from storage from supported for storage to
        for (File file : filesFrom) {
            if ((file != null) && (!isFileFormatSupported(file, storageTo)))
                throw new RuntimeException("Transfer from storage id " + storageFrom.getId() +
                        " filed cause file, id " + file.getId() +
                        " has unsupported format for destination storage, id " + storageTo.getId());
        }

        //Check for file id already exist in storage to
        for (File file : filesFrom) {

            if ((file != null) &&
                    (getFileById(file.getId(), storageTo) != null))
                throw new RuntimeException("Transfer from storage id " + storageFrom.getId() +
                        " filed cause file, id " + file.getId() +
                        " is already exist in destination storage, id " + storageTo.getId());
        }

        //Insert files to destination storage
        File[] filesTo = storageTo.getFiles();

        for (int i = 0, n = 0; i < filesFrom.length; i++) {
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

    public void transferFile(Storage storageFrom, Storage storageTo, long id) {

        File file = getFileById(id, storageFrom);
        if (file == null) {
            throw new RuntimeException("Transfer filed cause file with id " + id +
                    " is no find in source storage, id " + storageFrom.getId());
        }

        try {
            delete(storageFrom, file);
            put(storageTo, file);
        } catch (Exception e) {
            throw new RuntimeException("Failed to transfer file id " + id + " cause " + e.getMessage());
        }
    }

    private int freeCellsCount(Storage storage) {
        int counter = 0;
        for (File file : storage.getFiles()) {
            if (file == null)
                counter++;
        }
        return counter;
    }

    private long freeSpaceCalc(Storage storage) {
        long space = storage.getStorageSize();
        for (File file : storage.getFiles()) {
            if (file != null)
                space -= file.getSize();
        }
        return space;
    }

    private File getFileById(long id, Storage storage) {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id)
                return file;
        }
        return null;
    }

    private boolean isFileNameValid(File file) {
        return file.getName().length() <= 10;
    }

    private boolean isFileFormatSupported(File file, Storage storage) {
        for (String fileFormat : storage.getFormatsSupported()) {
            if (fileFormat.equals(file.getFormat()))
                return true;
        }
        return false;
    }
}
