package lesson25.hw;

public class Sys extends EntityId {

    private long id;
    private String name;

    public Sys(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
