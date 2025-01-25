package service;

import model.Player;
import java.util.List;

public interface PlayerService {
    void addPlayer(Player player);
    List<Player> getAllPlayers();
    Player getPlayerByEmail(String email);
    void removePlayer(Player player);
    void addSubscription(Player player);
    void deleteSubscription(Player player);
    List<Player> getSubscribers();
    void showAllSubscribers();
}