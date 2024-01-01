import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<AccountObserver> observers = new ArrayList<>();

    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (AccountObserver observer : observers) {
            observer.update(this);
        }
    }

    // Other account-related methods
}
