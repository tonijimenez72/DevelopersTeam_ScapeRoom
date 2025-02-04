package dao;

import model.Player;

import java.util.List;

public interface DaoPlayer extends GenericDao<Player> {
    void save(Player player);
    List<Player> getAll();
    Player getById(int id);
    void remove(Player player);
}