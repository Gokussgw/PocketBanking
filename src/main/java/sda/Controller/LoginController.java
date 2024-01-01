package sda.Controller;

import sda.Model.Account;
import sda.View.LoginView;

public class LoginController {
    private Account model = new Account();
    private LoginView view;

    public LoginController(Account model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public boolean processLogin() {
        int customerId = view.getCustomerId();
        String password = view.getPassword();

        boolean success = model.authenticate(customerId, password);
        view.showLoginResult(success);
        return success;
    }

    public Account getAccount() {
        return model;
    }
}
