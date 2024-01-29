package com.mowit.core;

public enum Command {
    A, D, G;

    public static Command fromChar(char commandChar) {
        String charAsString = String.valueOf(commandChar).toUpperCase();
        try {
            return valueOf(charAsString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid command character: " + commandChar);
        }
    }
}
