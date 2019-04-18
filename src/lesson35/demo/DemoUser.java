package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;
import lesson35.repository.UserRepository;

public class DemoUser {
    public static void main(String[] args) throws Exception{

        //userRegister();
        userLogin();
    }

    private static void userRegister() throws Exception {

        User user = new User(-1,"Joann","1234", "USA", User.UserType.USER);
        UserController userController = new UserController();
        System.out.println(userController.registerUser(user));
    }

    private static void userLogin() throws Exception {

        UserController userController = new UserController();
        //userController.login("Janet", "12");
        userController.login("Joann", "1234");
    }
}
