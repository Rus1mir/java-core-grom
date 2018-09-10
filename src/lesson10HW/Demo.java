package lesson10HW;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Customer customer = new Customer("Test", "Киев", "Мужской");
        Customer customer1 = new Customer("Натали", "Киев", "Женский");

        Order[] orders = new Order[4];

        orders[0] = new ElectronicsOrder("Something", new Date(),
                "Киев", "Киев", 200, customer, 12);
        orders[1] = new ElectronicsOrder("Something else", new Date(),
                "Киев", "Днепр", 100, customer1, 12);

        orders[2] = new FurnitureOrder("Something furniture", new Date(),
                "Киев", "Винница", 1000, customer, "2");
        orders[3] = new FurnitureOrder("Something else details", new Date(),
                "Киев", "Ташкент", 800, customer1, "12");

        for (Order order : orders) {
            processOrder(order);
        }

    }

    private static void processOrder(Order order) {
        order.calculatePrice();
        order.validateOrder();
        order.confirmShipping();
        System.out.println(order.getCustomerOwned().getName());
        System.out.println(order.getItemName());
        System.out.println(order.getBasePrice());
        System.out.println(order.getTotalPrice());
        System.out.println(order.getDateConfirmed());
        System.out.println(order.getDateShipped());
        System.out.println();
    }
}
