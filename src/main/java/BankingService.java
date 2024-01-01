import database.DatabaseConnection;

import java.sql.*;

public class BankingService {

    // View Transaction History
    public void viewTransactionHistory(int accountId) {
        String query = "SELECT * FROM transaction WHERE account_id = ? ORDER BY transaction_date DESC";
        try (Connection connection = (Connection) DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
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

    // Transfer Funds
    public void transferFunds(int sourceAccountId, int targetAccountId, double amount) {
        String checkBalanceQuery = "SELECT balance FROM account WHERE account_id = ?";
        String deductBalanceQuery = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
        String addBalanceQuery = "UPDATE account SET balance = balance + ? WHERE account_id = ?";

        Connection connection = null;
        try {
            connection = (Connection) DatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Check balance
            try (PreparedStatement checkBalanceStmt = connection.prepareStatement(checkBalanceQuery)) {
                checkBalanceStmt.setInt(1, sourceAccountId);
                ResultSet resultSet = checkBalanceStmt.executeQuery();
                if (resultSet.next()) {
                    double balance = resultSet.getDouble("balance");
                    if (balance < amount) {
                        throw new SQLException("Insufficient balance");
                    }
                } else {
                    throw new SQLException("Source account not found");
                }
            }

            // Deduct amount from source account
            try (PreparedStatement deductBalanceStmt = connection.prepareStatement(deductBalanceQuery)) {
                deductBalanceStmt.setDouble(1, amount);
                deductBalanceStmt.setInt(2, sourceAccountId);
                deductBalanceStmt.executeUpdate();
            }

            // Add amount to target account
            try (PreparedStatement addBalanceStmt = connection.prepareStatement(addBalanceQuery)) {
                addBalanceStmt.setDouble(1, amount);
                addBalanceStmt.setInt(2, targetAccountId);
                addBalanceStmt.executeUpdate();
            }

            connection.commit(); // Commit transaction
            System.out.println("Transfer completed successfully.");
        } catch (SQLException e) {
            System.out.println("Transfer failed.");
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    // View Account Details
    public Account viewAccountDetails(int accountId) {
        String query = "SELECT * FROM account WHERE account_id = ?";
        try (Connection connection = (Connection) DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setAccountId(resultSet.getInt("account_id"));
                    account.setCustomerId(resultSet.getInt("customer_id"));
                    account.setAccountType(resultSet.getString("account_type"));
                    account.setBalance(resultSet.getBigDecimal("balance"));
                    account.setCreatedAt(resultSet.getTimestamp("created_at"));
                    account.setPasswordHash(resultSet.getString("password_hash"));
                    return account;
                }
            } catch (SQLException e) {
                System.out.println("Error fetching account details");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    // View Account Balance
    public double viewAccountBalance(int accountId) {
        String query = "SELECT balance FROM account WHERE account_id = ?";
        try (Connection connection = (Connection) DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account balance");
            e.printStackTrace();
        }
        return 0.0;
    }
}
