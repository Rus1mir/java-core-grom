package lesson16;

import java.util.Arrays;

public class Exercises {
    public static void main(String[] args) {
        String test = "test str here was here two times or not no test";
        System.out.println(deleteDuplicates(test));

        System.out.println(Arrays.toString(countDuplicates(test, new String[]{"test", "here", "not"})));

        System.out.println(replace("Go to school little gentleman, to", " ", "_"));
    }

    public static String deleteDuplicates(String input) {
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j]))
                    words[j] = "";
            }
        }
        String res = "";
        for (String word : words) {
            res += word;
            if (!word.isEmpty())
                res += " ";
        }
        return res;
    }

    public static int[] countDuplicates(String input, String[] words) {
        String[] strings = input.split(" ");
        int[] res = new int[words.length];
        for (String string : strings) {
            for (int i = 0; i < words.length; i++) {
                if (string.equals(words[i]))
                    res[i]++;
            }
        }
        return res;
    }

    public static String replace(String input, String target, String replacement) {
        char[] cInput = input.toCharArray();
        char[] cTarget = target.toCharArray();
        char[] cReplacement = replacement.toCharArray();
        boolean[] map = new boolean[input.length()];
        int counter = 0;
        if (target.length() == 0)
            return input;

        for (int i = 0, n = 0; i < cInput.length; i++) {
            if (cInput[i] == cTarget[n]) {
                n += 1;
                if (n == cTarget.length) {
                    n = 0;
                    map[i] = true;
                    counter += 1;
                }
            } else {
                n = 0;
            }
        }
        if (counter == 0)
            return input;

        char[] rez = new char[cInput.length - cTarget.length * counter + cReplacement.length * counter];
        for (int i = cInput.length - 1, n = rez.length - 1; i >= 0; i--) {
            if (map[i]) {
                for (int j = cReplacement.length - 1; j >= 0; j--) {
                    rez[n] = cReplacement[j];
                    n--;
                }
                i = i - cTarget.length + 1;
            } else {
                rez[n] = cInput[i];
                n--;
            }
        }
        return new String(rez);
    }
}
