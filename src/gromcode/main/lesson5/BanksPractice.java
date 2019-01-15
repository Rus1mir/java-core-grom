package gromcode.main.lesson5;

import java.util.Arrays;

public class BanksPractice {
    public static void main(String[] args) {
        String[] names = {"Jack", "Ann", "Denis", "Andrey", "Nikolay", "Irina", "John"};
        int[] balances = {100, 500, 8432, -99, 1200, -54, 0};
        System.out.println(Arrays.toString(findClientByBalance(names, balances, -100)));
        System.out.println(Arrays.toString(findClientWithNegativeBalance(names, balances)));
    }

    public static String[] findClientByBalance(String[] clients, int[] balances, int n) {
        int count = 0;

        for (int balance : balances) {
            if (balance > n)
                count++;
        }

        String[] results = new String[count];

        int index = 0;
        int resIndex = 0;
        for (int balance : balances) {
            if (balance > n) {
                results[resIndex] = clients[index];
                resIndex++;
            }
            index++;
        }
        return results;
    }
    public static String[] findClientWithNegativeBalance(String[] clients, int[] balances) {
        int count = 0;

        for (int balance : balances) {
            if (balance < 0)
                count++;
        }

        String[] results = new String[count];

        int index = 0;
        int resIndex = 0;
        for (int balance : balances) {
            if (balance < 0) {
                results[resIndex] = clients[index];
                resIndex++;
            }
            index++;
        }
        return results;
    }

    public static void depositMoney(String[] clients, int[] balances, String client, int money) {
        int elementIndex = 0;
        for (String cl : clients ) {
            if(cl == client) {
                break;
            }
            elementIndex++;

            int moneyToDeposit;
        }
    }
}
