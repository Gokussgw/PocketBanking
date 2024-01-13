package dataAccess;

import model.Account;
import model.Customer;
import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public void createAccount(Account account) {
        try (Connection connection = DatabaseManager.getConnection()) {
            // Assuming 'id' is auto-increment, so it's not included in the insert statement
            String sql = "INSERT INTO accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // For now, setting a placeholder for customer_id, update it as per your requirement
                statement.setInt(1, 1); // Placeholder, set the actual customer_id
                statement.setString(2, account.getAccountType());
                statement.setDouble(3, account.getBalance());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In production, use proper logging
        }
    }



    public void transferFunds(int fromCustomerId, String fromAccountType, int toCustomerId, String toAccountType, double amount) throws SQLException {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            connection.setAutoCommit(false); // Start transaction

            int fromAccountId = getAccountId(connection, fromCustomerId, fromAccountType);
            int toAccountId = getAccountId(connection, toCustomerId, toAccountType);

            // Withdraw from the source account
            if (!updateAccountBalance(connection, fromAccountId, -amount)) {
                throw new SQLException("Failed to withdraw from account " + fromAccountId);
            }
            // Record withdrawal transaction
            recordTransaction(connection, fromAccountId, "WITHDRAWAL", amount);

            // Deposit to the target account
            if (!updateAccountBalance(connection, toAccountId, amount)) {
                throw new SQLException("Failed to deposit to account " + toAccountId);
            }
            // Record deposit transaction
            recordTransaction(connection, toAccountId, "DEPOSIT", amount);

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Rollback in case of error
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true); // Reset auto-commit
                connection.close();
            }
        }
    }

    private void recordTransaction(Connection connection, int accountId, String type, double amount) throws SQLException {
        String sql = "INSERT INTO transactions (account_id, type, amount) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            statement.setString(2, type);
            statement.setDouble(3, amount);
            statement.executeUpdate();
        }
    }


    private int getAccountId(Connection connection, int customerId, String accountType) throws SQLException {
        String sql = "SELECT id FROM accounts WHERE customer_id = ? AND account_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.setString(2, accountType);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int accountId = resultSet.getInt("id");
                    System.out.println("Fetched Factory.Account ID: " + accountId + " for model.Customer ID: " + customerId + " and Factory.Account Type: " + accountType);
                    return accountId;
                }
            }
        }
        throw new SQLException("Factory.Account not found for customer: " + customerId + " and type: " + accountType);
    }


    private boolean updateAccountBalance(Connection connection, int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ? AND balance + ? >= 0";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);
            statement.setDouble(3, amount);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }



    public List<Transaction> getTransactionHistory(int customerId) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT t.id, t.account_id, t.type, t.amount, t.transaction_date " +
                    "FROM transactions t " +
                    "INNER JOIN accounts a ON t.account_id = a.id " +
                    "WHERE a.customer_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Transaction transaction = new Transaction(
                                resultSet.getInt("id"),
                                resultSet.getInt("account_id"),
                                resultSet.getString("type"),
                                resultSet.getDouble("amount"),
                                resultSet.getTimestamp("transaction_date")
                        );
                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
        return transactions;
    }

    public Customer getCustomerDetails(int customerId) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT name, email FROM customers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Customer(customerId, resultSet.getString("name"), resultSet.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper logging is recommended in production
        }
        return null; // Return null if customer is not found or an exception occurs
    }
    public Account getAccount(String accountId) {
        // Implement the logic to retrieve an account from the database
        return null;
    }



    public void deposit(int accountId, double amount) {
        updateAccountBalance(accountId, amount);
    }

    public void withdraw(int accountId, double amount) {
        updateAccountBalance(accountId, -amount);
    }

    private void updateAccountBalance(int accountId, double amount) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, amount);
                statement.setInt(2, accountId);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Factory.Account not found or insufficient funds.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    public void createCustomer(String name, String email) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    public void updateAccount(Account account) {
        // Implement the logic to update an account in the database
    }

    public void deleteAccount(String accountId) {
        // Implement the logic to delete an account from the database
    }


    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT id, name, email FROM customers";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
        return customers;
    }

    // Additional methods can be added for more complex operations
}
