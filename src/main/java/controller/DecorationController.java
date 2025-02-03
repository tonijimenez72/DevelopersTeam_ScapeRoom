package controller;

import exception.GlobalExceptionHandler;
import model.Decoration;
import service.DecorationService;

import java.util.List;

public class DecorationController {
    private final DecorationService decorationService;

    public DecorationController(DecorationService decorationService) {
        this.decorationService = decorationService;
    }

    public void add(String name, double price, String material, int roomId) {
        try {
            decorationService.create(name, price, material, roomId);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showAll() {
        try {
            List<Decoration> decorations = decorationService.getAll();
            if (!decorations.isEmpty()) {
                decorations.forEach(System.out::println);
            }
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public Decoration getById(int id) {
        try {
            return decorationService.getById(id);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
        return null;
    }

    public void delete(int id) {
        try {
            decorationService.delete(id);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }
}