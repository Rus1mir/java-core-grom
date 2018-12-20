package lesson34.hw2;

import java.io.*;

public class Solution {

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        validate(fileFromPath);

        StringBuffer contains = new StringBuffer();
        StringBuffer notContains = new StringBuffer();

        sortByContainsWord(readFile(fileFromPath), word, contains, notContains);

        writeFile(fileToPath, contains);

        writeFile(fileFromPath, notContains);
    }

    private static StringBuffer readFile(String path) throws Exception {
        StringBuffer res = new StringBuffer();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File: " + path + " does no exist");
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return res;
    }

    private static void sortByContainsWord(StringBuffer input, String word, StringBuffer contains, StringBuffer notContains) {

        for (String s : new String(input).split("\\.")) {

            if (s.length() > 10 && s.contains(word)) {
                contains.append(s);
                contains.append(".");
            } else {
                notContains.append(s);
                notContains.append(".");
            }
        }
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
