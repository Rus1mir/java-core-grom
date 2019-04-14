package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class DemoUser {
    public static void main(String[] args) throws Exception{

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

        User user = new User(-1,"John","213", "USA", User.UserType.USER);
        UserController userController = new UserController();
        System.out.println(userController.registerUser(user));
    }
}
