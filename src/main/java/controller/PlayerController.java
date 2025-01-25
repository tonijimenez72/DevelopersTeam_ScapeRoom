package controller;

import model.Player;
import service.PlayerService;

import java.util.List;

public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void addPlayer(Player player) {
        try {
            playerService.addPlayer(player);
            System.out.println("Player successfully added: " + player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }

    public void showAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("No players registered.");
        } else {
            players.forEach(System.out::println);
        }
    }

    public void removePlayer(Player player) {
        try {
            playerService.removePlayer(player);
            System.out.println("Player removed successfully: " + player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing player: " + e.getMessage());
        }
    }

    public Player getPlayerByEmail(String email) {
        try {
            return playerService.getPlayerByEmail(email);
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving player: " + e.getMessage());
            return null;
        }
    }

    public void subscribeToNotifications(Player player) {
        try {
            if (player.isSubscriber()) {
                System.out.println("Player is already subscribed to notifications: " + player.getName());
                return;
            }
            playerService.addSubscription(player);
            System.out.println("Player subscribed to notifications: " + player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error subscribing player: " + e.getMessage());
        }
    }

    public void unsubscribeFromNotifications(Player player) {
        try {
            playerService.deleteSubscription(player);
            System.out.println("Player unsubscribed from notifications: " + player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error unsubscribing player: " + e.getMessage());
        }
    }

    public void showAllSubscribers() {
        List<Player> subscribers = playerService.getSubscribers();
        if (subscribers.isEmpty()) {
            System.out.println("No players subscribed to notifications.");
        } else {
            System.out.println("List of subscribers:");
            subscribers.forEach(System.out::println);
        }
    }
}