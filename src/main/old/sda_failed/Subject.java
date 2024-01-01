package sda_failed.Observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<Observer> observers = new ArrayList<>();

    default void attach(Observer observer) {
        observers.add(observer);
    }

    default void detach(Observer observer) {
        observers.remove(observer);
    }

    void notifyObservers(String message);
}
