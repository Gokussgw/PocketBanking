package sda_failed;

import sda_failed.Observer.*;
import sda_failed.Factory.*;
import sda_failed.Services.*;
import sda_failed.Model.Account;
import sda_old.Controller.BankingOptions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Choose banking type and create services
        System.out.println("Choose banking type (1 for Retail, 2 for Corporate): ");
        int choice = scanner.nextInt();
        //BankingServiceFactory serviceFactory = (choice == 1) ? new RetailBankingServiceFactory() : new CorporateBankingServiceFactory();
//        LoanService loanService = serviceFactory.createLoanService();
//        AccountService accountService = serviceFactory.createAccountService();
//        InvestmentService investmentService = serviceFactory.createInvestmentService();
//
//        // Create an account and attach observers
//        Account account = new Account(); // Assuming Account constructor sets up an account
//        account.attach(new EmailNotification());
//        account.attach(new SMSNotification());
//        account.attach(new AppNotification());

        // Use BankingOptions with the created account
        //BankingOptions bankingOptions = new BankingOptions(account);
        //bankingOptions.showOptions();

        scanner.close();
    }
}
