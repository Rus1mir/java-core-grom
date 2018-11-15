package lesson22.repository;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {

        User user1 = new User(1001, "Daniil", "13123241aasdf");
        UserRepository.save(user1);
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        User user2 = new User(1002, "Andrey", "WDWd121212");
        UserRepository.save(user2);
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        User user3 = new User(1002, "Test", "WDWd121212");
        UserRepository.update(user3);
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

        UserRepository.delete(1002);
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));
        System.out.println(Arrays.deepToString(UserRepository.getUsers()));

    }
}
