package dao;

import model.Player;

import java.util.List;

public interface DaoPlayer {
    void save(Player player);
    List<Player> getAll();
    Player getById(int id);
}