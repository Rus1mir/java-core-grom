package lesson27.hw2;

import lesson27.hw2.exceptions.BadRequestException;
import lesson27.hw2.exceptions.UserNotFoundException;

import java.util.ArrayList;

public class UserRepository {
    public UserRepository() {
    }

    private ArrayList<User> users = new ArrayList<>();

    public User save(User user) throws BadRequestException {
        if (user == null)
            throw new BadRequestException("Can't update null");

        try {
            findById(user.getId());
            throw new BadRequestException("User with id: " + user.getId() + " is already exists");
        } catch (UserNotFoundException e) {
            System.out.println("User with id: " + user.getId() + " was no found. Will be saved.");
        }

        users.add(user);

        return users.get(users.indexOf(user));
    }

    public User update(User user) throws BadRequestException {
        if (user == null)
            throw new BadRequestException("Can't update null");

        users.remove(findById(user.getId()));

        users.add(user);

        return users.get(users.indexOf(user));
    }

    public User findById(long id) throws UserNotFoundException {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        throw new UserNotFoundException("User with id: " + id + " was no found");
    }

    public void delete(long id) {
        for (User user : users) {
            if (user.getId() == id)
                users.remove(user);
        }
    }

    public User[] getAll() {
        return users.toArray(new User[0]);
    }
}
