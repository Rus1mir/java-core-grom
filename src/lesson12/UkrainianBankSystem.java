package lesson12;

public class UkrainianBankSystem implements BankSystem {
    @Override
    public void withdraw(User user, int amount) {
        //проверить лимит, проверить достаточно ли денег
        // снять деньги
        if (!checkWithdraw(user, amount))
            return;
        user.setBalance(user.getBalance() - amount - user.getBank().getCommission(amount) * amount);
    }

    @Override
    public void fund(User user, int amount) {
        //проверить лимит на пополнение
        // пополнить
        if (!checkFundingLimits(user, amount))
            return;
        user.setBalance(user.getBalance() + amount - user.getBank().getCommission(amount) * amount);
    }

    @Override
    public void transferMoney(User fromUser, User toUser, int amount) {
        //проверить возможность снятия и пополнения
        //снять у отправителя пополнить получателя
        if (!checkFundingLimits(toUser, amount) ||
                !checkWithdraw(fromUser, amount))
            return;
        withdraw(fromUser, amount);
        fund(toUser, amount);
    }

    @Override
    public void paySalary(User user) {
        //проверить возможность пополнения у сотрудника
        //выплатить зарплату пополнением
        if (checkFundingLimits(user, user.getSalary()))
            return;
        fund(user, user.getSalary());
    }

    private boolean checkFundingLimits(User user, int amount) {
        if (amount - user.getBank().getCommission(amount) * amount > user.getBank().getLimitOfFunding()) {
            printFundingErrMsg(amount, user);
            return false;
        }
        return true;
    }

    private boolean checkWithdraw(User user, int amount) {
        return (checkWithdrawLimits(user, amount, user.getBank().getLimitOfWithdrawal()) &&
                checkWithdrawLimits(user, amount, user.getBalance()));
    }

    private boolean checkWithdrawLimits(User user, int amount, double limit) {
        if (amount + user.getBank().getCommission(amount) * amount > limit) {
            printWithdrawalErrMsg(amount, user);
            return false;
        }
        return true;
    }

    private void printWithdrawalErrMsg(int amount, User user) {
        System.err.println("Can't withdraw money " + amount + " from user " + user.toString());
    }

    private void printFundingErrMsg(int amount, User user) {
        System.err.println("Can't deposit money " + amount + " from user " + user.toString());
    }
}
