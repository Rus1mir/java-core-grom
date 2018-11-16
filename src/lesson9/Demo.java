package lesson9;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        testSaveMethod();
        testUpdateMethod();
        testDeleteMethod();
        testGetUserNamesMethod();
        testGetUserIdsMethod();
        testGetUserNameByIdMethod();
        testGetUserByCondMethods();
    }

    private static void testSaveMethod() {
        //сохранение null+
        //сохранение юзер+
        //сохранение юзера с уже существующим id+
        //сохранение когда нет места+

        User[] users = new User[10];
        UserRepository userRepository = new UserRepository(users);

        System.out.println(userRepository.save(null));

        User user = new User(1209, "Jack", "12A13");
        System.out.println(userRepository.save(user));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        System.out.println(userRepository.save(user));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        int n = 15;
        while (n > 0) {
            User user1 = new User(n, "repository " + n, "123" + n);
            System.out.println(userRepository.save(user1));
            n--;
        }
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
    }

    private static void testUpdateMethod() {
        //обновление юзера+
        //обновление несуществующего юзера+
        //передача null в метод+

        User[] users = new User[10];
        users[0] = new User(1212, "Ivan", "12D43");
        UserRepository userRepository = new UserRepository(users);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        User user = new User(1212, "NEW Ivan", "12D43");
        System.out.println(userRepository.update(user));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        User user1 = new User(22, "NEW Ivan", "12D43");
        System.out.println(userRepository.update(user1));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        System.out.println(userRepository.update(null));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
    }

    private static void testDeleteMethod() {
        //удаление несуществующего юзера из пустой базы+
        //удаление несуществующего юзера+
        //удаление существующего юзера+
        User[] users = new User[10];
        UserRepository userRepository = new UserRepository(users);

        userRepository.delete(11);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        User user = new User(1212, "Ivan", "12D43");
        userRepository.save(user);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        userRepository.delete(11);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        userRepository.delete(1212);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
    }

    private static void testGetUserNamesMethod() {
        //получить список имен из пустого массива+
        //получить список имен из полного массива+
        //получить список имен из массива с несколькими null ячейками+
        User[] users = new User[10];
        UserRepository userRepository = new UserRepository(users);
        System.out.println(Arrays.toString(userRepository.getUserNames()));

        for (int i = 100; i < 111; i++) {
            userRepository.save(new User(i, "repository " + i, "A " + i));
        }
        System.out.println(Arrays.toString(userRepository.getUserNames()));

        for (int i = 100; i < 111; i += 2) {
            userRepository.delete(i);
        }
        System.out.println(Arrays.toString(userRepository.getUserNames()));
    }

    private static void testGetUserIdsMethod() {
        //получить список id из пустого массива+
        //получить список id из полного массива+
        //получить список id из массива с несколькими null ячейками+
        User[] users = new User[10];
        UserRepository userRepository = new UserRepository(users);
        System.out.println(Arrays.toString(userRepository.getUserNames()));

        for (int i = 100; i < 111; i++) {
            userRepository.save(new User(i, "repository " + i, "A " + i));
        }
        System.out.println(Arrays.toString(userRepository.getUserIds()));

        for (int i = 100; i < 111; i += 2) {
            userRepository.delete(i);
        }
        System.out.println(Arrays.toString(userRepository.getUserIds()));
    }

    private static void testGetUserNameByIdMethod() {
        //нахождение имени в пустом массиве+
        //нахождение имени по существующему id+
        //нахождение имени по не существующему id+
        User[] users = new User[10];
        UserRepository userRepository = new UserRepository(users);

        System.out.println(userRepository.getUserNameById(12));

        User user = new User(1212, "Ivan", "12D43");
        userRepository.save(user);
        System.out.println(userRepository.getUserNameById(1212));

        System.out.println(userRepository.getUserNameById(1213));
    }

    private static void testGetUserByCondMethods() {
        //нахождение юзера в пустом массиве
        //нахождение юзера по существующему условию в полном массиве
        //нахождение юзера по существующему условию в частично заполненном массиве
        //нахождение юзера по не существующему условию

        User[] users = new User[10];
        UserRepository userRepository = new UserRepository(users);

        System.out.println(userRepository.getUserById(12));
        System.out.println(userRepository.getUserByName("Ivan"));
        System.out.println(userRepository.getUserBySessionId("S12"));

        User user = new User(1212, "John", "12D43");
        userRepository.save(user);
        for (int i = 100; i < 109; i++) {
            userRepository.save(new User(i, "repository " + i, "A " + i));
        }
        System.out.println(Arrays.deepToString(userRepository.getUsers()));


        System.out.println(userRepository.getUserById(1212));
        System.out.println(userRepository.getUserByName("repository 102"));
        System.out.println(userRepository.getUserBySessionId("A 105"));
    }
}
