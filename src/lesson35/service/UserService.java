package lesson35.service;

import lesson35.exception.UserAlreadyExistException;
import lesson35.exception.UserNotFoundException;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {

        if (userRepository.getUserByNameAndPass(user.getUserName(), user.getPassword()) != null)
                throw new UserAlreadyExistException("User with equal name and password already exists. Register not possible");

        return userRepository.addObjectToDb(user);
    }

    public void login(String userName, String password) throws Exception {

        User existingUser = userRepository.getUserByNameAndPass(userName, password);

        if (existingUser == null)
            throw new UserNotFoundException("User with name " + userName + " was not currently registered login failed");

       userRepository.login(existingUser);
    }

    public void logout() {

        userRepository.logout();
    }
}
