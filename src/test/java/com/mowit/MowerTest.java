package com.mowit;

import com.mowit.core.Lawn;
import com.mowit.core.Mower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MowerTest {

    @Test
    public void testMowerSingleMove() {
        Lawn lawn = new Lawn(5, 5);

        Mower mower = new Mower(lawn);
        lawn.addMower(mower, 1, 2, 'N');

        mower.move("GAGAGAGAA");
        assertEquals("1 3 N", mower.getPosition());
    }

    @Test
    public void testMultipleMowers() {
        Lawn lawn = new Lawn(5, 5);

        Mower mower1 = new Mower(lawn);
        lawn.addMower(mower1, 1, 2, 'N');
        mower1.move("GAGAGAGAA");

        Mower mower2 = new Mower(lawn);
        lawn.addMower(mower2, 3, 3, 'E');
        mower2.move("AADAADADDA");

        assertEquals("1 3 N", mower1.getPosition());
        assertEquals("5 1 E", mower2.getPosition());
    }

    @Test
    public void testMowerDoesNotGoOutOfBounds() {
        Lawn lawn = new Lawn(5, 5);

        Mower mower = new Mower(lawn);
        lawn.addMower(mower, 4, 4, 'N');
        mower.move("AAAAA");  // Try to move outside the lawn

        assertEquals("4 5 N", mower.getPosition());  // Should not move beyond the lawn boundaries
    }

    @Test
    void testMowerCollision() {
        Lawn lawn = new Lawn(5, 5);

        // Deploy the first mower
        Mower mower1 = new Mower(lawn);
        lawn.addMower(mower1, 1, 1, 'N');

        // Deploy the second mower
        Mower mower2 = new Mower(lawn);
        lawn.addMower(mower2, 2, 1, 'W');

        // Move the second mower to the left, but it should stay at the initial position due to collision
        mower2.move("A");

        // Check the final positions of both mowers
        assertEquals("1 1 N", mower1.getPosition());
        assertEquals("2 1 W", mower2.getPosition());
    }

    @Test

    void testMultipleMowersSamePosition() {
        Lawn lawn = new Lawn(5, 5);

        // Deploy the first mower
        Mower mower1 = new Mower(lawn);
        lawn.addMower(mower1, 1, 1, 'N');

        // Deploy the second mower attempting to occupy the same position
        Mower mower2 = new Mower(lawn);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> lawn.addMower(mower2, 1, 1, 'N'));
    }
}
