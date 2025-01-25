package service;

import utils.NotificationObserver;

import java.util.List;

public interface NotificationService {
    void addObserver(NotificationObserver observer);
    void removeObserver(NotificationObserver observer);
    void notifyObservers(String message);
    List<NotificationObserver> getObservers();
}