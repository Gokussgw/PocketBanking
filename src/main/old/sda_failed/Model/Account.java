package sda_failed.Model;

import sda_failed.Observer.Subject;
import sda_failed.Observer.Observer;

public class Account implements Subject {
    // existing Account attributes and methods

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // methods to change account state that trigger notifyObservers
}
