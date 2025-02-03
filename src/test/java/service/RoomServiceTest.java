package service;

import enums.DifficultyLevel;
import enums.Theme;
import exception.EntityNotFoundException;
import exception.InvalidEntityDataException;
import model.Room;
import org.junit.jupiter.api.*;
import service.impl.RoomServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {
    private static RoomServiceImpl roomService;

    @BeforeAll
    static void setup() {
        roomService = new RoomServiceImpl();
    }

    @Test
    void testCreateRoomSuccess() {
        assertDoesNotThrow(() -> roomService.create("Test Room", 50.0, Theme.CIFI, DifficultyLevel.HARD));

        List<Room> rooms = roomService.getAll();
        assertFalse(rooms.isEmpty(), "Room list must be not empty.");
        assertEquals("Test Room", rooms.get(0).getName(), "Room names must be equals.");
    }

    @Test
    void testCreateRoomInvalidData() {
        assertThrows(InvalidEntityDataException.class, () -> roomService.create("", 10.0, Theme.CIFI, DifficultyLevel.HARD),
                "Throws InvalidEntityDataException if name is empty.");

        assertThrows(InvalidEntityDataException.class, () -> roomService.create("Test Room", -10.0, Theme.CIFI, DifficultyLevel.MEDIUM),
                "Throws InvalidEntityDataException if price value is less than zero.");
    }

    @Test
    void testGetRoomById() {
        Room room = roomService.getAll().get(0);
        assertDoesNotThrow(() -> {
            Room foundRoom = roomService.getById(room.getId());
            assertNotNull(foundRoom, "Room mus be not null.");
            assertEquals(room.getName(), foundRoom.getName(), "Must be the same names.");
        });
    }

    @Test
    void testGetRoomByInvalidId() {
        assertThrows(EntityNotFoundException.class, () -> roomService.getById(999),
                "Throws EntityNotFoundException if room not exists.");
    }

    @Test
    void testDeleteRoom() {
        Room room = roomService.getAll().get(0);
        assertDoesNotThrow(() -> roomService.delete(room.getId()), "Should not throw exceptions when deleting a room.");

        List<Room> roomsAfterDeletion = roomService.getAll();
        assertFalse(roomsAfterDeletion.contains(room), "Room should not exist after deletion.");

        assertThrows(EntityNotFoundException.class, () -> roomService.getById(room.getId()),
                "Should throw EntityNotFoundException when trying to get a deleted room.");
    }
}
