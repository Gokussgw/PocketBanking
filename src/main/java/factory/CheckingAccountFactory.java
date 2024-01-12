package factory;

public class CheckingAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(String accountId) {
        return new CheckingAccount(accountId);
    }
}