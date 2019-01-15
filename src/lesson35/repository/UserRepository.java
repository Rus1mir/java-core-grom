package lesson35.repository;

import lesson35.model.User;

import java.util.Random;

public class UserRepository {

    private final String PATH = "D:/UserDb.txt";

    public User save(User user) throws Exception {

        user.setId(Math.abs(new Random().nextLong()));
        return (User) DataReaderWriter.save(user, PATH);
    }
}
