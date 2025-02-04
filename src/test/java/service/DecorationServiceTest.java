package service;

import exception.InvalidEntityDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.impl.DecorationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DecorationServiceTest {    private DecorationServiceImpl decorationeService;

    @BeforeEach
    void setUp() {
        decorationeService = new DecorationServiceImpl();
    }

    @Test
    void createDecorationTest_IsOK() {
        String name = "Test deco";
        double price = 20.0;
        String material = "deco";
        int roomId = 1;

        assertDoesNotThrow(() -> decorationeService.create(name, price, material, roomId));
    }

    @Test
    void createDecorationTest_InvalidIfPriceIsLessThanZero() {
        String name = "Test deco";
        double price = -20.0;
        String material = "deco";
        int roomId = 1;

        assertThrows(InvalidEntityDataException.class, () -> decorationeService.create(name, price, material, roomId));
    }

    @Test
    void createClueTest_InvalidIfEmptyName() {
        String name = "";
        double price = 20.0;
        String material = "deco";
        int roomId = 1;

        assertThrows(InvalidEntityDataException.class, () -> decorationeService.create(name, price, material, roomId));
    }
}
