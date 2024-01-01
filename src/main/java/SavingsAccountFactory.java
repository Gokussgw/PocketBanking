public class SavingsAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(String accountId) {
        return new SavingsAccount(accountId);
    }
}