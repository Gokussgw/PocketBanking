package factory;

import factory.Account;

public interface AccountFactory {
    Account createAccount(String type);
}
