package service;

import enums.Theme;
import exception.InvalidEntityDataException;
import model.Clue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.impl.ClueServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class ClueServiceTest {
    private ClueServiceImpl clueService;

    @BeforeEach
    void setUp() {
        clueService = new ClueServiceImpl();
    }

    @Test
    void createClueTest_IsOK() {
        String name = "Test clue";
        double price = 20.0;
        Theme theme = Theme.MISTERY;
        int roomId = 1;

        assertDoesNotThrow(() -> clueService.create(name, price, theme, roomId));
    }

    @Test
    void createClueTest_InvalidIfPriceIsLessThanZero() {
        String name = "Test clue";
        double price = -20.0;
        Theme theme = Theme.MISTERY;
        int roomId = 1;

        assertThrows(InvalidEntityDataException.class, () -> clueService.create(name, price, theme, roomId));
    }

    @Test
    void createClueTest_InvalidIfEmptyName() {
        String name = "";
        double price = 20.0;
        Theme theme = Theme.MISTERY;
        int roomId = 1;

        assertThrows(InvalidEntityDataException.class, () -> clueService.create(name, price, theme, roomId));
    }
}
