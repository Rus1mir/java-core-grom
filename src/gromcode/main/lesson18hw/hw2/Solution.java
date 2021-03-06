package gromcode.main.lesson18hw.hw2;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        String test = "23 33 r 3e 333 2d";
        System.out.println(Arrays.toString(findNumbers(test)));
    }

    //Напишите метод который будет принимать текст на вход и возвращать массив int[] всех чисел которые в нем содержаться.
    //Для каждого слова которое не является целым числом выводите в консоль “not a number” с новой строки.
    //Под слово подразумевается совокупность символом разделенных пробелом НЕ Используйте try-catch конструкцию для решения этой задачи
    //Сигнатура метода: findNumbers(String text) Назание класса - Solution
    private static int[] findNumbers(String text) {
        String[] words = text.split(" ");
        int counter = 0;
        for (String word : words) {
            if (isTotallyNumeric(word))
                counter++;
        }
        int[] res = new int[counter];
        counter = 0;

        for (String word : words) {
            try {
                res[counter] = Integer.parseInt(word);
                counter++;
            } catch (Exception e) {
                System.out.println(word + " not a number");
            }
        }
        return res;
    }

    private static boolean isTotallyNumeric(String word) {
        for (char c : word.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
