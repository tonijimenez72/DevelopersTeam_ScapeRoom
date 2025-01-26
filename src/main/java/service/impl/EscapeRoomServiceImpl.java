package service.impl;

import dao.Impl.DaoEscapeRoomImpl;
import model.Clue;
import model.Decoration;
import model.EscapeRoom;
import model.Room;
import service.EscapeRoomService;
import dao.Impl.DaoRoomImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EscapeRoomServiceImpl implements EscapeRoomService {

    private String escapeRoomName;
    private List<Room> rooms;
    private List<Clue> clues;
    private List<Decoration> decorations;
    private final DaoEscapeRoomImpl daoEscapeRoom;
    private final DaoRoomImpl daoRoom;

    public EscapeRoomServiceImpl() {
        this.rooms = new ArrayList<>();
        this.clues = new ArrayList<>();
        this.decorations = new ArrayList<>();
        this.daoEscapeRoom = new DaoEscapeRoomImpl();
        this.daoRoom= new DaoRoomImpl();
    }

    @Override
    public void createEscapeRoom(String name, int id) {
        try {
            if (escapeRoomName != null) {
                throw new IllegalStateException("An Escape Room already exists: " + escapeRoomName);
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Escape Room name cannot be null or blank.");
            }
            this.escapeRoomName = name;
            System.out.println("New Escape Room created: " + name);

            EscapeRoom escapeRoom = new EscapeRoom(id,name);

            daoEscapeRoom.addEscapeRoom(escapeRoom);

        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String displayEscapeRoomInventory(boolean onlyAvailable) {
        if (escapeRoomName == null || escapeRoomName.isEmpty()) {
            return "Create an Escape Room first.";
        }
        StringBuilder inventory = new StringBuilder("Escape Room: ").append(escapeRoomName).append("\n");
        appendElementsToInventory(inventory, "Rooms", rooms.stream().filter(room -> !onlyAvailable || room.isAvailable()).toList(), room -> String.format(" * %s [Price: %s] [Theme: %s] [Difficulty: %s] [Available: %s]\n", room.getName(), room.getPrice(), room.getTheme(), room.getDifficultyLevel(), room.isAvailable()));
        appendElementsToInventory(inventory, "Clues", clues.stream().filter(clue -> !onlyAvailable || clue.isAvailable()).toList(), clue -> String.format(" * %s [Price: %s] [Theme: %s] [Available: %s]\n", clue.getName(), clue.getPrice(), clue.getTheme(), clue.isAvailable()));
        appendElementsToInventory(inventory, "Decorations", decorations.stream().filter(decoration ->!onlyAvailable || decoration.isAvailable()).toList(), decoration -> String.format(" * %s [Price: %s] [Material: %s] [Available: %s]\n", decoration.getName(), decoration.getPrice(), decoration.getMaterial(), decoration.isAvailable()));

        return inventory.toString();
    }

    private <T> void appendElementsToInventory(StringBuilder state, String type, List<T> elements, Function<T, String> formatter) {
        state.append(type).append(": \n");
        if (elements.isEmpty()) {
            state.append("No ").append(type.toLowerCase()).append(" available.\n");
        } else {
            elements.forEach(element -> state.append(formatter.apply(element)));
        }
    }

    @Override
    public double calculateInventoryValue() {
        return rooms.stream().mapToDouble(Room::getPrice).sum()
                + clues.stream().mapToDouble(Clue::getPrice).sum()
                + decorations.stream().mapToDouble(Decoration::getPrice).sum();
    }




    public void addRoomToScapeRoom(Room room, int escapeRoomid) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        rooms.add(room);

        daoRoom.addRoomToEscapeRoom(room,escapeRoomid);

        System.out.println("Room added: " + room.getName());
    }


    public List<Room> getRooms(int id_scape_room) {


        return daoRoom.getAllRoomOfTheScapeRoom(id_scape_room);

    }


    public void addClue(Clue clue) {
        if (clue == null) {
            throw new IllegalArgumentException("Clue cannot be null.");
        }
        clues.add(clue);
        System.out.println("Clue added: " + clue.getName());
    }

    public List<Clue> getClues() {
        return new ArrayList<>(clues);
    }

    public void addDecoration(Decoration decoration) {
        if (decoration == null) {
            throw new IllegalArgumentException("Decoration cannot be null.");
        }
        decorations.add(decoration);
        System.out.println("Decoration added: " + decoration.getName());
    }

    public List<Decoration> getDecorations() {
        return new ArrayList<>(decorations);
    }
}