package controller;

import service.NotificationService;
import utils.NotificationObserver;

import java.util.List;

public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void addObserver(NotificationObserver observer) {
        try {
            notificationService.addObserver(observer);
            System.out.println("Observer added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding observer: " + e.getMessage());
        }
    }

    public void removeObserver(NotificationObserver observer) {
        try {
            notificationService.removeObserver(observer);
            System.out.println("Observer removed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing observer: " + e.getMessage());
        }
    }

    public void notifyObservers(String message) {
        try {
            notificationService.notifyObservers(message);
            System.out.println("Observers notified successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error notifying observers: " + e.getMessage());
        }
    }

    public void showAllObservers() {
        List<NotificationObserver> observers = notificationService.getObservers();
        if (observers.isEmpty()) {
            System.out.println("No observers subscribed.");
        } else {
            System.out.println("Current observers:");
            observers.forEach(observer -> System.out.println(observer.toString()));
        }
    }
}
