package sda_old.Controller;
import java.sql.*;
import java.util.Scanner;

import sda_old.Model.Account;

public class BankingOptions {
    private Account model = new Account();
    private Scanner scanner;
    

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/sda_prototype"; // Replace with your database URL
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = ""; // Replace with your database password

    public BankingOptions(Account model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
    }

    public void showOptions() {
        while (true) {
            System.out.println("\n1. View Transaction History");
            System.out.println("2. Transfer Funds");
            System.out.println("3. View Account Details");
            System.out.println("4. View Account Balance");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    transferFunds();
                    break;
                case 3:
                    viewAccountDetails();
                    break;
                case 4:
                    viewAccountBalance();
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Methods for viewTransactionHistory, transferFunds, viewAccountDetails...

    // Tranfer Funds
    private void transferFunds() {
        System.out.print("Enter target account ID: ");
        int targetAccountId = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        int amount = scanner.nextInt();
    
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            connection.setAutoCommit(false);  // Start transaction
    
            // Check if source account has enough balance
            try (PreparedStatement checkBalanceStmt = connection.prepareStatement("SELECT balance FROM account WHERE account_id = ?")) {
                checkBalanceStmt.setInt(1, model.getCustomerId());
                System.out.println("Checking balance for account ID: " + model.getCustomerId());  // Debug print
            
                ResultSet resultSet = checkBalanceStmt.executeQuery();
            
                if (resultSet.next()) {
                    int balance = resultSet.getInt("balance");
                    System.out.println("Current balance: " + balance);  // Debug print
                    if (balance < amount) {
                        System.out.println("Insufficient balance in the account.");
                        return;
                    }
                } else {
                    System.out.println("Account not found.");
                    return;
                }
            }
    
            // Deduct amount from source account
            try (PreparedStatement deductStmt = connection.prepareStatement("UPDATE account SET balance = balance - ? WHERE account_id = ?")) {
                deductStmt.setInt(1, amount);
                deductStmt.setInt(2, model.getCustomerId());
                deductStmt.executeUpdate();
            }
    
            // Add amount to target account
            try (PreparedStatement addStmt = connection.prepareStatement("UPDATE account SET balance = balance + ? WHERE account_id = ?")) {
                addStmt.setInt(1, amount);
                addStmt.setInt(2, targetAccountId);
                addStmt.executeUpdate();
            }

            recordTransactionHistory(connection, model.getCustomerId(), "withdrawal", amount);
            recordTransactionHistory(connection, targetAccountId, "deposit", amount);
    
            connection.commit();  // Commit transaction
            System.out.println("Transfer completed successfully.");
        } catch (SQLException e) {
            System.out.println("Transfer failed.");
            e.printStackTrace();
        }
    }
    



    // View Account Details
    private void viewAccountDetails() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE account_id = ?")) {
            
            statement.setInt(1, model.getCustomerId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and display account details
                    double balance = resultSet.getInt("balance");
                    // Display other details as required
                    System.out.printf("Account ID: %d, Balance: %.2f\n", model.getCustomerId(), balance);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account details");
            e.printStackTrace();
        }
    }







    // View Transaction History
    private void viewTransactionHistory() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM transaction WHERE account_id = ? ORDER BY transaction_date DESC")) {
            
            statement.setInt(1, model.getCustomerId());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve and display transaction details
                    int transactionId = resultSet.getInt("transaction_id");
                    String type = resultSet.getString("type");
                    double amount = resultSet.getDouble("amount");
                    Timestamp date = resultSet.getTimestamp("transaction_date");
                    System.out.printf("ID: %d, Type: %s, Amount: %.2f, Date: %s\n", transactionId, type, amount, date.toString());
                    
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transaction history");
            e.printStackTrace();
        }
    }
    
    
    
    
    // Record transaction
    private void recordTransactionHistory(Connection connection, int accountId, String type, int amount) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO transaction (account_id, type, amount, transaction_date) VALUES (?, ?, ?, NOW())")) {
            stmt.setInt(1, accountId);
            stmt.setString(2, type);
            stmt.setInt(3, amount);
            stmt.executeUpdate();
        }
    }




    // View Balance
    private void viewAccountBalance() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT balance FROM account WHERE customer_id = ?")) {
            
            statement.setInt(1, model.getCustomerId());
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double balance = resultSet.getDouble("balance");
                    System.out.printf("Account Balance for Customer ID %d: %.2f\n", model.getCustomerId(), balance);
                } else {
                    System.out.println("Account not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account balance");
            e.printStackTrace();
        }
    }
    
    
}


