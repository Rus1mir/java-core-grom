package lesson27.hw1;

import lesson26.lesson26hw.Order;

import java.util.LinkedList;
import java.util.Arrays;

public class LinkedListTest {
    public static void main(String[] args) {
        System.out.println(useList().toString());
    }

    private static LinkedList<lesson26.lesson26hw.Order> useList() {
        lesson26.lesson26hw.Order order1 = new lesson26.lesson26hw.Order(100, 200, "USD", "Mirror", "A12G3");
        lesson26.lesson26hw.Order order2 = new lesson26.lesson26hw.Order(101, 500, "USD", "Boiler", "A12G3");
        lesson26.lesson26hw.Order order3 = new lesson26.lesson26hw.Order(102, 250, "USD", "Kettle", "A4E45");
        lesson26.lesson26hw.Order order4 = new lesson26.lesson26hw.Order(103, 400, "USD", "Fan", "F1G3");
        lesson26.lesson26hw.Order order5 = new lesson26.lesson26hw.Order(104, 100, "USD", "MP3", "H12H31");
        lesson26.lesson26hw.Order order6 = new lesson26.lesson26hw.Order(105, 930, "USD", "Freezer", "V12S5");
        lesson26.lesson26hw.Order order7 = new lesson26.lesson26hw.Order(104, 100, "USD", "MP3", "H12H31");
        lesson26.lesson26hw.Order order8 = new lesson26.lesson26hw.Order(105, 930, "USD", "Freezer", "V12S5");

        LinkedList<lesson26.lesson26hw.Order> list = new LinkedList<>();

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

        LinkedList<lesson26.lesson26hw.Order> list1 = new LinkedList<>();
        list1.add(order1);
        list1.add(order8);
        list.addAll(list1);
        System.out.println(list.toString());

        LinkedList<lesson26.lesson26hw.Order> list2 = new LinkedList<>(list.subList(3, 7));
        System.out.println(list2.toString());

        list.remove(order1);
        System.out.println(list.contains(order1));
        list.set(0, order1);
        System.out.println(list.contains(order1));

        lesson26.lesson26hw.Order[] orders = list.toArray(new Order[0]);
        System.out.println(Arrays.deepToString(orders));

        list2.clear();
        System.out.println(list2.toString());

        while(list.size() != 5) {
            list.remove(0);
        }

        return list;
    }
}

