package model;

public interface Account {
    String getAccountId();
    String getAccountType(); // here
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);
}
