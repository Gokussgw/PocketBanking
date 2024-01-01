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
                    System.out.println("Account not found or insufficient funds.");
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
