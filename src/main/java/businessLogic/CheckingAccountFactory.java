package businessLogic;

import model.Account;
import model.CheckingAccount;

public class CheckingAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(String accountId) {
        return new CheckingAccount(accountId);
    }
}