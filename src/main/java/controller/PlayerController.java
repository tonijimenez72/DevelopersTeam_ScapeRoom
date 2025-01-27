package controller;

import model.Player;
import service.PlayerService;

import java.util.List;

public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void createPlayer(Player player) {
        try {
            playerService.createPlayer(player);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating player: " + e.getMessage());
        }
    }

    public Player getPlayerById(int id) {
        try {
            return playerService.getPlayerById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void showAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("No players available.");
        } else {
            players.forEach(System.out::println);
        }
    }

    public void addSubscription(int playerId) {
        try {
            playerService.addSubscription(playerId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error subscribing player: " + e.getMessage());
        }
    }

    public void deleteSubscription(int playerId) {
        try {
            playerService.deleteSubscription(playerId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error unsubscribing player: " + e.getMessage());
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

    public void deletePlayer(int playerId) {
        try {
            playerService.deletePlayer(playerId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting player: " + e.getMessage());
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