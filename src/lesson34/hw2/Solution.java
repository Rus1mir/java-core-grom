package lesson34.hw2;

import java.io.*;

public class Solution {

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        // validate(fileFromPath);

        StringBuffer original = readFile(fileFromPath);

        StringBuffer[] results = sortByContainsWord(original, word);

        writeFile(fileToPath, results[0]);

        try {
            writeFile(fileFromPath, results[1]);
        } catch (Exception e) {
            undo(original, fileFromPath, fileToPath);
            throw new IOException(e.getMessage());
        }
    }

    private static void undo(StringBuffer original, String fromPath, String toPath) throws Exception {
        File file = new File(toPath);

        if (file.exists())
            file.delete();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fromPath))) {
            bw.append(original);
        } catch (IOException e) {
            throw new IOException("Can't undo file: " + fromPath, e);
        }
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

    private static StringBuffer[] sortByContainsWord(StringBuffer input, String word) {
        StringBuffer[] res = {new StringBuffer(), new StringBuffer()};

        for (String s : new String(input).split("\\.")) {

            if (s.length() > 10 && s.contains(word)) {
                res[0].append(s);
                res[0].append(".");
            } else {
                res[1].append(s);
                res[1].append(".");
            }
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
