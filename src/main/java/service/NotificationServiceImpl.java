package service;

import utils.NotificationObserver;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class NotificationServiceImpl implements NotificationService{
    private final List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        observers.stream()
                .filter(observer -> observer instanceof Player && ((Player) observer).isSubscriber())
                .forEach(observer -> observer.update(message));
    }
}