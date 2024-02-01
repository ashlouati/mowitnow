package com.mowit.core;

public class Mower implements Movable {
    private Position position;
    private Orientation orientation;
    private final Lawn lawn;

    public Mower(Lawn lawn) {
        this.lawn = lawn;
    }

    @Override
    public void advance() {
        int x = position.x();
        int y = position.y();

        Position newPosition = switch (orientation) {
            case N -> new Position(x, y + 1);
            case E -> new Position(x + 1, y);
            case S -> new Position(x, y - 1);
            case W -> new Position(x - 1, y);
        };
        if (lawn.isValidPosition(newPosition)) {
            lawn.moveMower(this, newPosition);
            this.position = newPosition;
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
        Position pos = new Position(x, y);
        if (lawn.isValidPosition(pos)) {
            this.position = pos;
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
        return String.format("%d %d %s", position.x(), position.y(), orientation);
    }

    public Position getCurrentPosition() {
        return this.position;
    }
}
