import java.util.ArrayList;
import java.util.List;

public class BankingService implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update("Notification from Banking Service");
        }
    }

    // Other banking methods
}
