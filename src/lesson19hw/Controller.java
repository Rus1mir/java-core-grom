package lesson19hw;

public class Controller {

    public void put(Storage storage, File file) throws Exception {

        String message = validatePut(storage, file);
        if (message != null)
            throw new Exception("Failed put file id " + file.getId() +
                    " to storage " + storage.getId() +
                    " cause " + message);

        File[] files = storage.getFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                files[i] = file;
                break;
            }
        }
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

        File[] filesFrom = storageFrom.getFiles();
        File[] filesTo = storageTo.getFiles();

        String message = validatePut(storageTo, filesFrom);
        if (message != null)
            throw new Exception("Filed to transfer all files from storage id " +
                    storageFrom.getId() + " to storage " +
                    storageTo.getId() + " cause " + message);

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
            throw new Exception("Filed to transfer file id " + id +
                    " cause file not found in storage id " + storageFrom.getId());

        String message = validatePut(storageTo, file);
        if (message != null)
            throw new Exception("Filed to transfer file id " + id +
                    " cause " + message);

        File[] filesTo = storageTo.getFiles();
        File[] filesFrom = storageFrom.getFiles();

        for (int i = 0; i < filesTo.length; i++) {
            if (filesTo[i] == null) {
                filesTo[i] = file;
                break;
            }
        }

        for (int i = 0; i < filesFrom.length; i++) {
            if (filesFrom[i] != null && filesFrom[i].getId() == id) {
                filesFrom[i] = null;
                break;
            }
        }
    }

    private String validatePut(Storage storage, File file) {
        //No free space, No free cells, Wrong format, Duplicate

        if (!isFileFormatSupported(file, storage))
            return "unsupported format for file id " + file.getId();

        long space = storage.getStorageSize();
        int cells = 0;

        for (File f : storage.getFiles()) {
            if (f != null) {
                if (f.getId() == file.getId())
                    return "duplicate file id " + file.getId() + " was found";
                space -= f.getSize();
            } else {
                cells++;
            }
        }

        if (cells == 0 || space < file.getSize())
            return "free space for file " + file.getId() + " is not enough";
        return null;
    }

    private String validatePut(Storage storage, File[] files) {
        //No free space, No free cells, Wrong format, Duplicate

        long spaceNeed = 0;
        int cellsNeed = 0;

        for (File file : files) {
            if (file != null) {
                if (!isFileFormatSupported(file, storage))
                    return ("unsupported format for file id " + file.getId());
                spaceNeed += file.getSize();
                cellsNeed++;
            }
        }

        long space = storage.getStorageSize();
        int cells = 0;

        for (File file : storage.getFiles()) {
            if (file != null) {
                for (File file1 : files) {
                    if (file1 != null && file1.getId() == file.getId())
                        return "duplicate file id " + file.getId() + " was found";
                    space -= file.getSize();
                }
            } else {
                cells++;
            }
        }

        if (spaceNeed > space || cellsNeed > cells)
            return "free space in storage " + storage.getId() + " is not enough";

        return null;
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
