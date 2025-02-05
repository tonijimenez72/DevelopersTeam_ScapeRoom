package service.impl;

import dao.DaoEscapeRoom;
import dao.DaoPlayer;
import dao.impl.DaoEscapeRoomImpl;
import dao.impl.DaoPlayerImpl;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.EscapeRoom;
import model.Player;
import service.PlayerService;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerServiceImpl implements PlayerService {
    private final DaoPlayer daoPlayer;
    private final DaoEscapeRoom daoEscapeRoom;

    public PlayerServiceImpl() {
        this.daoPlayer = new DaoPlayerImpl();
        this.daoEscapeRoom = new DaoEscapeRoomImpl();
    }

    @Override
    public void create(String name, String email) throws InvalidEntityDataException {
        List<EscapeRoom> escapeRooms = daoEscapeRoom.getAll();
        if (escapeRooms.isEmpty()) {
            throw new InvalidEntityDataException("Cannot create a player. Must create an Escape Room first.");
        }

        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new InvalidEntityDataException("Invalid player data: Name and Email cannot be empty.");
        }

        int escapeRoomId = escapeRooms.get(0).getId();

        Player player = new Player(name, email, escapeRoomId);
        daoPlayer.save(player);
    }

    @Override
    public List<Player> getAll() {
        return daoPlayer.getAll();
    }

    @Override
    public Player getById(int id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Player ID must be greater than 0.");
        }
        Player player = daoPlayer.getById(id);
        if (player == null) {
            throw new EntityNotFoundException("Player not found for ID: " + id);
        }
        return player;
    }

    @Override
    public void addSubscription(int id) {
        try {
            Player player = getById(id);
            if (!player.isSubscriber()) {
                player.setSubscriber(true);
                daoPlayer.save(player);
                System.out.println("Player subscribed to notifications.");
            } else {
                System.out.println("Player is already subscribed.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteSubscription(int id) {
        try {
            Player player = getById(id);

            if (player.isSubscriber()) {
                player.setSubscriber(false);
                daoPlayer.save(player);
            } else {
                System.out.println("Player is not subscribed.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    public List<Player> getAllSubscribers() {
        List<Player> players = daoPlayer.getAll();

        return players.stream()
                .filter(Player::isSubscriber)
                .collect(Collectors.toList());
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
