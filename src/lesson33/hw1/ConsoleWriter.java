package lesson33.hw1;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ConsoleWriter {
    private static final String WRITE_SIGN = "wr";

    public static void writeToFileFromConsole(String path) {

        System.out.println("Enter file content to write in the file: " + path);
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader consoleRdr = new BufferedReader(inputStreamReader);

        String line;
        String content = "";
        try {
            while (!(line = consoleRdr.readLine()).trim().equals(WRITE_SIGN)) {
                    content = content + line + '\n';
            }
            appendToFile(path, content);
            System.out.println("File " + path + " has been updated successfully");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            IOUtils.closeQuietly(consoleRdr);
            IOUtils.closeQuietly(inputStreamReader);
        }
    }

    private static void appendToFile(String path, String content) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("File with path " + path + " was not found");
        }

        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;

        try {
            writer = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.append(content);
        } catch (IOException e) {
            throw new IOException("Can't write to file with path: " + path);
        } finally {
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(writer);
        }
    }
}
