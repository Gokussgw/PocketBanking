public class SavingsAccount implements Account {
    private String accountId;
    private double balance;

    public SavingsAccount(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
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