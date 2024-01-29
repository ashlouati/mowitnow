package com.mowit.core;

public enum Orientation {
    N, E, S, W;

    public Orientation rotateRight() {
        int newIndex = (this.ordinal() + 1) % values().length;
        return values()[newIndex];
    }

    public Orientation rotateLeft() {
        int leftOrdinal = ordinal() - 1;
        int newIndex = leftOrdinal < 0 ? values().length - 1 : leftOrdinal;
        return values()[newIndex];
    }

    public static Orientation fromChar(char orientationChar) {
        String charAsString = String.valueOf(orientationChar).toUpperCase();
        try {
            return valueOf(charAsString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid orientation character: " + orientationChar);
        }
    }
}