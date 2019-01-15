package gromcode.main.lesson8.arithmetic;

public class Arithmetic {
    public boolean check(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int element : array) {
            if (min > element) min = element;
            if (max < element) max = element;
        }
        return (min + max > 100);
    }
}
