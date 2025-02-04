package main;

import database.DatabaseConnection;
import exception.EntityNotFoundException;
import menu.MainMenu;


public class Main {
    public static void main(String[] args) throws EntityNotFoundException {
        try {
            DatabaseConnection.getInstance().testConnection();
            DependencyInjector injector = new EscapeRoomDependencyInjector();
            MainMenu mainMenu = injector.initializeApplication();
            mainMenu.run();
        } finally {
            DatabaseConnection.getInstance().closeConnection();
            System.out.println("Connection closed.");
        }
    }
}