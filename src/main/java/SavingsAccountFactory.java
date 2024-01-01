public class SavingsAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(String type) {
        return new SavingsAccount();
    }
}

