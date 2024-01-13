package factory;

import factory.Account;

public class CheckingAccount implements Account {
    private String accountId;
    private double balance;

    public CheckingAccount(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }
}