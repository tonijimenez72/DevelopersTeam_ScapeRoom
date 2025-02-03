package dao;

import model.Clue;

import java.util.List;

public interface DaoClue extends GenericDao<Clue>{

    void save(Clue clue);
    List<Clue> getAll();
    Clue getById(int id);
    void remove(Clue clue);

}