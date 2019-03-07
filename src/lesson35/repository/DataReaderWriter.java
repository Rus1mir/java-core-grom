package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataReaderWriter {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YYYY");

    public static <T> T save(T data, String path) throws Exception {
        validate(path);
        appendRecord(data.toString(), path);
        return data;
    }

    public static void deleteRecordById(String path, long id) throws Exception {
        int index = 0;
        ArrayList<String> recordset = getRecords(path);

        for (String rec : recordset) {
            long id1;

            try {
                id1 = Long.parseLong(rec.substring(0, rec.indexOf(',')));
            } catch (Exception e) {
                throw new DataFormatErrorException("Incorrect format field Id in file: " + path + " row: " + index);
            }

            if (id == id1) {
                recordset.remove(index);
                break;
            }
            index++;
        }
        saveRecords(recordset, path);
    }

    public static ArrayList<String[]> getRecordsByFilter(String path, String[] filter) throws Exception {
        validate(path);

        ArrayList<String[]> res = new ArrayList<String[]>();

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (matchFilter(fields, filter))
                    res.add(fields);
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return res;
    }

    private static void validate(String path) throws Exception {
        File file = new File(path);

        if (!file.exists()) throw new FileNotFoundException("File " + file.getPath() + " does not exist");

        if (!file.canRead()) throw new Exception("File: " + file.getPath() + " does no permissions to read");

        if (!file.canWrite()) throw new Exception("File: " + file.getPath() + " does no permissions to write");
    }

    private static boolean matchFilter(String[] fields, String[] filter) throws Exception {

        if (fields.length != filter.length)
            throw new DataFormatErrorException("Number of fields is incorrect");

        boolean res = true;
        int index = 0;

        for (String f : filter) {
            res &= (f == null || fields[index++].equals(f));
        }
        return res;
    }

    private static ArrayList<String> getRecords(String path) throws Exception {
        validate(path);

        String line;
        ArrayList<String> recordSet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                recordSet.add(line);
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return recordSet;
    }

    private static void saveRecords(ArrayList<String> recordSet, String path) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            for (String rec : recordSet) {
                bw.append(rec);
                bw.append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }

    private static void appendRecord(String line, String path) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(line);
            bw.append("\n");
        } catch (IOException e) {
            throw new IOException("Can't append record file: " + path, e);
        }
    }
}
