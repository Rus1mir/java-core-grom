package lesson4;

public class Comparator {
    public static void main(String[] args) {
        System.out.println(compareSums(2,20,4,10));
    }

    public static boolean compareSums(int a, int b, int c, int d){
        return (sum(a, b) > sum(c, d));
    }

    public static int sum(int from, int to){
        int sum = 0;
        for (int i = from; i <= to ; i++) {
           sum += i;
        }
        return sum;
    }
}
