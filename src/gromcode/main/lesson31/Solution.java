package gromcode.main.lesson31;

import java.util.HashMap;

public class Solution {

    public HashMap<Character, Integer> countSymbols(String text) {

        HashMap<Character, Integer> res = new HashMap<>();

        for (Character ch : text.toCharArray()) {
            if (!Character.isLetter(ch))
                continue;

            if (res.containsKey(ch)) {
                int freq = res.get(ch) + 1;
                res.put(ch, freq);
            } else {
                res.put(ch, 1);
            }
        }
        return res;
    }

    public HashMap<String, Integer> words(String text) {

        HashMap<String, Integer> res = new HashMap<>();

        for (String part : text.split(" ")) {
            if (part.length() <= 2 || !isWord(part))
                continue;

            if (res.containsKey(part)) {
                int freq = res.get(part) + 1;
                res.put(part, freq);
            } else {
                res.put(part, 1);
            }
        }
        return res;
    }

    private boolean isWord(String word) {
        for (Character ch : word.toCharArray()) {
            if (!Character.isLetter(ch))
                return false;
        }
        return true;
    }
}
