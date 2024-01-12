package observer;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int accountId;
    private String type; // Types could be "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    private double amount;
    private Timestamp timestamp;

    public Transaction(int transactionId, int accountId, String type, double amount, Timestamp timestamp) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Getters and potentially setters

    // Other methods if necessary
    public void print() {
        System.out.println("observer.Transaction ID: " + transactionId + ", Factory.Account ID: " + accountId + ", Type: " + type + ", Amount: " + amount + ", Timestamp: " + timestamp);
    }

}
