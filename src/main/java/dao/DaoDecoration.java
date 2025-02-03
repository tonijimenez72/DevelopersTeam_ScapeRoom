package dao;

import model.Decoration;

import java.util.List;

public interface DaoDecoration extends GenericDao<Decoration>{

    void save(Decoration decoration);
    List<Decoration> getAll();
    Decoration getById(int id);
    void remove(Decoration decoration);

}