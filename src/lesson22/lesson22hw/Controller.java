package lesson22.lesson22hw;

import lesson22.lesson22hw.exception.BadRequestException;
import lesson22.lesson22hw.exception.InternalServerException;

public class Controller {

    public Transaction save(Transaction transaction) throws BadRequestException, InternalServerException {
        return TransactionDAO.save(transaction);
    }

    public Transaction[] transactionList() {
        return TransactionDAO.transactionList();
    }

    public Transaction[] transactionList(String city) {
        return TransactionDAO.transactionList(city);
    }

    public Transaction[] transactionList(int amount) {
        return TransactionDAO.transactionList(amount);
    }
}
