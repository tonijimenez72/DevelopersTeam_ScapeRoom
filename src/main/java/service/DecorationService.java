package service;

import model.Decoration;

import java.util.List;

public interface DecorationService {
    void addDecoration(Decoration decoration);
    List<Decoration> getAllDecorations();
    Decoration getDecorationById(int id);
    void updateDecorationStatus(int id, boolean available);
    void removeDecoration(int id);
}