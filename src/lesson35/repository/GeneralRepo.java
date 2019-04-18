package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.model.Entity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class GeneralRepo<T extends Entity> {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YYYY");
    private String path;
    private int fieldsCount;

    protected GeneralRepo(String path, int fieldsCount) {
        this.path = path;
        this.fieldsCount = fieldsCount;
    }

    public T addObjectToDb(T data) throws Exception {

        validateFiles();

        String line = data.toString();

        validateFields(line.split(","));

        data.generateId();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(data.toString());
            bw.append("\n");
        } catch (IOException e) {
            throw new IOException("Can't append file: " + path + " with save " + data, e);
        }
        return data;
    }

    public ArrayList<T> getObjectsFromDb() throws Exception {

        ArrayList<T> objects = new ArrayList<>();

        for (String rec : getRecords()) {

            String[] fields = rec.split(",");
            validateFields(fields);

            objects.add(mapping(fields));
        }
        return objects;
    }

    public T getObjectByID(long id) throws Exception {
        validateFiles();

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {

                String[] fields = line.split(",");

                validateFields(fields);

                T obj = mapping(fields);

                if (obj.getId() == id) return obj;
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }

        System.out.println("No object id: " + id + " found in " + new File(path).getName());
        return null;
    }

    public void deleteObjectByID(long id) throws Exception {

        ArrayList<String> records = getRecords();

        for (String rec : records) {

            String[] fields = rec.split(",");
            validateFields(fields);

            T object = mapping(fields);

            if (object.getId() == id) {

                checkReferences(object);

                records.remove(rec);
                saveRecords(records);
                return;
            }
        }
        System.out.println("No object id: " + id + " found in " + new File(path).getName());
    }

    //Specific mapping of entities must be implemented
    protected abstract T mapping(String[] fields) throws Exception;

    protected abstract void checkReferences(T object) throws Exception;

    //Validate, may be overrated
    protected void validateFields(String[] fields) throws Exception {

        if (fields.length != fieldsCount)
            throw new DataFormatErrorException("Number of fields is wrong, required: " + fieldsCount);

        for (String f : fields) {
            if (f.trim().isEmpty())
                throw new DataFormatErrorException("Empty field detected");
        }
    }

    //Not public
    private void validateFiles() throws Exception {
        File file = new File(path);

        if (!file.exists()) throw new FileNotFoundException("File " + file.getPath() + " does not exist");

        if (!file.canRead()) throw new IOException("File: " + file.getPath() + " does no permissions to read");

        if (!file.canWrite()) throw new IOException("File: " + file.getPath() + " does no permissions to write");
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
