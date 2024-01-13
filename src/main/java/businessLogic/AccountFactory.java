package businessLogic;

import model.Account;

public interface AccountFactory {
    Account createAccount(String type);
}
