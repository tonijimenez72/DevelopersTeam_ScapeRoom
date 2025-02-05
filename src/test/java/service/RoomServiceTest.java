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
    void testCreateRoomInvalidData() {
        assertThrows(InvalidEntityDataException.class, () -> roomService.create("", 10.0, Theme.SCIFI, DifficultyLevel.HARD),
                "Throws InvalidEntityDataException if name is empty.");

        assertThrows(InvalidEntityDataException.class, () -> roomService.create("Test Room", -10.0, Theme.SCIFI, DifficultyLevel.MEDIUM),
                "Throws InvalidEntityDataException if price value is less than zero.");
    }

    @Test
    void testGetRoomByInvalidId() {
        assertThrows(EntityNotFoundException.class, () -> roomService.getById(999),
                "Throws EntityNotFoundException if room not exists.");
    }

}
