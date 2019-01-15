package gromcode.main.lesson26.lesson26hw;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest {
    public static void main(String[] args) {
        System.out.println(useList().toString());
    }

    private static ArrayList<Order> useList() {
        Order order1 = new Order(100, 200, "USD", "Mirror", "A12G3");
        Order order2 = new Order(101, 500, "USD", "Boiler", "A12G3");
        Order order3 = new Order(102, 250, "USD", "Kettle", "A4E45");
        Order order4 = new Order(103, 400, "USD", "Fan", "F1G3");
        Order order5 = new Order(104, 100, "USD", "MP3", "H12H31");
        Order order6 = new Order(105, 930, "USD", "Freezer", "V12S5");
        Order order7 = new Order(104, 100, "USD", "MP3", "H12H31");
        Order order8 = new Order(105, 930, "USD", "Freezer", "V12S5");

        ArrayList<Order> list = new ArrayList<>();

        list.add(order2);
        list.add(order3);
        list.add(order4);
        list.add(order5);
        list.add(order6);
        list.add(order7);

        list.add(0, order1);
        System.out.println(list.toString());

        list.remove(6);
        System.out.println(list.toString());

        list.remove(order1);
        System.out.println(list.toString());

        ArrayList<Order> list1 = new ArrayList<>();
        list1.add(order1);
        list1.add(order8);
        list.addAll(list1);
        System.out.println(list.toString());

        ArrayList<Order> list2 = new ArrayList<>(list.subList(3, 7));
        System.out.println(list2.toString());

        list.remove(order1);
        System.out.println(list.contains(order1));
        list.set(0, order1);
        System.out.println(list.contains(order1));

        Order[] orders = list.toArray(new Order[0]);
        System.out.println(Arrays.deepToString(orders));

        list2.clear();
        System.out.println(list2.toString());

        while(list.size() != 5) {
            list.remove(0);
        }

        return list;
    }
}

