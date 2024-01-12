package observer;

import database.DatabaseManager;
import factory.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer implements Observer {
    private int customerId;
    private String name;
    private String email;

    // Constructor used when only the name is known
    public Customer(String name) {
        this.name = name;
    }

    // Constructor used when all details are known
    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    // Observer.Observer update method
    @Override
    public void update(String message) {
        System.out.println("Hello " + name + ", " + message);
    }

    // Create an account for this customer
    public void createAccount(Account account) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, this.customerId);
                statement.setString(2, account.getAccountType());
                statement.setDouble(3, account.getBalance());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    // Retrieve a customer from the database
    public static Customer getCustomer(int customerId) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT id, name, email FROM customers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
        return null; // Return null if customer is not found
    }

    // Getters for customerId, name, and email
    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
