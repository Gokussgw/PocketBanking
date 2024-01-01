public class AccountFactory {
    public Account createAccount(String type) {
        if (type.equalsIgnoreCase("Checking")) {
            return new CheckingAccount();
        } else if (type.equalsIgnoreCase("Savings")) {
            return new SavingsAccount();
        }
        throw new IllegalArgumentException("Unknown account type");
    }
}

// Example account types
class CheckingAccount extends Account { /* Implementation specific to CheckingAccount */ }
class SavingsAccount extends Account { /* Implementation specific to SavingsAccount */ }
