package gromcode.main.lesson34.task1;

import java.io.*;

public class Solution {

    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);

        writeFile(fileToPath, readFile(fileFromPath));
    }

    private static void writeFile(String path, StringBuffer contentToWrite) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(contentToWrite);
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }

    private static StringBuffer readFile(String path) throws Exception {
        StringBuffer res = new StringBuffer();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                res.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File: " + path + " does no exist");
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return res;
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        if (!fileFrom.exists()) throw new FileNotFoundException("File: " + fileFromPath + " does no exist");

        if (!fileTo.exists()) throw new FileNotFoundException("File: " + fileToPath + " does no exist");

        if (!fileFrom.canRead()) throw new Exception("File: " + fileFromPath + " does no permissions to read");

        if (!fileTo.canWrite()) throw new Exception("File: " + fileToPath + " does no permissions to write");
    }
}
