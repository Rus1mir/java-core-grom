package lesson15hw.hw1;
// Так же создайте класс UserRepository, который будет содержать поле
// User[] users, выполняющее роль условной базы данных. Поле инициализируется в конструкторе.
// Возможность модификации поля из других классов должна быть закрыта, но возможность чтения открыта

// В этом классе напишите методы, доступные для всех:
// User save(User user)  - добавляет юзера и возвращает его
// User update(User user) -  обновляет текущего юзера в массиве (перезаписывать) и возвращать его.
// Если юзера нет, результат метода null
// void delete(long id) - удаляет юзера с массива
// findUser(User user) - нахождение юзера в условной базе данных

public class UserRepository {

    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    // User save(User user)  - добавляет юзера и возвращает его
    public User save(User user) {
        //Если такой уже есть в базе, то не добавляем
        if (findUser(user) != null) return null;
        //Ищем свободную ячейку и добавляем в первую
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return user;
            }
        }
        return null;
    }

    // User update(User user) -  обновляет текущего юзера в массиве (перезаписывать) и возвращать его.
    // Если юзера нет, результат метода null
    public User update(User user) {
        //Если пытаются обновить юзера без изменений то не обновляем
        if (findUser(user) != null) return null;
        //Ищем юзера с нужным ID и обновляем
        for (int i = 0; i < users.length; i++) {
            if ((users[i] != null) && (users[i].getId() == user.getId())) {
                users[i] = user;
                return user;
            }
        }
        return null;
    }

    // void delete(long id) - удаляет юзера с массива
    public void delete(long id) {
        for (int i = 0; i < users.length; i++) {
            if ((users[i] != null) && (users[i].getId() == id))
                users[i] = null;
        }
    }

    //findUser(User user) - нахождение юзера в условной базе данных
    public User findUser(User user) {
        for (User u : users) {
            if (user.equals(u)) return user;
        }
        return null;
    }
}
