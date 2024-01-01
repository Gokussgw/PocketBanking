import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the BankingService and Account Factories
        BankingService bankingService = new BankingService();
        AccountFactory savingsAccountFactory = new SavingsAccountFactory();
        AccountFactory checkingAccountFactory = new CheckingAccountFactory();

        // Menu loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Notify Customers");
            System.out.println("4. View Accounts");  // Placeholder for actual functionality
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Create Savings Account
                    Account savingsAccount = savingsAccountFactory.createAccount("SAVINGS");
                    System.out.println("Created Savings Account.");
                    break;
                case 2:
                    // Create Checking Account
                    Account checkingAccount = checkingAccountFactory.createAccount("CHECKING");
                    System.out.println("Created Checking Account.");
                    break;
                case 3:
                    // Notify Customers (Observer Pattern Demonstration)
                    bankingService.notifyObservers();
                    break;
                case 4:
                    // View Accounts (Placeholder)
                    System.out.println("Viewing accounts (functionality not implemented).");
                    break;
                case 5:
                    // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Exiting Banking System. Goodbye!");
    }
}
