package controller;

import model.Clue;
import model.Decoration;
import service.DecorationService;

import java.util.List;

public class DecorationController {
    private final DecorationService decorationService;

    public DecorationController(DecorationService decorationService) {
        this.decorationService = decorationService;
    }

    public void addDecoration(Decoration decoration) {
        try {
            decorationService.addDecoration(decoration);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding decoration: " + e.getMessage());
        }
    }

    public void showAllDecorations() {
        List<Decoration> decorations = decorationService.getAllDecorations();
        if (decorations.isEmpty()) {
            System.out.println("None.");
        } else {
            decorations.forEach(System.out::println);
        }
    }

    public void showAllAvailableDecorations() {
        List<Decoration> decorations = decorationService.getAvailableDecorations();
        int countAvailables = decorations.size();
        if (decorations.isEmpty()) {
            System.out.println("Available decorations: None.");
        } else {
            System.out.printf("Available decorations: %s%n", countAvailables);
            decorations.forEach(System.out::println);
        }
    }


    public Decoration getDecorationById(int id) {
        try {
            return decorationService.getDecorationById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void updateDecorationStatus(int id, boolean available) {
        try {
            decorationService.updateDecorationStatus(id, available);
            System.out.println("Updated status for decoaration.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating decoration status: " + e.getMessage());
        }
    }

    public void removeDecoration(int id) {
        try {
            decorationService.removeDecoration(id);
            System.out.println("Decoration removed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing decoration: " + e.getMessage());
        }
    }
}