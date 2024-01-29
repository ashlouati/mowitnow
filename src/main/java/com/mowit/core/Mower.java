package com.mowit.core;

public class Mower implements Movable {
    private int x;
    private int y;
    private Orientation orientation;
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
            case N -> newY++;
            case E -> newX++;
            case S -> newY--;
            case W -> newX--;
        }

        if (isValidPosition(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    @Override
    public void rotateRight() {
        orientation = orientation.rotateRight();
    }

    @Override
    public void rotateLeft() {
        orientation = orientation.rotateLeft();
    }

    public void deploy(int x, int y, char orientation) {
        if (isValidPosition(x, y)) {
            this.x = x;
            this.y = y;
            this.orientation = Orientation.fromChar(orientation);
        } else {
            throw new IllegalArgumentException("Invalid initial position for mower");
        }
    }

    public void move(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (Command.fromChar(instruction)) {
                case D -> rotateRight();
                case G -> rotateLeft();
                case A -> advance();
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
