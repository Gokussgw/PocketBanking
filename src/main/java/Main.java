import factory.Account;
import factory.CheckingAccountFactory;
import factory.SavingsAccountFactory;
import observer.AccountDAO;
import observer.BankingService;
import observer.Customer;
import observer.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AccountDAO accountDAO = new AccountDAO();
        BankingService bankingService = new BankingService();

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Savings Factory.Account");
            System.out.println("2. Create Checking Factory.Account");
            System.out.println("3. Notify Customers");
            System.out.println("4. View Accounts (Placeholder)");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Create New observer.Customer");
            System.out.println("8. Make a transaction");
            System.out.println("9. View transaction history");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                case 2:
                    handleAccountCreation(scanner, accountDAO, choice);
                    break;
                case 3:
                    bankingService.notifyAllCustomers();
                    System.out.println("All customers have been notified.");
                    break;
                case 4:
                    System.out.println("Viewing accounts (functionality not implemented yet).");
                    break;
                case 5:
                    handleDeposit(scanner, accountDAO);
                    break;
                case 6:
                    handleWithdraw(scanner, accountDAO);
                    break;
                case 7:
                    handleNewCustomer(scanner, accountDAO);
                    break;
                case 8:
                    System.out.print("Enter Source observer.Customer ID: ");
                    int fromCustomerId = scanner.nextInt();
                    System.out.print("Enter Source Factory.Account Type (SAVINGS/CHECKING): ");
                    String fromAccountType = scanner.next().toUpperCase();

                    System.out.print("Enter Target observer.Customer ID: ");
                    int toCustomerId = scanner.nextInt();
                    System.out.print("Enter Target Factory.Account Type (SAVINGS/CHECKING): ");
                    String toAccountType = scanner.next().toUpperCase();

                    System.out.print("Enter Amount to Transfer: ");
                    double amount = scanner.nextDouble();

                    accountDAO.transferFunds(fromCustomerId, fromAccountType, toCustomerId, toAccountType, amount);
                    System.out.println("Transferred " + amount + " from " + fromAccountType + " account of customer ID " + fromCustomerId + " to " + toAccountType + " account of customer ID " + toCustomerId);
                    break;
                case 9:
                    System.out.print("Enter observer.Customer ID for observer.Transaction History: ");
                    int customerId = scanner.nextInt();
                    List<Transaction> transactions = accountDAO.getTransactionHistory(customerId);
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found for customer ID " + customerId);
                    } else for (Transaction transaction : transactions) transaction.print();
                    break;
                case 10:
                    System.out.println("Exiting Banking System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleAccountCreation(Scanner scanner, AccountDAO accountDAO, int accountTypeChoice) {
        System.out.print("Enter observer.Customer ID: ");
        int customerId = scanner.nextInt();

        Customer customer = accountDAO.getCustomerDetails(customerId);
        if (customer == null) {
            System.out.println("observer.Customer not found!");
            return;
        }

        Account account = accountTypeChoice == 1
                ? new SavingsAccountFactory().createAccount(Integer.toString(customerId))
                : new CheckingAccountFactory().createAccount(Integer.toString(customerId));

        accountDAO.createAccount(account);
        String accountType = accountTypeChoice == 1 ? "Savings" : "Checking";
        System.out.println("Created " + accountType + " Factory.Account for " + customer.getName() + " (" + customer.getEmail() + ")");
    }

    private static void handleDeposit(Scanner scanner, AccountDAO accountDAO) {
        System.out.print("Enter Factory.Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        accountDAO.deposit(accountId, amount);
        System.out.println("Deposited " + amount + " to account ID " + accountId);
    }

    private static void handleWithdraw(Scanner scanner, AccountDAO accountDAO) {
        System.out.print("Enter Factory.Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        accountDAO.withdraw(accountId, amount);
        System.out.println("Withdrew " + amount + " from account ID " + accountId);
    }

    private static void handleNewCustomer(Scanner scanner, AccountDAO accountDAO) {
        scanner.nextLine(); // Consume the leftover newline
        System.out.print("Enter observer.Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter observer.Customer Email: ");
        String email = scanner.nextLine();
        accountDAO.createCustomer(name, email);
        System.out.println("New customer created: " + name);
    }
}
