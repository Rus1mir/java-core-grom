package lesson20.task2;

import lesson20.task2.exception.BadRequestException;
import lesson20.task2.exception.InternalServerException;
import lesson20.task2.exception.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save(Transaction transaction) throws BadRequestException, InternalServerException {
//        сумма транзакции больше указанного лимита
//        сумма транзакций за день больше дневного лимита
//        количество транзакций за день больше указанного лимита
//        если город оплаты (совершения транзакции) не разрешен
//        не хватает места
        validateSave(transaction);

        int index = 0;
        for (Transaction tr : transactions) {
            if (tr == null) {
                transactions[index] = transaction;
                return transactions[index];
            }
            index++;
        }
        throw new InternalServerException("Free space not enough for transaction " +
                transaction.getId() + " Can't be saved");
    }

    public Transaction[] transactionList() {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null)
                count++;
        }

        int index = 0;
        Transaction[] res = new Transaction[count];
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                res[index] = transaction;
                index++;
            }
        }
        return res;
    }

    public Transaction[] transactionList(String city) {

        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && city.equals(transaction.getCity()))
                count++;
        }

        int index = 0;
        Transaction[] res = new Transaction[count];
        for (Transaction transaction : transactions) {
            if (transaction != null && city.equals(transaction.getCity())) {
                res[index] = transaction;
                index++;
            }
        }
        return res;
    }

    public Transaction[] transactionList(int amount) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getAmount() >= amount)
                count++;
        }

        int index = 0;
        Transaction[] res = new Transaction[count];
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getAmount() >= amount) {
                res[index] = transaction;
                index++;
            }
        }
        return res;
    }

    private void validateSave(Transaction transaction) throws BadRequestException {
        validateCity(transaction);

        if (transaction.getAmount() > utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Transaction amount limit exceed " + transaction.getId() + " Can't be saved");

        int sum = 0;
        int count = 0;
        for (Transaction tr : getTransactionsPerDay(transaction.getDateCreated())) {
            sum += tr.getAmount();
            count++;
        }

        if (sum + transaction.getAmount() > utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Transactions amount limit per day exceed " +
                    transaction.getId() + " Can't be saved");

        if (count + 1 > utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Transaction count limit per day count exceed " +
                    transaction.getId() + " Can't be saved");
    }

    private void validateCity(Transaction transaction) throws BadRequestException {
        for (String c : utils.getCities()) {
            if (transaction.getCity().equals(c))
                return;
        }
        throw new BadRequestException("The city of transaction is not valid " +
                transaction.getId() + " Can't be saved");
    }

    private Transaction[] getTransactionsPerDay(Date dateOfCurTransaction) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfCurTransaction);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                calendar.setTime(transaction.getDateCreated());
                int trmonth = calendar.get(calendar.MONTH);
                int trday = calendar.get(calendar.DAY_OF_MONTH);
                if (trmonth == month && trday == day)
                    count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                calendar.setTime(transaction.getDateCreated());
                int trmonth = calendar.get(calendar.MONTH);
                int trday = calendar.get(calendar.DAY_OF_MONTH);
                if (trmonth == month && trday == day) {
                    result[index] = transaction;
                    index++;
                }
            }
        }
        return result;
    }
}
