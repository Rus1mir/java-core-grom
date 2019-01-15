package gromcode.main.lesson30.task1;

public class Main {
    //Create Main class with main method, where you should create 6 Users:
    // 2 of USBank, 2 of EUBank and 2 of ChinaBank.
    // Run four different operations with every User’s balance and print its objects to console

    public static void main(String[] args) throws Exception {
        Bank usBank = new USBank(2000, "US", Currency.USD, 120, 1600, 12, 2300000);
        Bank euBank = new EUBank(2001, "Sweden", Currency.EUR, 100, 1300, 10, 1210000);
        Bank chinaBank = new ChinaBank(2002, "China", Currency.EUR, 220, 800, 32, 180000);

        User[] users = new User[6];

        users[0] = new User(1000, "John", 12000, 22, "Steam", 1000, usBank);
        users[1] = new User(1001, "Francis", 22000, 32, "Freedom", 1100, usBank);
        users[2] = new User(1002, "Michael", 10000, 55, "KNN", 1300, euBank);
        users[3] = new User(1003, "Jan", 30000, 81, "Mirage", 2000, euBank);
        users[4] = new User(1004, "Lee", 18000, 12, "Dragon", 800, chinaBank);
        users[5] = new User(1005, "Fun", 100000, 42, "Star", 700, chinaBank);

        BankSystem bankSystem = new UkrainianBankSystem();

        for (User user : users) {
            bankSystem.withdraw(user, 100);
            bankSystem.fund(user, 100);
            bankSystem.paySalary(user);
            bankSystem.transferMoney(user, users[0], 5);
            Thread.sleep(1000);
        }
        System.out.println(((UkrainianBankSystem) bankSystem).getTransactions());
    }
}
