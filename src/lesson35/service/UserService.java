package lesson35.service;

import lesson35.exception.DuplicateUserExeption;
import lesson35.exception.UserNotExistExeption;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {
        if (userRepository.getUserByNameAndPass(user.getUserName(), user.getPassword()) != null)
            throw new DuplicateUserExeption("User with name " + user.getUserName() + " already exist in database");

        return userRepository.save(user);
    }

    public void login(String userName, String password) throws Exception {
        User existingUser = userRepository.getUserByNameAndPass(userName, password);

        if (existingUser == null)
            throw new UserNotExistExeption("User with this name and password was no found, login failed");

        User.login(existingUser.getId());
    }

    public void logout() {
        User.logout();
    }
}
