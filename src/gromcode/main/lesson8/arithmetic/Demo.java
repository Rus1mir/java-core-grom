package gromcode.main.lesson8.arithmetic;

public class Demo {
    public static void main(String[] args) {
        Adder adder = new Adder();
        int[] array = {12, 33, 44, 43, 95, 99, 12, 1};
        System.out.println(adder.check(array));
        System.out.println(adder.add(12, 2147483647));
    }
}
