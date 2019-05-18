package lesson35.service;

import lesson35.exception.BadRequestException;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {

        //Поправил эксепшн по замечанию
        if (userRepository.getUserByNameAndPass(user.getUserName(), user.getPassword()) != null)
                throw new BadRequestException("User with equal name and password already exists. Register not possible");

        return userRepository.addObjectToDb(user);
    }

    public void login(String userName, String password) throws Exception {

        User existingUser = userRepository.getUserByNameAndPass(userName, password);

        //Поправил эксепшн по замечанию
        if (existingUser == null)
            throw new BadRequestException("User with name " + userName + " was not currently registered login failed");

       userRepository.login(existingUser);
    }

    public void logout() {

        userRepository.logout();
    }
}
