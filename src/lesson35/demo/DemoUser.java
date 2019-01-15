package lesson35.demo;

import lesson35.model.User;
import lesson35.repository.UserRepository;

public class DemoUser {
    public static void main(String[] args) throws Exception{

        User user = new User(-1,"fff","ffff", "ff", User.UserType.USER);
        UserRepository userRepository = new UserRepository();
        userRepository.save(user);
    }
}
