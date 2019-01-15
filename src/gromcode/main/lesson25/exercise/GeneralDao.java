package gromcode.main.lesson25.exercise;

public class GeneralDao {

    private void print(Order order) {
        System.out.println("Order is:" + order.toString());
    }

    public <T extends idEntity> void validate(T t) throws Exception {
        if (t.getID() <= 0)
            throw new Exception("id can't be negative");
    }

    public <K> void validate(K k) {
        if (k.equals(100)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
