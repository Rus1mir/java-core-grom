package lesson25.exercise;

public class Order extends idEntity {
    private long id;

    public Order(long id) {
        this.id = id;
    }

    @Override
    public long getID() {
        return id;
    }
}
