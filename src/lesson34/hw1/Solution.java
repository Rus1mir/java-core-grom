package lesson34.hw1;

import java.io.*;

public class Solution {

    public static void transferFileContent(String fileFromPath, String fileToPath) throws Exception {
        boolean isEmptyFileTo = validate(fileFromPath, fileToPath);

        writeFile(fileToPath, readFile(fileFromPath), isEmptyFileTo);

        clearFile(fileFromPath);
    }

    private static void writeFile(String path, StringBuffer contentToWrite, boolean isEmpty) throws Exception {
        if (!isEmpty)
            contentToWrite.insert(0, '\n');

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
                if (res.length() > 0) res.append('\n');
                res.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File: " + path + " does no exist");
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return res;
    }

    private static void clearFile(String path) throws Exception {
        try (FileWriter writer = new FileWriter(path)) {
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File: " + path + " does no exist");
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }

    private static boolean validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        if (!fileFrom.exists()) throw new FileNotFoundException("File: " + fileFromPath + " does no exist");

        if (!fileTo.exists()) throw new FileNotFoundException("File: " + fileToPath + " does no exist");

        if (!fileFrom.canRead()) throw new Exception("File: " + fileFromPath + " does no permissions to read");

        if (!fileFrom.canWrite()) throw new Exception("File: " + fileFromPath + " does no permissions to write");

        if (!fileTo.canWrite()) throw new Exception("File: " + fileToPath + " does no permissions to write");

        return (fileTo.length() == 0);
    }
}
