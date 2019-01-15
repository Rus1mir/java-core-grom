package gromcode.main.lesson20.task1;

import gromcode.main.lesson20.task1.exception.BadRequestException;
import gromcode.main.lesson20.task1.exception.InternalServerException;
import gromcode.main.lesson20.task1.exception.UserNotFoundException;

public class UserRepository {

    private User[] users;

    public User[] getUsers() {
        return users;
    }

    public User save(User user) throws Exception {

        if (user == null)
            throw new BadRequestException("User is null can't save repository");

        try {
            findById(user.getId());
            throw new BadRequestException("User with id: " + user.getId() + " is already exists");
        } catch (UserNotFoundException e) {
            System.out.println("User with id: " + user.getId() + " was not found. Will be saved");
        }

        int index = 0;
        for (User us : users) {
            if (us == null) {
                users[index] = user;
                return users[index];
            }
            index++;
        }
        throw new InternalServerException("Not enough space for save repository id: " + user.getId());
    }

    public User update(User user) throws Exception {

        if (user == null)
            throw new BadRequestException("User is null can't update repository");

        findById(user.getId());

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == user.getId()) {
                users[index] = user;
                return users[index];
            }
            index++;
        }
        throw new InternalServerException("Unexpected error");
    }

    public void delete(long id) {
        int index = 0;
        for (User user : users) {
            if (user != null && user.getId() == id) {
                users[index] = null;
            }
            index++;
        }
    }

    public User findById(long id) throws UserNotFoundException {

        for (User user : users) {
            if (user != null && user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException("User with id: " + id + " was not found");
    }
}
