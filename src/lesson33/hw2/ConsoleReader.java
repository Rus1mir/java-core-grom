package lesson33.hw2;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ConsoleReader {

    public static void readFileByConsolePath() {
        System.out.println("Please, enter file path to read:");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader consoleRdr = new BufferedReader(inputStreamReader);

        String line = null;
        try {
            while ((line = consoleRdr.readLine()) != null) {
                readFile(line.trim());
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            IOUtils.closeQuietly(consoleRdr);
            IOUtils.closeQuietly(inputStreamReader);
        }

    }

    private static void readFile(String path) throws IOException {
        FileReader reader = null;

        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File with path " + path + " was not found");
        }

        BufferedReader br = new BufferedReader(reader);

        try {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new IOException("Can't read file by path " + path);
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(reader);
        }
    }
}
