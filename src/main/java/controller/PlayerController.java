package controller;

import exception.GlobalExceptionHandler;
import model.Player;
import service.PlayerService;
import service.impl.PlayerServiceImpl;

import java.util.List;

public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
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

    public Player getById(int id) {
        try {
            return playerService.getById(id);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
        return null;
    }

    public void delete(int id) {
        try {
            playerService.delete(id);
            System.out.println("Player removed successfully.");
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
            subscribers.forEach(subscriber -> System.out.println(" Name: " + subscriber.getName() + " | Email: " + subscriber.getEmail()));
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
