package lesson9;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        User[] users = new User[10];
        users[4] = new User(12, "Ivan", "321435");
        users[7] = new User(13, "Den", "321436");
        users[9] = new User(14, "Janis", "321437");

        UserRepository userRepository = new UserRepository(users);
        User user = new User(11, "Goga", "3434333");
        userRepository.save(user);
        System.out.println(Arrays.deepToString(userRepository.getUserNames()));
        System.out.println(Arrays.toString(userRepository.getUserIds()));
        System.out.println(userRepository.getUserNameById(13));
        System.out.println(userRepository.getUserById(13).getName());
        System.out.println(userRepository.getUserByName("Ivan").getId());
        System.out.println(userRepository.getUserBySessionId("321437").getName());
        User upUser = new User(12, "Vasya no Ivan", "1234333");
        userRepository.update(upUser);
        System.out.println(Arrays.deepToString(userRepository.getUserNames()));
        userRepository.delete(14);
        System.out.println(Arrays.deepToString(userRepository.getUserNames()));
    }
}
