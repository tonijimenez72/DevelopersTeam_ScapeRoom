package service.impl;

import dao.Impl.DaoDecorationImpl;
import model.Clue;
import model.Decoration;
import model.Room;
import service.DecorationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DecorationServiceImpl implements DecorationService {
    private final List<Decoration> decorations = new ArrayList<>();
    private final DaoDecorationImpl daoDecoration;

    public DecorationServiceImpl (){

    this.daoDecoration=new DaoDecorationImpl();

    }


    @Override
    public void addDecoration(Decoration decoration) {
        if (decoration == null) {
            throw new IllegalArgumentException("Decoration cannot be null.");
        }
        decorations.add(decoration);
        daoDecoration.insertDecoration(decoration);

    }
    @Override
    public List<Decoration> getAllDecorations() {
        return daoDecoration.getAllDecorations();
    }

    @Override
    public List<Decoration> getAvailableDecorations() {
        return decorations.stream()
                .filter(Decoration::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public Decoration getDecorationById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Clue ID must be greater than 0.");
        }
        return decorations.stream()
                .filter(decoration -> decoration.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Decoration not found for ID: " + id));
    }


    @Override
    public void updateDecorationStatus(int id, boolean available) {
        Decoration decoration = getDecorationById(id);
        decoration.setAvailable(available);
        daoDecoration.updateDecorationAvailability(id,available);
        System.out.printf("Decoration status updated to: %s%n", available ? "available" : "not available");
    }

    @Override
    public void removeDecoration(int id) {
        Decoration decoration = getDecorationById(id);
        decorations.remove(decoration);
        daoDecoration.deleteDecoration(id);
    }
}