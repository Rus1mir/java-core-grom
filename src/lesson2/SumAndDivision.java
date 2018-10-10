package lesson2;

public class SumAndDivision {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 1000 ; i++) {
            sum += i;
        }
        int div = sum / 1234;
        int mod = sum % 1234;
        System.out.println(mod > div);
    }
}
