package lesson19hw;

public class Controller {

    public void put(Storage storage, File file) throws Exception {

        File[] files = {file};
        validatePut(storage, files);

        files = storage.getFiles();

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

//Замечание бросать эксепшны прямо из подметодов без проброса стрингов
        validatePut(storageTo, filesFrom);

//Замечание цикл неверный траблы с индексами, совет юзать фор ич и переменные-индексы
        int i = -1;
        for (File fileFrom : filesFrom) {
            i++;
            int j=-1;
            if (fileFrom == null) {
                continue;
            }
            for (File fileTo : filesTo) {
                j++;
                if (fileTo == null) {
                    filesTo[j] = fileFrom;
                    filesFrom[i] = null;
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

        File[] files = {file};
        validatePut(storageTo, files);

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

    private void validatePut(Storage storage, File[] files) throws Exception {
        //No free space, No free cells, Wrong format, Duplicate

        long spaceNeed = 0;
        int cellsNeed = 0;

        for (File file : files) {
            if (file != null) {
                if (!isFileFormatSupported(file, storage))
                    throw new Exception("File format is not supported file id " + file.getId() +
                            " for storage id " + storage.getId());
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
                        throw new Exception("Duplicate was found file id " + file.getId() +
                                " in storage id " + storage.getId());
                    space -= file.getSize();
                }
            } else {
                cells++;
            }
        }

        if (spaceNeed > space || cellsNeed > cells)
            throw new Exception("Free space is not enough in storage id " + storage.getId());
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
