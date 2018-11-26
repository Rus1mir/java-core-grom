package lesson29;

import java.util.*;

public class HashSetTest {
    public static void main(String[] args) {
        System.out.println(useHashSet());
    }

    public static Set<Order> useHashSet() {
        Set<Order> orderSet = new HashSet<>();
        Order order1 = new Order(222, "USD", "fan", "wed431");
        Order order2 = new Order(123, "USD", "kettle", "wed431");
        Order order3 = new Order(300, "USD", "player", "rew452");
        Order order4 = new Order(800, "USD", "freezer", "opt998");
        Order order5 = new Order(456, "USD", "cleaner", "rew452");
        Order order6 = new Order(540, "USD", "cooler", "opy965");
        Order order7 = new Order(1000, "USD", "thing", "rfr965");

        orderSet.add(order1);
        orderSet.add(order2);
        orderSet.add(order3);
        orderSet.add(order4);
        orderSet.add(order5);
        orderSet.add(order6);
        orderSet.add(order7);

        Iterator<Order> iterator = orderSet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        List<Order> list = new ArrayList<Order>();

        list.add(order1);
        list.add(order2);
        list.add(order3);
        list.add(order4);
        list.add(order5);
        list.add(order6);

        orderSet.retainAll(list);

        System.out.println(orderSet);

        while (orderSet.size() != 5) {
            orderSet.remove(orderSet.iterator().next());
        }

        return orderSet;
    }
}
