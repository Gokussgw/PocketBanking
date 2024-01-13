package businessLogic;

import model.Customer;
import dataAccess.AccountDAO;

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


    public void notifyAllCustomers() {
        List<Customer> customers = new AccountDAO().getAllCustomers();
        for (Customer customer : customers) {
            attach(customer);
        }
        notifyObservers();
        // Detach customers after notification to avoid keeping them in memory
        customers.forEach(this::detach);
    }
}

