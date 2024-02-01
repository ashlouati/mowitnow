package com.mowit;

import com.mowit.core.Lawn;
import com.mowit.core.Mower;
import com.mowit.core.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LawnTest {

    @Test
    public void testAddMowerValidPosition() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn);

        lawn.addMower(mower, 2, 3, 'N');

        assertEquals(new Position(2, 3), mower.getCurrentPosition());
    }

    @Test
    public void testAddMowerInvalidPosition() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn);

        assertThrows(IllegalArgumentException.class, () -> lawn.addMower(mower, 6, 3, 'N'));
    }

    @Test
    public void testIsValidPosition() {
        Lawn lawn = new Lawn(5, 5);

        assertTrue(lawn.isValidPosition(new Position(2, 3)));
        assertTrue(lawn.isValidPosition(new Position(0, 0)));
        assertFalse(lawn.isValidPosition(new Position(6, 3)));
        assertFalse(lawn.isValidPosition(new Position(2, 6)));
    }

    @Test
    public void testIsPositionAvailable() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower1 = new Mower(lawn);
        Mower mower2 = new Mower(lawn);

        lawn.addMower(mower1, 2, 3, 'N');

        assertTrue(lawn.isPositionAvailable(new Position(2, 4)));
        assertFalse(lawn.isPositionAvailable(new Position(2, 3)));
        assertTrue(lawn.isPositionAvailable(new Position(4, 4)));

        lawn.addMower(mower2, 4, 4, 'E');

        assertFalse(lawn.isPositionAvailable(new Position(4, 4)));
    }
}
