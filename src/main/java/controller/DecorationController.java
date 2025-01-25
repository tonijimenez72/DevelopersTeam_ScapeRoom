package controller;

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
            System.out.println("No decorations available.");
        } else {
            decorations.forEach(System.out::println);
        }
    }

    public void updateDecorationAvailability(Decoration decoration, boolean available) {
        try {
            decorationService.updateDecorationAvailability(decoration, available);
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating decoration availability: " + e.getMessage());
        }
    }

    public void removeDecoration(Decoration decoration) {
        try {
            decorationService.removeDecoration(decoration);
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing decoration: " + e.getMessage());
        }
    }
}