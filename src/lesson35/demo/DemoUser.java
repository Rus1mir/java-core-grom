package lesson35.demo;

import lesson35.controller.UserController;
import lesson35.model.User;

public class DemoUser {

    private static UserController userController = new UserController();

    public static void main(String[] args) throws Exception {

        //userRegister(); //tested;
        userLogin(); //tested;
    }

    private static void userRegister() throws Exception {

        User user = new User(-1, "Nguen", "4545", "Zimbabwe", User.UserType.USER);
        System.out.println(userController.registerUser(user));
    }

    private static void userLogin() throws Exception {

        //userController.login("Janet", "12");
        userController.login("Joann", "1234");
        userController.login("Joann", "1234");
    }
}
