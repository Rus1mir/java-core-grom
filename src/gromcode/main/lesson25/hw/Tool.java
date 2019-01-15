package gromcode.main.lesson25.hw;

public class Tool extends EntityId {
    private long id;
    private String description;

    public Tool(long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
