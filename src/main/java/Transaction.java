import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TransactionObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (TransactionObserver observer : observers) {
            observer.update(this);
        }
    }

    // Other transaction-related methods
}
