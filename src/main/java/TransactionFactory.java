public class TransactionFactory {
    public Transaction createTransaction(String type) {
        if (type.equalsIgnoreCase("Deposit")) {
            return new DepositTransaction();
        } else if (type.equalsIgnoreCase("Withdrawal")) {
            return new WithdrawalTransaction();
        } else if (type.equalsIgnoreCase("Transfer")) {
            return new TransferTransaction();
        }
        throw new IllegalArgumentException("Unknown transaction type");
    }
}

// Example transaction types
class DepositTransaction extends Transaction { /* ... */ }
class WithdrawalTransaction extends Transaction { /* ... */ }
class TransferTransaction extends Transaction { /* ... */ }
