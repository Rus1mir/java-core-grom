package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.model.Entity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class GeneralRepo<T extends Entity> {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
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

        if (data.getId() <= 0) data.generateId();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(data.toString());
            bw.append("\n");
        } catch (IOException e) {
            throw new IOException("Can't append file: " + path + " with save " + data, e);
        }
        return data;
    }

    public ArrayList<T> getAllObjectsFromDb() throws Exception {

        validateFiles();

        ArrayList<T> objects = new ArrayList<>();

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((line = br.readLine()) != null) {
                objects.add(mapping(line.split(",")));
            }
        } catch (IOException e) {
            throw new IOException("Can't read file: " + path, e);
        }
        return objects;
    }

    public T getObjectByID(long id) throws Exception {

        //Упростил выборку по id
        for (T o : getAllObjectsFromDb()) {

            if (o.getId() == id)
                return o;
        }

        System.out.println("No object id: " + id + " found in file: " + path);
        return null;
    }

    protected void deleteById(long id) throws Exception {

        ArrayList<T> objects = getAllObjectsFromDb();

        for (T o : objects) {
            if (o.getId() == id) {
                objects.remove(o);
                break;
            }
        }
        rewriteFile(objects);
    }

    //Abstract methods
    protected abstract T mapping(String[] fields) throws Exception;

    //Вынес метод в классы - наследники
    public abstract void deleteObjectById(long id) throws Exception;

    private void rewriteFile(ArrayList<T> objects) throws Exception {

        validateFiles();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {

            for (T o : objects) {
                bw.append(o.toString());
                bw.append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Can't write file: " + path, e);
        }
    }

    private void validateFields(String[] fields) throws Exception {

        if (fields.length != fieldsCount)
            throw new DataFormatErrorException("Number of fields is wrong, required: " + fieldsCount);

        for (String f : fields) {
            if (f.trim().isEmpty())
                throw new DataFormatErrorException("Empty field detected");
        }
    }

    private void validateFiles() throws Exception {
        File file = new File(path);

        if (!file.exists()) throw new FileNotFoundException("File " + file.getPath() + " does not exist");

        if (!file.canRead()) throw new IOException("File: " + file.getPath() + " does no permissions to read");

        if (!file.canWrite()) throw new IOException("File: " + file.getPath() + " does no permissions to write");
    }
}
