package sda;

import sda.Controller.*;
import sda.Model.*;
import sda.View.*;

public class Main {
    public static void main(String[] args) {
        Account model = new Account(); // Initialize account model
        LoginView view = new LoginView();
        LoginController controller = new LoginController(model, view);

        model = controller.getAccount();

        if (controller.processLogin()) {
            BankingOptions bankingOptions = new BankingOptions(model);
            bankingOptions.showOptions();
        }else {
            System.out.println("Login Failed");
        }

        view.close();
    }
}
