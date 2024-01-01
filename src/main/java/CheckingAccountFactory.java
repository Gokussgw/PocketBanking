public class CheckingAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(String type) {
        return new CheckingAccount();
    }
}
