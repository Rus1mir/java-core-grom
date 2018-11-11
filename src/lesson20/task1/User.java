package lesson20.task1;
// Создайте класс User c полями:
// long id
// String name
// String sessionId
// Объект должен создаваться по всем полям. Также возможность модификации полей должна быть закрыта,
// но возможность их считывания нет. Юзеры считаются равные, если у них равные все поля

public class User {
    private long id;
    private String name;
    private String sessionId;

    public User(long id, String name, String sessionId) {
        this.id = id;
        this.name = name;
        this.sessionId = sessionId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
