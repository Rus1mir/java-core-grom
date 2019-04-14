package lesson35.repository;

import lesson35.exception.DataFormatErrorException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class GeneralRepo<T> {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YYYY");

    protected String path;

    protected T save(T data) throws Exception {
        String line = data.toString();
        validateFiles();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(data.toString());
            bw.append("\n");
        } catch (IOException e) {
            throw new IOException("Can't append record file: " + path, e);
        }
        return data;
    }

    protected T getObjectById(long id) throws Exception {
        int index = 0;
        ArrayList<String> recordset = getRecords();

        for (String rec : recordset) {
            long id1;

            try {
                id1 = Long.parseLong(rec.substring(0, rec.indexOf(',')));
            } catch (Exception e) {
                throw new DataFormatErrorException("Incorrect format field Id in file: " + path + " line: " + index);
            }

            if (id == id1) {
                return createObjFromFields(recordset.get(index).split(","));
            }
            index++;
        }
        return null;
    }

    protected void deleteRecordById(long id) throws Exception {

        int index = 0;
        ArrayList<String> recordset = getRecords();

        for (String rec : recordset) {
            long id1;

            try {
                id1 = Long.parseLong(rec.substring(0, rec.indexOf(',')));
            } catch (Exception e) {
                throw new DataFormatErrorException("Incorrect format field Id in file: " + path + " line: " + index);
            }

            if (id == id1) {
                recordset.remove(index);
                break;
            }
            index++;
        }
        saveRecords(recordset);
    }

    protected ArrayList<T> getObjectsByFilter(String[] filter) throws Exception {
        validateFiles();

        ArrayList<T> res = new ArrayList<>();
        int index = 0;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((line = br.readLine()) != null) {

                String[] fields = line.split(",");

                validateFields(fields);

                if (matchFilter(fields, filter)) {
                    res.add(createObjFromFields(fields));
                }
                index++;
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);

        } catch (DataFormatErrorException e) {
            throw new DataFormatErrorException("GeneralRepo getObjectsByFilter method returns exception." +
                    " Wrong format of data field, method can`t create object from file: " + path + " line " + index, e);
        }
        return res;
    }

    protected abstract T createObjFromFields(String[] fields) throws Exception;

    protected abstract void validateFields(String[] fields) throws Exception;

    //Not public
    private void validateFiles() throws Exception {
        File file = new File(path);

        if (!file.exists()) throw new FileNotFoundException("File " + file.getPath() + " does not exist");

        if (!file.canRead()) throw new Exception("File: " + file.getPath() + " does no permissions to read");

        if (!file.canWrite()) throw new Exception("File: " + file.getPath() + " does no permissions to write");
    }

    private boolean matchFilter(String[] fields, String[] filter) throws Exception {

        if (fields.length != filter.length)
            throw new DataFormatErrorException("Number of fields is incorrect");

        boolean res = true;
        int index = 0;

        for (String f : filter) {
            res &= (f == null || fields[index].equals(f));
            index++;
        }
        return res;
    }

    private ArrayList<String> getRecords() throws Exception {
        validateFiles();

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

    private void saveRecords(ArrayList<String> recordSet) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            for (String rec : recordSet) {
                bw.append(rec);
                bw.append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }
}
