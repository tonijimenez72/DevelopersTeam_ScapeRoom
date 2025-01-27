package service.impl;

import model.Player;
import service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerServiceImpl implements PlayerService {

    private final List<Player> players = new ArrayList<>();

    @Override
    public void createPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (player.getName() == null || player.getName().isBlank() || player.getEmail() == null || player.getEmail().isBlank()) {
            throw new IllegalArgumentException("Player name and email cannot be null or blank.");
        }

        players.add(player);
        System.out.println("Player created.");
    }

    @Override
    public Player getPlayerById(int id) {
        return players.stream()
                .filter(player -> player.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found."));
    }

    @Override
    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public void addSubscription(int id) {
        Player player = getPlayerById(id);
        if (!player.isSubscriber()) {
            player.setSubscriber(true);
            System.out.println("Player subscribed to notifications.");
        } else {
            System.out.println("Player is already subscribed");
        }
    }

    @Override
    public void deleteSubscription(int id) {
        Player player = getPlayerById(id);
        if (player.isSubscriber()) {
            player.setSubscriber(false);
            System.out.println("Player unsubscribed from notifications.");
        } else {
            System.out.println("Player is not subscribed.");
        }
    }

    @Override
    public List<Player> getAllSubscribers() {
        return players.stream()
                .filter(Player::isSubscriber)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlayer(int id) {
        Player player = getPlayerById(id);
        players.remove(player);
        System.out.println("Player removed.");
    }

    @Override
    public void sendNotification(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Notification message cannot be null or blank.");
        }

        List<Player> subscribers = getAllSubscribers();
        if (subscribers.isEmpty()) {
            System.out.println("No subscribers to notify.");
            return;
        }

        System.out.println("Sending notifications to subscribers:");
        subscribers.forEach(player -> player.update(message));
    }
}