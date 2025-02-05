package controller;

import enums.*;
import exception.GlobalExceptionHandler;
import model.Room;
import service.RoomService;

import java.util.List;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void add(String name, double price, Theme theme, DifficultyLevel difficultyLevel) {
        try {
            roomService.create(name, price, theme, difficultyLevel);
            System.out.println("Room added successfully.");
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showAll() {
        try {
            List<Room> rooms = roomService.getAll();
            if (rooms.isEmpty()) {
                System.out.println(" None.");
            } else {
                rooms.forEach(System.out::println);
            }
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void getById(int id) {
        try {
            System.out.println(roomService.getById(id));
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void delete(int id) {
        try {
            roomService.delete(id);
            System.out.println("Room removed successfully.");
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public void showStockInfo(){
        try {
            roomService.showStockInfo();
        } catch (IllegalArgumentException e) {
            System.out.println("Error printing stock info: " + e.getMessage());
        }
    }
}