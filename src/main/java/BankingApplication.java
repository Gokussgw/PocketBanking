import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingService service = new BankingService(); // This will handle the logic

        while (true) {
            System.out.println("\n1. View Transaction History");
            System.out.println("2. Transfer Funds");
            System.out.println("3. View Account Details");
            System.out.println("4. View Account Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    service.viewTransactionHistory();
                    break;
                case 2:
                    service.transferFunds();
                    break;
                case 3:
                    service.viewAccountDetails();
                    break;
                case 4:
                    service.viewAccountBalance();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
