package lesson17HW.HW1;

public class Solution {
    public static void main(String[] args) {
        String test = "   we go to school now we 34 now now we we we";
        System.out.println(countWords(test));
    }
    //Напишите метод countWords для подсчета слов во входящем стринге.
    // Под словом имеется ввиду текст (без спецсимволов и цифр) разделенный пробелом
    //Название класса - Solution1
    //В данной задачи можно использовать regex, но это не рекомендуется.
    // Простое и правильное решение возможно с помощью стандартных методов
    //Сигнатура метода:
    //int countWords(String input)

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
}
