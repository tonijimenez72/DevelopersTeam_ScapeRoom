package service.impl;

import model.Decoration;
import service.DecorationService;

import java.util.ArrayList;
import java.util.List;

public class DecorationServiceImpl implements DecorationService {
    private final List<Decoration> decorations = new ArrayList<>();

    @Override
    public void addDecoration(Decoration decoration) {
        if (decoration == null) {
            throw new IllegalArgumentException("Decoration cannot be null.");
        }
        decorations.add(decoration);
        System.out.println("Decoration added: " + decoration.getName());
    }

    @Override
    public List<Decoration> getAllDecorations() {
        return new ArrayList<>(decorations);
    }

    @Override
    public void updateDecorationAvailability(Decoration decoration, boolean available) {
        decoration.setAvailable(available);
        System.out.println("\nDecoration: " + decoration.getName() + "[New status: " + (available ? "available]" : "not available]"));
    }

    @Override
    public void removeDecoration(Decoration decoration) {
        decorations.remove(decoration);
        System.out.println("Decoration removed: " + decoration.getName());
    }
}