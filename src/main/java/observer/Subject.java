package observer;

import observer.Observer;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
