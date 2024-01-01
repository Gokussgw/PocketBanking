import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountDAO accountDAO = new AccountDAO();

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                break;
            }

            System.out.print("Enter Customer ID: ");
            int customerId = scanner.nextInt();

            // Retrieve customer details
            Customer customer = accountDAO.getCustomerDetails(customerId);
            if (customer == null) {
                System.out.println("Customer not found!");
                continue;
            }

            Account account;
            switch (choice) {
                case 1:
                    account = new SavingsAccountFactory().createAccount(Integer.toString(customerId));
                    accountDAO.createAccount(account);
                    System.out.println("Created Savings Account for " + customer.getName() + " (" + customer.getEmail() + ")");
                    break;
                case 2:
                    account = new CheckingAccountFactory().createAccount(Integer.toString(customerId));
                    accountDAO.createAccount(account);
                    System.out.println("Created Checking Account for " + customer.getName() + " (" + customer.getEmail() + ")");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }
        }
        scanner.close();
    }
}
