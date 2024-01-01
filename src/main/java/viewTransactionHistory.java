import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.*;

public void viewTransactionHistory(int accountId) {
    try (Connection connection = (Connection) DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM transaction WHERE account_id = ? ORDER BY transaction_date DESC")) {

        statement.setInt(1, accountId);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve and display transaction details
                int transactionId = resultSet.getInt("transaction_id");
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                Timestamp date = resultSet.getTimestamp("transaction_date");
                System.out.printf("ID: %d, Type: %s, Amount: %.2f, Date: %s\n", transactionId, type, amount, date.toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } catch (SQLException e) {
        System.out.println("Error fetching transaction history");
        e.printStackTrace();
    }
}

public void main() {
}
