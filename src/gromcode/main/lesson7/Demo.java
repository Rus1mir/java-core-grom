package gromcode.main.lesson7;

import gromcode.main.lesson6.Car;
import gromcode.main.lesson6.DbConnector;
import gromcode.main.lesson6.User;

public class Demo {
    public static void main(String[] args) {
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User("Jack");

        Car car = new Car(1000, 2015, "Anton");
        DbConnector dbConnector = new DbConnector();
    }
}
