package lesson32.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFromKeyboard {

    public void readKeyboardWithScanner() {
        System.out.println("Please, enter your name");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            if (validate(input)) {
                System.out.println("Hello, " + input);
                break;
            } else {
                System.out.println("Input is wrong, please try again.");
            }
        }
    }

    public void readKeyboardWithIOStream() throws IOException {
        System.out.println("Please, enter your name");

        InputStreamReader ISreader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(ISreader);

        while (true) {
            String input = reader.readLine();
            if (validate(input)) {
                System.out.println("Hello, " + input);
                break;
            } else {
                System.out.println("Input is wrong, please try again.");
            }
        }
    }

    private boolean validate(String input) {

        if (input.equals(""))
            return false;

        for (char ch : input.toCharArray()) {
            if (!Character.isLetter(ch))
                return false;
        }
        return true;
    }
}
