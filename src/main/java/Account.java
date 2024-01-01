public interface Account {
    String getAccountId();
    String getAccountType();
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);
}
