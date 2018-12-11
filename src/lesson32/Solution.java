package lesson32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public void readNumbers() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int counter = 3;
        while (true) {
            System.out.println("Input please 10 numbers < 100 in one line and press 'Enter' key");
            String[] input = reader.readLine().split(" ");
            try {
                System.out.println("Sum is = " + parseValidateSum(input));
                counter = 3;
            } catch (Exception e) {
                counter--;
                if (counter <= 0)
                    break;
                System.out.println("Your numbers are wrong. You have " + counter
                        + " attempts to try again");
            }
        }
    }

    private double parseValidateSum(String[] input) throws Exception {
        if (input.length != 10)
            throw new Exception();

        double res = 0;

        for (String part : input) {
            Double val = Double.parseDouble(part);
            if (val > 100)
                throw new Exception();
            res += val;
        }
        return res;
    }
}
