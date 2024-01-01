public class ConsoleTransactionObserver implements TransactionObserver {
    @Override
    public void update(Transaction transaction) {
        System.out.println("Transaction updated: " + transaction);
    }
}
