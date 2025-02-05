package controller;

import exception.GlobalExceptionHandler;
import model.Player;
import service.PlayerService;
import service.impl.PlayerServiceImpl;

import java.util.List;

public class PlayerController {
    private final PlayerService playerService;

    public PlayerController() {
        this.playerService = new PlayerServiceImpl();
    }

    public void add(String name, String email) {
        try {
            playerService.create(name, email);
            System.out.println("Player added successfully.");
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showAll() {
        try {
            List<Player> players = playerService.getAll();
            if (players.isEmpty()) {
                System.out.println("No players registered.");
            } else {
                players.forEach(System.out::println);
            }
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void getById(int id) {
        try {
            System.out.println(playerService.getById(id));
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void addSubscription(int playerId) {
        try {
            playerService.addSubscription(playerId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error subscribing player: " + e.getMessage());
        }
    }

    public void showAllSubscribers() {
        List<Player> subscribers = playerService.getAllSubscribers();
        if (subscribers.isEmpty()) {
            System.out.println("No subscribers found.");
        } else {
            System.out.println("Subscribers:");
            subscribers.forEach(subscriber -> System.out.printf("Player ID: %s | Name: %s | Email: %s%n", subscriber.getId(), subscriber.getName(), subscriber.getEmail()));
        }
    }

    public void deleteSubscription(int playerId) {
        try {
            playerService.deleteSubscription(playerId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting subscription player: " + e.getMessage());
        }
    }

    public void sendNotification(String message) {
        try {
            playerService.sendNotification(message);
        } catch (IllegalArgumentException e) {
            System.out.println("Error sending notification: " + e.getMessage());
        }
    }
}
