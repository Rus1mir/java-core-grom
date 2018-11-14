package lesson20.task2;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
        //testSaveNormal();
        //testSaveBigSimpleAmount();
        //testSaveBigGroupAmount();
        //testSaveNoSpace();
        //testSaveWrongCity();
        testSaveAlreadyExist();
    }

    private static void testSaveNormal() throws Exception {
        Controller controller = new Controller();
        Transaction transaction = new Transaction(10, "Kiev", 10,
                "some", TransactionType.INCOME, new Date());
        controller.save(transaction);
        System.out.println(Arrays.deepToString(controller.transactionList()));
    }

    private static void testSaveBigSimpleAmount() throws Exception {
        Controller controller = new Controller();
        Transaction transaction = new Transaction(10, "Kiev", 50,
                "some", TransactionType.INCOME, new Date());
        controller.save(transaction);
        System.out.println(Arrays.deepToString(controller.transactionList()));
    }

    private static void testSaveBigGroupAmount() throws Exception {
        Controller controller = new Controller();
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.deepToString(controller.transactionList()));
            Transaction transaction = new Transaction(10 + i, "Kiev", 40,
                    "some", TransactionType.INCOME, new Date());
            controller.save(transaction);
        }
    }

    private static void testSaveNoSpace() throws Exception {
        Controller controller = new Controller();
        for (int i = 0; i < 11; i++) {
            System.out.println(Arrays.deepToString(controller.transactionList()));
            Transaction transaction = new Transaction(10 + i, "Kiev", 3,
                    "some", TransactionType.INCOME, new Date());
            controller.save(transaction);
        }
    }

    private static void testSaveWrongCity() throws Exception {
        Controller controller = new Controller();
        Transaction transaction = new Transaction(10, "Kiev", 3,
                "some", TransactionType.INCOME, new Date());
        controller.save(transaction);
        System.out.println(Arrays.deepToString(controller.transactionList()));
    }

    private static void testSaveAlreadyExist() throws Exception {
        Controller controller = new Controller();
        Transaction transaction = new Transaction(10, "Kiev", 3,
                "some", TransactionType.INCOME, new Date());
        controller.save(transaction);
        System.out.println(Arrays.deepToString(controller.transactionList()));
        controller.save(transaction);
    }
}
