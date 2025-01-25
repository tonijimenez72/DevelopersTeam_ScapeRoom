package service.impl;

import service.NotificationService;
import utils.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(NotificationObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null.");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer added successfully.");
        } else {
            System.out.println("Observer is already subscribed.");
        }
    }

    @Override
    public void removeObserver(NotificationObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null.");
        }
        if (observers.remove(observer)) {
            System.out.println("Observer removed successfully.");
        } else {
            System.out.println("Observer is not subscribed.");
        }
    }

    @Override
    public void notifyObservers(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank.");
        }
        for (NotificationObserver observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public List<NotificationObserver> getObservers() {
        return new ArrayList<>(observers);
    }

}