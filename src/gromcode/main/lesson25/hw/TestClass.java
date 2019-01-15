package gromcode.main.lesson25.hw;

public class TestClass extends EntityId{
    private long id;
    private int price;

    public TestClass(long id, int price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
