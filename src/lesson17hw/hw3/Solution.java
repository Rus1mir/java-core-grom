package lesson17hw.hw3;

public class Solution {
    public static void main(String[] args) {
        String test = "   we go to school now we 34 now now we we we";
        System.out.println(mostCountedWord(test));
    }

    public static int countWords(String input) {
        int counter = 0;
        for (String word : input.split(" ")) {
            if (validateWord(word))
                counter++;
        }
        return counter;
    }

    private static boolean validateWord(String input) {
        if (input.length() <= 0)
            return false;
        for (char symbol : input.toCharArray()) {
            if (!Character.isLetter(symbol)) {
                return false;
            }
        }
        return true;
    }

    //Напишите метод mostCountedWords, который будет искать наиболее повторяемое слово во входящем стринге.
    //Если самое популярное слово повторяется одинаковое количество раз,  можно возвращать любое из них.
    //Если такое слово не вышло найти нет возвращайте null.
    //Название класса - Solution. Сигнатура метода: mostCountedWord(String input).
    public static String mostCountedWord(String input) {
        if (countWords(input) == 0)
            return null;
        String[] temp = input.split(" ");
        int index = 0;
        for (int i = 0, n = 0; i < temp.length; i++) {
            for (int j = i + 1, k = 0; j < temp.length; j++) {
                if (validateWord(temp[i]) &&
                        validateWord(temp[j])) {
                    if (temp[i].equals(temp[j]))
                        k++;
                    if (k > n) {
                        n = k;
                        index = i;
                    }
                }
            }
        }
        return temp[index];
    }
}
