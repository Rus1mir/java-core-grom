package lesson22.repository;

public class UserRepository {

    private static User[] users = new User[10];

    public static User[] getUsers() {
        return users;
    }

    public static User save(User user) {
        if (user == null)
            return null;

        if (findById(user.getId()) != null)
            return null;

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return user;
            }
        }
        return null;
    }

    public static User update(User user) {
        if (user == null)
            return null;

        if (findById(user.getId()) != null)
            return null;

        for (int i = 0; i < users.length; i++) {
            if ((users[i] != null) && (users[i].getId() == user.getId())) {
                users[i] = user;
                return user;
            }
        }
        return null;
    }

    public static void delete(long id) {
        for (int i = 0; i < users.length; i++) {
            if ((users[i] != null) && (users[i].getId() == id))
                users[i] = null;
        }
    }

    public static User findById(Long id) {
        for (User u : users) {
            if (u != null && u.getId() == id)
                return u;
        }
        return null;
    }
}
