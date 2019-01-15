package gromcode.main.lesson10.abstractbigexample;

public class DeveloperEmployee extends Employee {
    private String[] frameworks;

    @Override
    void paySalary() {
        int newBalance = getBankAccount().getBalance() + getSalaryPerMonth() + 1000;
        getBankAccount().setBalance(newBalance);
    }
}
