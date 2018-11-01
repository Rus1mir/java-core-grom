package lesson19hw;

//https://github.com/Rus1mir/java-core-grom/tree/master/src/lesson19hw
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws Exception {
        //testCreateFileBigName();
        //testPutNormally();
        //testPutWithSameId();
        //testPutWithOversize();
        //testPutWithNoCells();
        //testPutFileWrongFormat();

        //testDeleteNormally();
        //testDeleteFromEmpty();
        //testDeleteWithWrongId();

        //testTransferAllNormally();
        //testTransferAllNoCells();
        //testTransferAllNoSpace();
        //testTransferAllEmptySource();
        //testTransferAllWrongFormat();

        //testTransferByIdNormally();
        //testTransferByIdEmptyFrom();
        //testTransferByIdAlreadyExist();
        //testTransferByIdWrongFormat();
        //testTransferByIdNoFound();
    }

    static void testCreateFileBigName() throws Exception {
        File file = new File(12, "pretty very big file", "pic", 20);
    }

    static void testPutNormally() throws Exception {
        System.out.println("Test normal put");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        File file = new File(12, "pretty", "pic", 20);
        Controller controller = new Controller();
        controller.put(storage, file);
        System.out.println(Arrays.deepToString(storage.getFiles()));
    }

    static void testPutWithSameId() throws Exception {
        System.out.println("Test put with same id");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        File file = new File(12, "pretty1", "pic", 20);
        Controller controller = new Controller();

        controller.put(storage, file);
        System.out.println(Arrays.deepToString(storage.getFiles()));
        controller.put(storage, file);
        System.out.println(Arrays.deepToString(storage.getFiles()));
    }

    static void testPutWithOversize() throws Exception {
        System.out.println("Test put with oversize");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 40);
        Controller controller = new Controller();

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.deepToString(storage.getFiles()));
            File file = new File(100 + i, "item " + i, "pic", 20);
            controller.put(storage, file);
        }
    }

    static void testPutWithNoCells() throws Exception {
        System.out.println("Test put with no cells");
        Storage storage = new Storage(1001, new File[3], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.deepToString(storage.getFiles()));
            File file = new File(200 + i, "file " + i, "music", 4);
            controller.put(storage, file);
        }
    }

    static void testPutFileWrongFormat() throws Exception {
        System.out.println("Test put with wrong format");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        File wrongFile = new File(1003, "wfile", "wrong", 22);
        System.out.println();
        storage.setFiles(new File[10]);
        controller.put(storage, wrongFile);
    }

    static void testDeleteNormally() throws Exception {
        System.out.println("Test delete normally");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();
        File file = new File(300, "mustDelete", "text", 4);

        controller.put(storage, file);
        System.out.println(Arrays.deepToString(storage.getFiles()));

        controller.delete(storage, file);
        System.out.println(Arrays.deepToString(storage.getFiles()));
    }

    static void testDeleteFromEmpty() throws Exception {
        System.out.println("Test delete from empty");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();
        File file = new File(300, "mustDelete", "text", 4);

        controller.delete(storage, file);
    }

    static void testDeleteWithWrongId() throws Exception {
        System.out.println("Test delete wrong id");
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();
        File file = new File(300, "mustDel", "text", 4);
        File file1 = new File(310, "mustDel", "text", 4);

        controller.put(storage, file);
        System.out.println(Arrays.deepToString(storage.getFiles()));
        controller.delete(storage, file1);
        System.out.println(Arrays.deepToString(storage.getFiles()));
    }

    static void testTransferAllNormally() throws Exception {
        System.out.println("Test transfer all normally");
        Storage storageFrom = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storageTo = new Storage(1002, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        for (int i = 0; i < 8; i++) {
            File fileFrom = new File(400 + i, "fileFrom " + i, "text", 4);
            controller.put(storageFrom, fileFrom);
        }
        System.out.println(Arrays.deepToString(storageFrom.getFiles()));

        File file = new File(310, "file", "text", 4);
        controller.put(storageTo, file);
        System.out.println(Arrays.deepToString(storageTo.getFiles()));

        controller.transferAll(storageFrom, storageTo);
        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));
    }

    static void testTransferAllNoCells() throws Exception {
        System.out.println("Test transfer all no free cells in To ");
        Storage storageFrom = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storageTo = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        for (int i = 0; i < 5; i++) {
            File fileFrom = new File(500 + i, "fileFrom " + i, "text", 4);
            controller.put(storageFrom, fileFrom);
        }
        System.out.println(Arrays.deepToString(storageFrom.getFiles()));

        File file = new File(550, "file", "text", 4);
        controller.put(storageTo, file);
        System.out.println(Arrays.deepToString(storageTo.getFiles()));

        controller.transferAll(storageFrom, storageTo);
        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));
    }

    static void testTransferAllNoSpace() throws Exception {
        System.out.println("Test transfer all no free space in To ");
        Storage storageFrom = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storageTo = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 10);
        Controller controller = new Controller();

        for (int i = 0; i < 5; i++) {
            File fileFrom = new File(600 + i, "fileFrom " + i, "text", 20);
            controller.put(storageFrom, fileFrom);
        }
        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));

        controller.transferAll(storageFrom, storageTo);
        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));
    }

    static void testTransferAllEmptySource() throws Exception {
        System.out.println("Test transfer all empty From");
        Storage storageFrom = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storageTo = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));

        controller.transferAll(storageFrom, storageTo);
    }

    static void testTransferAllWrongFormat() throws Exception {
        System.out.println("Test transfer all wrong format");
        Storage storageFrom = new Storage(1001, new File[5], new String[]{"pic", "music", "text", "some"}, "USA", 100);
        Storage storageTo = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        File file = new File(700, "wrongF", "some", 20);

        controller.put(storageFrom, file);

        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));

        controller.transferAll(storageFrom, storageTo);

        System.out.println(Arrays.deepToString(storageFrom.getFiles()));
        System.out.println(Arrays.deepToString(storageTo.getFiles()));
    }

    static void testTransferByIdNormally() throws Exception {
        System.out.println("Test transfer by id normally");
        Storage storage1 = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storage2 = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        for (int i = 0; i < 2; i++) {
            File file = new File(i + 900, "file " + i, "music", 2);
            controller.put(storage1, file);
        }

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        controller.transferFile(storage1, storage2, 900);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        controller.transferFile(storage1, storage2, 901);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
    }

    static void testTransferByIdEmptyFrom() throws Exception {
        System.out.println("Test transfer by id empty From");
        Storage storage1 = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storage2 = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        controller.transferFile(storage1, storage2, 100);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
    }

    static void testTransferByIdAlreadyExist() throws Exception {
        System.out.println("Test transfer by id already exist");
        Storage storage1 = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storage2 = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        File file = new File(1000, "file", "music", 2);
        controller.put(storage1, file);
        controller.put(storage2, file);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        controller.transferFile(storage1, storage2, 1000);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
    }

    static void testTransferByIdWrongFormat() throws Exception {
        System.out.println("Test transfer by id wrong format");
        Storage storage1 = new Storage(1001, new File[5], new String[]{"pic", "music", "text", "some"}, "USA", 100);
        Storage storage2 = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        File file = new File(1100, "wrongF", "some", 2);
        controller.put(storage1, file);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        controller.transferFile(storage1, storage2, 1100);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
    }

    static void testTransferByIdNoFound() throws Exception {
        System.out.println("Test transfer by id no found");
        Storage storage1 = new Storage(1001, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storage2 = new Storage(1002, new File[5], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        File file = new File(1200, "file", "music", 2);
        controller.put(storage1, file);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        controller.transferFile(storage1, storage2, 300);

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
    }
}



