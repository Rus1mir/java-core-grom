package lesson17HW;

public class Solution {
    public static void main(String[] args) {
        String test = "   we go to school now we 34 now now we we we";
        System.out.println(countWords(test));
        System.out.println(minWord(test));
        System.out.println(maxWord(test));
        System.out.println(mostCountedWord(test));
        test = "https://gromcode.com/lesson5";
        System.out.println(validate(test));
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

    //Напишите методы для поиска самого длинного и самого короткого слова во входящем стринге
    //maxWord(String input)
    //minWord(String input)
    public static String maxWord(String input) {
        if (input.length() == 0) return null;
        String rez = null;
        for (String word : input.split(" ")) {
            if (validateWord(word)) {
                rez = word;
                break;
            }
        }
        if (rez == null) return null;
        for (String word : input.split(" ")) {
            if (validateWord(word) &&
                    (rez.length() < word.length())) {
                rez = word;
            }
        }
        return rez;
    }

    public static String minWord(String input) {
        if (input.length() == 0) return null;
        String rez = null;
        for (String word : input.split(" ")) {
            if (validateWord(word)) {
                rez = word;
                break;
            }
        }
        if (rez == null) return null;
        for (String word : input.split(" ")) {
            if (validateWord(word) &&
                    (rez.length() > word.length())) {
                rez = word;
            }
        }
        return rez;
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

    //Напишите метод для упрощенной валидации интернет адресса. Название класса не важно
    //Для подобных задач на практике используют regex (регулярные выражения), но это задание необходимо решить используя
    //стандартные возможности класса String. Сигнатура метода: validate(String address)
    //Требования: адресс должен начинаться с названия протокола, допустимые - http:// или https://+, www не обязательно+
    //доменная зона должна разделяться точкой, допустимые - com, org, net
    //другие точки в названии адреса а так же спецчимволы не допускаются. Название класса - Solution

    public static boolean validate(String address) {
        String[] parts = address.replace("www.", "").split("://|\\.");
        if (parts.length < 3)
            return false;
        boolean rez = (parts[0].equals("http") || parts[0].equals("https")) &&
                validateStr(parts[1]) &&
                (parts[2].startsWith("com") || parts[2].startsWith("org") || parts[2].startsWith("net"));
        return rez;
    }

    private static boolean validateStr(String input) {
        for (char symb : input.toCharArray()) {
            if (!Character.isDigit(symb) &&
                    !Character.isLetter(symb))
                return false;
        }
        return true;
    }
}
