package com.mowit.core;

public class Mower implements Movable {
    private int x;
    private int y;
    private char orientation;
    private final int maxX;
    private final int maxY;

    public Mower(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }


    @Override
    public void advance() {
        int newX = x;
        int newY = y;

        switch (orientation) {
            case 'N':
                newY++;
                break;
            case 'E':
                newX++;
                break;
            case 'S':
                newY--;
                break;
            case 'W':
                newX--;
                break;
        }

        if (isValidPosition(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    @Override
    public void rotateRight() {
        switch (orientation) {
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'N';
                break;
        }
    }

    @Override
    public void rotateLeft() {
        switch (orientation) {
            case 'N':
                orientation = 'W';
                break;
            case 'E':
                orientation = 'N';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'W':
                orientation = 'S';
                break;
        }
    }

    public void deploy(int x, int y, char orientation) {
        if (isValidPosition(x, y)) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
        } else {
            throw new IllegalArgumentException("Invalid initial position for mower");
        }
    }

    public void move(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'D':
                    rotateRight();
                    break;
                case 'G':
                    rotateLeft();
                    break;
                case 'A':
                    advance();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }

    public String getPosition() {
        return String.format("%d %d %s", x, y, orientation);
    }

    private boolean isValidPosition(int x, int y) {
        return (x >= 0 && x <= maxX) && (y >= 0 && y <= maxY);
    }
}
