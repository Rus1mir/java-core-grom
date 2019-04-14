package lesson35.service;

//import lesson35.exception.DuplicateUserExeption;
//import lesson35.exception.UserNotExistExeption;
import lesson35.exception.UserNotFoundException;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {
        return userRepository.addUser(user);
    }

    public void login(String userName, String password) throws Exception {
        User existingUser = userRepository.getUserByNameAndPass(userName, password);

        if (existingUser == null)
           throw new UserNotFoundException("UserService method login returns exception. User with this name: " +
                   userName + " and password: " + password + " was no found, login failed.");

        User.login(existingUser.getId());
    }

    public void logout() {
        User.logout();
    }
}
