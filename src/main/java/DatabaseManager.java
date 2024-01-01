import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USER = "yourUsername"; // Replace with your MySQL username
    private static final String PASSWORD = "yourPassword"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // You can add more methods if needed for handling database operations
}
