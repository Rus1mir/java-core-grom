package lesson19hw;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        //testPut();
        //testDelete();
        testTransfer();
        //testTransferById();
    }

    static void testPut() {
        // Simple put
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        File file = new File(12, "pretty", "pic", 20);
        Controller controller = new Controller();
        try {
            controller.put(storage, file);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        // Put with same id
        try {
            controller.put(storage, file);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        // Oversize by size
        File file1 = new File(13, "pretty big", "pic", 81);
        try {
            controller.put(storage, file1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        // Oversize by cells
        try {
            for (int i = 0; i < 10; i++) {
                file = new File(i + 100, "file " + i, "music", 4);
                controller.put(storage, file);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        // Wrong format
        storage.setFiles(new File[10]);
        File wrongFormat = new File(1003, "pretty big", "wrong", 22);
        try {
            controller.put(storage, wrongFormat);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        // Wrong name
        File wrongName = new File(1010, "pretty big name", "text", 8);
        try {
            controller.put(storage, wrongName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));
    }

    static void testDelete() {

        // Delete from empty
        Storage storage = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        File file = new File(12, "pretty", "pic", 20);
        Controller controller = new Controller();

        try {
            controller.delete(storage, file);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        // Delete normally
        try {
            controller.put(storage, file);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        File file1 = new File(12, "pretty", "pic", 20);
        System.out.println(Arrays.deepToString(storage.getFiles()));

        try {
            controller.delete(storage, file1);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        //Delete file with wrong id
        File file2 = new File(13, "pretty", "pic", 20);
        System.out.println(Arrays.deepToString(storage.getFiles()));

        try {
            controller.delete(storage, file2);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));

        //Delete file with wrong name (not equals)
        File file3 = new File(12, "no_pretty", "pic", 20);
        System.out.println(Arrays.deepToString(storage.getFiles()));

        try {
            controller.delete(storage, file3);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage.getFiles()));
    }

    static void testTransfer() {

        // Normally transfer
        Storage storage1 = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storage2 = new Storage(1005, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Controller controller = new Controller();

        for (int i = 0; i < 10; i++) {
            File file = new File(1000 + i, "file" + i, "music", 10);
            try {
                controller.put(storage1, file);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        try {
            controller.transferAll(storage1, storage2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        // No free cells
        try {
            controller.put(storage1, new File(555, "already", "music", 5));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(Arrays.deepToString(storage1.getFiles()));

        try {
            controller.transferAll(storage2, storage1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));

        // No free space (oversize)
        Storage storage3 = new Storage(1007, new File[11], new String[]{"pic", "music", "text"}, "USA", 20);

        try {
            controller.transferAll(storage2, storage3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println(Arrays.deepToString(storage2.getFiles()));
        System.out.println(Arrays.deepToString(storage3.getFiles()));

        // Empty source
        storage1.setFiles(new File[10]);

        try {
            controller.transferAll(storage1, storage3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Wrong format
        Storage storage4 = new Storage(1012, new File[5], new String[]{"pic", "music", "text", "some"}, "USA", 120);
        try {
            controller.put(storage4, new File(234, "wrong", "some", 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.deepToString(storage4.getFiles()));

        try {
            controller.transferAll(storage4, storage1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static void testTransferById() {

        // Normally transfer by id
        Storage storage1 = new Storage(1001, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);
        Storage storage2 = new Storage(1002, new File[10], new String[]{"pic", "music", "text"}, "USA", 100);

        Controller controller = new Controller();

        for (int i = 0; i < 10; i++) {
            File file = new File(i + 100, "file " + i, "music", 4);
            try {
                controller.put(storage1, file);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
        controller.transferFile(storage1, storage2, 102);
        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
        controller.transferFile(storage1, storage2, 101);
        System.out.println(Arrays.deepToString(storage1.getFiles()));
        System.out.println(Arrays.deepToString(storage2.getFiles()));
    }
}



