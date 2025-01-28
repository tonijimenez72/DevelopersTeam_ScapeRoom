package dao;

import model.Player;

import java.util.List;

public interface DaoPlayer {

    void addPlayer(Player player);
    List<Player> getAllPlayers();
    void updatePlayer(Player player);
    void deletePlayer(int idPlayer);
}
