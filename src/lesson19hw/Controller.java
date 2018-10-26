package lesson19hw;

public class Controller {
    public void put(Storage storage, File file) throws Exception {

        String message = checkPut(storage, file);

        if (message != null)
            throw new Exception("Filed put file id " + file.getId() +
                    " in storage id " + storage.getId() + message);

        File[] files = storage.getFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                files[i] = file;
                storage.setFiles(files);
                return;
            }
        }
    }

    public void delete(Storage storage, File file) throws Exception {

        String message = checkDelete(storage, file);

        if (message != null)
            throw new Exception("Filed delete file id " + file.getId() +
                    " from storage id " + storage.getId() + message);

        File[] files = storage.getFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i] != null &&
                    files[i].getId() == file.getId()) {
                files[i] = null;
                storage.setFiles(files);
                return;
            }
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        String message = checkTransferAll(storageFrom, storageTo);

        if (message != null)
            throw new Exception("Transfer all files from storage id " + storageFrom.getId() +
                    " to storage id " + storageTo + " is filed " + message);

        File[] filesFrom = storageFrom.getFiles();
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

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        File file = getFileById(id, storageFrom);
        if (file == null)
            throw new Exception("Transfer file id " + id + " is filed file not found in storage id " +
                    storageFrom.getId());

        String message = checkPut(storageTo, file);

        if (message != null)
            throw new Exception("Transfer file id " + id + " from storage id " + storageFrom.getId()
                    + " to storage id " + storageTo.getId() + " is filed file not found in storage id " + message);

        File[] filesFrom = storageFrom.getFiles();
        File[] filesTo = storageTo.getFiles();

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

    private String checkTransferAll(Storage storageFrom, Storage storageTo) {

        File[] filesFrom = storageFrom.getFiles();
        //Check for empty storage from
        if (filesFrom.length == freeCellsCount(storageFrom))
            return "Storage id " + storageFrom.getId() + " is empty";

        //Check for free cells in storage to
        if (filesFrom.length - freeCellsCount(storageFrom) > freeCellsCount(storageTo))
            return "Number of free cells in storage id " + storageTo.getId() + " is not enough";

        //Check for free space in storage to
        if (storageFrom.getStorageSize() - freeSpaceCalc(storageFrom) > freeSpaceCalc(storageTo))
            return "Free space in storage id " + storageTo.getId() + " is not enough";

        //Check for file formats from storage from supported for storage to
        for (File file : filesFrom) {
            if ((file != null) && (!isFileFormatSupported(file, storageTo)))
                return "Unsupported file format " + file.getFormat() + " for storage id " + storageTo.getId();
        }

        //Check for file id already exist in storage to
        for (File file : filesFrom) {
            if ((file != null) &&
                    (getFileById(file.getId(), storageTo) != null))
                return "File with id " + file.getId() + " is already exist in storage id " + storageTo.getId();
        }
        return null;
    }

    private String checkPut(Storage storage, File file) {

        if (freeCellsCount(storage) == 0)
            return "No free cells in storage id " + storage.getId();

        if (freeSpaceCalc(storage) < file.getSize())
            return "No free space in storage id " + storage.getId();

        if (getFileById(file.getId(), storage) != null)
            return "File with id " + file.getId() + " is already exist in storage id " + storage.getId();

        if (!isFileNameValid(file))
            return "File with id " + file.getId() + " name is too long";

        if (!isFileFormatSupported(file, storage))
            return "File with id " + file.getId() + " has unsupported format for storage id " + storage.getId();

        return null;
    }

    private String checkDelete(Storage storage, File file) {
        if (getFileById(file.getId(), storage) == null)
            return "File id " + file.getId() + " no found in storage id " + storage.getId();
        return null;
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
