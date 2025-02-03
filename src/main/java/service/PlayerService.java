package service;

import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Player;

import java.util.List;

public interface PlayerService{
    void create(String name, String email) throws InvalidEntityDataException;
    List<Player> getAll();
    Player getById(int id) throws EntityNotFoundException;
    void delete(int id) throws EntityNotFoundException;
    void addSubscription(int id);
    List<Player> getAllSubscribers();
    void deleteSubscription(int id);
    void sendNotification(String message);
}