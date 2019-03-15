package lesson35.repository;

import lesson35.model.User;

import java.util.ArrayList;
import java.util.Random;

public class UserRepository {

    private final String PATH = "D:/UserDb.txt";

    public User save(User user) throws Exception {

        user.setId(Math.abs(new Random().nextLong()));
        return (User) DataReaderWriter.save(user, PATH);
    }

    public User getUserByNameAndPass(String name, String password) throws Exception {

        String userFilter[] = {null, name, password, null, null};

        ArrayList<String[]> res = DataReaderWriter.getRecordsByFilter(PATH, userFilter);

        if (res.size() != 0)
            return new User(res.get(0));

        return null;
    }
}
