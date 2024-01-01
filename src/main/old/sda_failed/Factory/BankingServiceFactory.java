package sda_failed.Factory;

import sda_failed.Services.*;

public interface BankingServiceFactory {
    LoanService createLoanService();
    AccountService createAccountService();
    InvestmentService createInvestmentService();
}
