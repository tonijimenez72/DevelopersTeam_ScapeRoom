package service;

import model.Player;
import java.util.List;

public interface PlayerService {
    void createPlayer(Player player);
    void addSubscription(int id);
    Player getPlayerById(int id);
    List<Player> getAllPlayers();
    List<Player> getAllSubscribers();
    void deleteSubscription(int id);
    void deletePlayer(int id);
    void sendNotification(String message);
}