package lesson35.repository;

import lesson35.model.WritableToCSV;

import java.io.*;
import java.util.ArrayList;

public class DataReaderWriter {

    public static WritableToCSV save(WritableToCSV data, String path) throws Exception {
        validate(path);

        ArrayList<String> recordSet = getRecords(path);
        String dataLine = data.fieldsToCSV();
        recordSet.add(dataLine);
        saveRecords(recordSet, path);

        return data;
    }

    private static ArrayList<String> getRecords(String path) throws Exception {
        String line;
        ArrayList<String> recordSet = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                recordSet.add(line);
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return recordSet;
    }

    private static void saveRecords (ArrayList<String> recordSet, String path) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            for (String rec : recordSet) {
                bw.append(rec);
            }
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }

    private static void validate(String path) throws Exception {
        File file = new File(path);

        if (!file.exists()) throw new FileNotFoundException("File " + file.getPath() + " does not exist");

        if (!file.canRead()) throw new Exception("File: " + file.getPath() + " does no permissions to read");

        if (!file.canWrite()) throw new Exception("File: " + file.getPath() + " does no permissions to write");
    }
}
