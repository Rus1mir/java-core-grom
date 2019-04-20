package lesson35.repository;

import lesson35.exception.ReferenceException;
import lesson35.exception.DataFormatErrorException;
import lesson35.model.Order;
import lesson35.model.User;

import java.util.ArrayList;


public class UserRepository extends GeneralRepo<User> {

    private static User loginedUser;

    public UserRepository() {
        super("C:/javaExercises/project/UserDb.txt", 5);
    }

    public static boolean isLogined() {
        return (loginedUser != null);
    }

    public static boolean isAdmin() {
        return (loginedUser != null && loginedUser.getUserType() == User.UserType.ADMIN);
    }

    public static User getLoginedUser() {
        return loginedUser;
    }

    public void login(User user) {

        if (loginedUser != null && user.getId() == loginedUser.getId()) {
            System.out.println("User with id " + user.getId() + " was already login");
            return;
        }

        loginedUser = user;
    }

    public void logout() {
        loginedUser = null;
    }

    public User getUserByNameAndPass(String name, String password) throws Exception {

        for (User user : getObjectsFromDb()) {

            if (user.getUserName().equals(name) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    @Override
    protected User mapping(String[] fields) throws Exception {

        try {
            return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], User.UserType.valueOf(fields[4]));
        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong Id field format detected");
        }
    }

    @Override
    protected void checkReferences(User object) throws Exception {
        ArrayList<Order> orders = new OrderRepository().getObjectsFromDb();

        for (Order order : orders) {
            if (order.getRoom().getId() == object.getId())
                throw new ReferenceException("Removing User " + object.getId() +
                        " was failed cause one of some orders still has reference to it");
        }
    }
}
