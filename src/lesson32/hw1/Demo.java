package lesson32.hw1;

import lesson32.hw2.Solution;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        ReadFromKeyboard read = new ReadFromKeyboard();
        read.readKeyboardWithScanner();
        read.readKeyboardWithIOStream();
    }
}
