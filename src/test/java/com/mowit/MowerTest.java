package com.mowit;

import com.mowit.core.Mower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    @Test
    public void testMowerSingleMove() {
        Mower mower = new Mower(5, 5);
        mower.deploy(1, 2, 'N');
        mower.move("GAGAGAGAA");
        assertEquals("1 3 N", mower.getPosition());
    }

    @Test
    public void testMultipleMowers() {
        Mower mower1 = new Mower(5, 5);
        mower1.deploy(1, 2, 'N');
        mower1.move("GAGAGAGAA");

        Mower mower2 = new Mower(5, 5);
        mower2.deploy(3, 3, 'E');
        mower2.move("AADAADADDA");

        assertEquals("1 3 N", mower1.getPosition());
        assertEquals("5 1 E", mower2.getPosition());
    }

    @Test
    public void testMowerDoesNotGoOutOfBounds() {
        Mower mower = new Mower(5, 5);
        mower.deploy(4, 4, 'N');
        mower.move("AAAAA");  // Try to move outside the lawn
        assertEquals("4 5 N", mower.getPosition());  // Should not move beyond the lawn boundaries
    }
}
