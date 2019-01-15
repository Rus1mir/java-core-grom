package gromcode.main.lesson27.hw2;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws Exception {
        UserRepository repository = new UserRepository();
        User user = new User(12, "Vasya", "AA34fd");

        //Save user
        System.out.println(repository.save(user).toString());
        System.out.println(Arrays.deepToString(repository.getAll()));

        //Save with same id
        User user1 = new User(12, "Petya", "NN45");
        // System.out.println(repository.save(user1).toString());

        //Update
        System.out.println(repository.update(user1));
        System.out.println(Arrays.deepToString(repository.getAll()));

        //Update no found
        User user2 = new User(14, "Petya", "NN45");
        //repository.update(user2);

        //Delete
        repository.save(user2);
        System.out.println(Arrays.deepToString(repository.getAll()));
        repository.delete(12);
        System.out.println(Arrays.deepToString(repository.getAll()));
    }
}
