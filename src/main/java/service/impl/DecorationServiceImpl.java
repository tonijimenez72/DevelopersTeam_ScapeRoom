package service.impl;

import dao.Impl.DaoDecorationImpl;
import model.Decoration;
import service.DecorationService;

import java.util.ArrayList;
import java.util.List;

public class DecorationServiceImpl implements DecorationService {


    private final List<Decoration> decorations = new ArrayList<>();
    DaoDecorationImpl daoDecoration= new DaoDecorationImpl();



    @Override
    public void addDecoration(Decoration decoration) {
        if (decoration == null) {
            throw new IllegalArgumentException("Decoration cannot be null.");
        }
        decorations.add(decoration);
        daoDecoration.insertDecoration(decoration);
        System.out.println("Decoration added: " + decoration.getName());
    }

    @Override
    public List<Decoration> getAllDecorations() {

        return daoDecoration.getAllDecorations();

    }

    @Override
    public void updateDecorationAvailability(Decoration decoration, boolean available) {

        decoration.setAvailable(available);
        daoDecoration.updateDecorationAvailability(decoration,available);

        System.out.println("\nDecoration: " + decoration.getName() + "[New status: " + (available ? "available]" : "not available]"));
    }

    @Override
    public void removeDecoration(Decoration decoration) {

        decorations.remove(decoration);
        daoDecoration.deleteDecoration(decoration);

        System.out.println("Decoration removed: " + decoration.getName());
    }
}