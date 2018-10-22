package lesson17HW.HW2;

public class Solution {
    public static void main(String[] args) {
        String test = "   we go to school now we 34 now now we we we";
        System.out.println(minWord(test));
        System.out.println(maxWord(test));
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
}
