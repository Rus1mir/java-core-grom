package lesson34.hw3;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Solution {

    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        File from = new File(fileFromPath);
        File to = new File(fileToPath);

        try {
            Files.copy(from.toPath(), to.toPath());
        } catch (IOException e) {
            throw new IOException("Copy file content was filed from: " + fileFromPath + " to: " + fileToPath, e);
        }
    }

    public static void copyFileContentApacheIO(String fileFromPath, String fileToPath) throws Exception {
        File from = new File(fileFromPath);
        File to = new File(fileToPath);

        try {
            FileUtils.copyFile(from, to);
        } catch (IOException e) {
            throw new IOException("Copy file content was filed from: " + fileFromPath + " to: " + fileToPath, e);
        }
    }
}
