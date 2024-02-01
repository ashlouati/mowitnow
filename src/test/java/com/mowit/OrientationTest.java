package com.mowit;

import com.mowit.core.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrientationTest {

    @Test
    public void testRotateRight() {
        assertEquals(Orientation.E, Orientation.N.rotateRight());
        assertEquals(Orientation.S, Orientation.E.rotateRight());
        assertEquals(Orientation.W, Orientation.S.rotateRight());
        assertEquals(Orientation.N, Orientation.W.rotateRight());
    }

    @Test
    public void testRotateLeft() {
        assertEquals(Orientation.W, Orientation.N.rotateLeft());
        assertEquals(Orientation.N, Orientation.E.rotateLeft());
        assertEquals(Orientation.E, Orientation.S.rotateLeft());
        assertEquals(Orientation.S, Orientation.W.rotateLeft());
    }

    @Test
    public void testFromCharValid() {
        assertEquals(Orientation.N, Orientation.fromChar('N'));
        assertEquals(Orientation.E, Orientation.fromChar('E'));
        assertEquals(Orientation.S, Orientation.fromChar('S'));
        assertEquals(Orientation.W, Orientation.fromChar('W'));
    }

    @Test
    public void testFromCharInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Orientation.fromChar('X'));
        assertThrows(IllegalArgumentException.class, () -> Orientation.fromChar('Z'));
    }
}
