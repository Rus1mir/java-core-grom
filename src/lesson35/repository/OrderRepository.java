package lesson35.repository;

import lesson35.model.Order;

import java.util.Random;

public class OrderRepository {

    private final String PATH = "D:/OrderDb.txt";

    public Order save(Order order) throws Exception {

        order.setId(Math.abs(new Random().nextLong()));
        return (Order) DataReaderWriter.save(order, PATH);
    }
}
