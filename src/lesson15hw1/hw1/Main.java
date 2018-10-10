package lesson15hw1.hw1;

public class Main {
    public static void main(String[] args) {
        User[] users = new User[5];
        users[0] = new User(11, "Vasya", "q1212");
        users[1] = new User(123, "Petya", "f3434");
        UserRepository userRepository = new UserRepository(users);
        System.out.println(userRepository.findUser(users[0]));
        User user = new User(123, "new Petya", "d33");
        System.out.println(userRepository.update(user));
    }
}
