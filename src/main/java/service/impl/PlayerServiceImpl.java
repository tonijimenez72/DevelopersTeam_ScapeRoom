package service.impl;

import model.Player;
import model.Room;
import service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private final List<Player> players = new ArrayList<>();

    @Override
    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        players.add(player);
        System.out.println("Player added: " + player.getName());
    }

    @Override
    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public Player getPlayerByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        return players.stream()
                .filter(player -> player.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player with email \"" + email + "\" not found."));
    }

    @Override
    public void removePlayer(Player player) {
        if (!players.remove(player)) {
            throw new IllegalArgumentException("Player \"" + player.getName() + "\" does not exist.");
        }
        System.out.println("Player removed: " + player.getName());
    }

    @Override
    public void addSubscription(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (!player.isSubscriber()) {
            player.setSubscriber(true);
            System.out.println("Player subscribed to notifications: " + player.getName());
        } else {
            System.out.println("Player is already subscribed.");
        }
    }

    @Override
    public void deleteSubscription(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (player.isSubscriber()) {
            player.setSubscriber(false);
            System.out.println("Player unsubscribed from notifications: " + player.getName());
        } else {
            System.out.println("Player is not subscribed.");
        }
    }


    @Override
    public List<Player> getSubscribers() {
        return players.stream()
                .filter(Player::isSubscriber)
                .toList();
    }


    @Override
    public void showAllSubscribers() {
        List<Player> subscribers = players.stream()
                .filter(Player::isSubscriber)
                .toList();
        if (subscribers.isEmpty()) {
            System.out.println("No subscribers found.");
        } else {
            System.out.println("List of Subscribers:");
            subscribers.forEach(subscriber -> System.out.println(" - " + subscriber.getName() + " (" + subscriber.getEmail() + ")"));
        }
    }
}
