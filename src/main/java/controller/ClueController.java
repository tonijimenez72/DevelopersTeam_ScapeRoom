package controller;

import enums.Theme;
import exception.GlobalExceptionHandler;
import model.Clue;
import service.ClueService;

import java.util.List;

public class ClueController {
    private final ClueService clueService;

    public ClueController(ClueService clueService) {
        this.clueService = clueService;
    }

    public void add(String name, double price, Theme theme, int roomId) {
        try {
            clueService.create(name, price, theme, roomId);
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showAll() {
        try {
            List<Clue> clues = clueService.getAll();
            if (!clues.isEmpty()) {
                clues.forEach(System.out::println);
            }
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void getById(int id) {
        try {
            System.out.println(clueService.getById(id));
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void delete(int id) {
        try {
            clueService.delete(id);
            System.out.println("Clue removed successfully.");
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }
}