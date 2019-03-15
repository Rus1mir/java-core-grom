package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class DemoUser {
    public static void main(String[] args) throws Exception{
        //userRepoSave();
        //userControllerReg();
        userControllerLogin();
    }

    private static void userControllerLogin() throws Exception {

        UserRepository repository = new UserRepository();
        User user = repository.getUserByNameAndPass("Janet", "6456");

        UserController userController = new UserController();
        userController.login("Janet", "6456");
        System.out.println(User.isLogined(user.getId()));

        userController.logout();
        System.out.println(User.isLogined(user.getId()));
    }

    private static void userControllerReg() throws Exception {

        User user = new User(-1,"John","23", "USA", User.UserType.USER);
        UserController userController = new UserController();
        System.out.println(userController.registerUser(user));
    }

    private static void userRepoSave() throws Exception {

        User user = new User(-1,"Peter","12345", "USA", User.UserType.USER);
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.save(user));
    }
}
