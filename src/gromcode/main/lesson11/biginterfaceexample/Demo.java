package gromcode.main.lesson11.biginterfaceexample;

public class Demo {
    public static void main(String[] args) {
        File file1 = new File("test", "C:/repository/home", "txt", 11);
        File file2 = new File("test", "C:/repository/home", "txt", 11);
        File file3 = new File("video", "C:/repository/home", "txt", 11);
        //File file4 = new File("test", "C:/repository/home", "txt", 11);
        File file5 = new File("myhome", "C:/repository/home", "jpg", 120);
        File[] files = new File[]{file1, file2, file3, null, file5};
        Storage storage = new Storage(files);

        FileReader fileReader = new FileReader();
        SimpleReader simpleReader = new SimpleReader();

        read(storage, fileReader);
        read(storage, simpleReader);
    }

    private static void read(Storage storage, Readable readable) {
        readable.readFileFromStorage(storage);
    }
}
