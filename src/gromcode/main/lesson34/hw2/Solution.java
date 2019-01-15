package gromcode.main.lesson34.hw2;

import java.io.*;

public class Solution {

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        validate(fileFromPath);

        StringBuffer original = readFile(fileFromPath);
        StringBuffer noContains = new StringBuffer();
        StringBuffer contains = new StringBuffer();

        for (String s : new String(original).split("\\.")) {
            if (s.length() > 10 && s.contains(word)) {
                contains.append(s);
                contains.append(".");
            } else {
                noContains.append(s);
                noContains.append(".");
            }
        }

        try {
            writeFile(fileToPath, contains);
            writeFile(fileFromPath, noContains);
        } catch (Exception e) {
            new File(fileToPath).delete();
            writeFile(fileFromPath, original);
            throw new IOException(e.getMessage());
        }
    }

    private static StringBuffer readFile(String path) throws Exception {
        StringBuffer res = new StringBuffer();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return res;
    }

    private static void writeFile(String path, StringBuffer contentToWrite) throws Exception {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.append(contentToWrite);
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }

    private static void validate(String fileFromPath) throws Exception {
        File fileFrom = new File(fileFromPath);

        if (!fileFrom.exists()) throw new FileNotFoundException("File: " + fileFromPath + " does no exist");

        if (!fileFrom.canRead()) throw new Exception("File: " + fileFromPath + " does no permissions to read");

        if (!fileFrom.canWrite()) throw new Exception("File: " + fileFromPath + " does no permissions to write");
    }
}
