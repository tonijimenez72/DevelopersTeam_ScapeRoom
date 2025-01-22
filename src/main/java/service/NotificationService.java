package service;

import utils.NotificationObserver;

public interface NotificationService {
    void addObserver(NotificationObserver observer);
    void removeObserver(NotificationObserver observer);
    void notifyObservers(String message);
}