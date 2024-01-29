package com.mowit;

import com.mowit.core.Mower;
import com.mowit.core.MowerManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    @Test
    public void testMowerSingleMove() throws IOException {
        MowerManager mowerManager = new MowerManager();
        Mower mower = new Mower(5, 5, mowerManager);
        mower.deploy(1, 2, 'N');
        mower.move("GAGAGAGAA");
        assertEquals("1 3 N", mower.getPosition());
    }

    @Test
    public void testMultipleMowers() {
        MowerManager mowerManager = new MowerManager();
        Mower mower1 = new Mower(5, 5, mowerManager);
        mower1.deploy(1, 2, 'N');
        mower1.move("GAGAGAGAA");

        Mower mower2 = new Mower(5, 5, mowerManager);
        mower2.deploy(3, 3, 'E');
        mower2.move("AADAADADDA");

        assertEquals("1 3 N", mower1.getPosition());
        assertEquals("5 1 E", mower2.getPosition());
    }

    @Test
    public void testMowerDoesNotGoOutOfBounds() {
        MowerManager mowerManager = new MowerManager();
        Mower mower = new Mower(5, 5, mowerManager);
        mower.deploy(4, 4, 'N');
        mower.move("AAAAA");  // Try to move outside the lawn
        assertEquals("4 5 N", mower.getPosition());  // Should not move beyond the lawn boundaries
    }

    @Test
    void testMowerCollision() {
        MowerManager mowerManager = new MowerManager();

        // Deploy the first mower
        Mower mower1 = new Mower(5, 5, mowerManager);
        mower1.deploy(1, 1, 'N');
        mowerManager.addMower(mower1, mower1.getCurrentPosition());

        // Deploy the second mower attempting to occupy the same position
        Mower mower2 = new Mower(5, 5, mowerManager);
        mower2.deploy(2, 1, 'W');
        mowerManager.addMower(mower2, mower1.getCurrentPosition());

        // Move the second mower to the left, but it should stay at the initial position due to collision
        mower2.move("A");

        // Check the final positions of both mowers
        assertEquals("1 1 N", mower1.getPosition());
        assertEquals("2 1 W", mower2.getPosition());
    }
}
