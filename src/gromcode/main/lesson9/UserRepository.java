package gromcode.main.lesson9;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    private int getNotEmptyUsersCount() {
        int n = 0;
        for (User user : users) {
            if (user != null)
                n++;
        }
        return n;
    }

    private User[] getNotEmptyUsers() {
        User[] notEmptyUsers = new User[getNotEmptyUsersCount()];
        int n = 0;
        for (User user : users) {
            if (user != null) {
                notEmptyUsers[n] = user;
                n++;
            }
        }
        return notEmptyUsers;
    }

    public String[] getUserNames() {
        User[] realUsers = getNotEmptyUsers();
        String[] userNames = new String[getNotEmptyUsersCount()];
        for (int i = 0; i < realUsers.length; i++) {
            userNames[i] = realUsers[i].getName();
        }
        return userNames;
    }

    public long[] getUserIds() {
        User[] realUsers = getNotEmptyUsers();
        long[] userIds = new long[getNotEmptyUsersCount()];
        for (int i = 0; i < realUsers.length; i++) {
            userIds[i] = realUsers[i].getId();
        }
        return userIds;
    }

    public String getUserNameById(long id) {
        for (User user : getNotEmptyUsers()) {
            if (user.getId() == id)
                return user.getName();
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User user : getNotEmptyUsers()) {
            if (user.getName().equals(name))
                return user;
        }
        return null;
    }

    public User getUserById(long id) {
        for (User user : getNotEmptyUsers()) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public User getUserBySessionId(String sessionId) {
        for (User user : getNotEmptyUsers()) {
            if (user.getSessionId().equals(sessionId))
                return user;
        }
        return null;
    }

    public User findById(long id) {
        for (User user : getNotEmptyUsers()) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public User save(User user) {
        if ((user != null) &&
                (getNotEmptyUsersCount() < users.length) &&
                (findById(user.getId()) == null)) {
            for (int i = 0; i < users.length; i++) {
                if (users[i] == null) {
                    users[i] = user;
                    return user;
                }
            }
        }
        return null;
    }

    private int getIndexUserById(long id) {
        if (findById(id) != null) {
            for (int i = 0; i < users.length; i++) {
                if ((users[i] != null) && (users[i].getId() == id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public User update(User user) {
        if (user == null) return null;
        int userIndex = getIndexUserById(user.getId());
        if (userIndex >= 0) {
            users[userIndex] = user;
            return user;
        }
        return null;
    }

    public void delete (long id) {
        int userIndex = getIndexUserById(id);
        if (userIndex >= 0) {
            users[userIndex] = null;
        }
    }
}
