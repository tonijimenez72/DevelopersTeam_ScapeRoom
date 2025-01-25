package service;

import model.Decoration;

import java.util.List;

public interface DecorationService {
    void addDecoration(Decoration decoration);
    List<Decoration> getAllDecorations();
    void updateDecorationAvailability(Decoration decoration, boolean available);
    void removeDecoration(Decoration decoration);
}