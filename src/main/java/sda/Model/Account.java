package sda.Model;
import java.sql.*;

public class Account {
    private int customerId;
    private String password;

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/sda_prototype"; // Replace with your database URL
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = ""; // Replace with your database password

    public Account()
    {

    }
    public boolean authenticate(int customerId, String password) {
        this.customerId = customerId;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT password_hash FROM account WHERE customer_id = ?")) {
            
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.password = resultSet.getString("password_hash");
                return this.password.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors appropriately
        }
        return false;
    }

    public int getCustomerId()
    {
        return customerId;
    }
}
