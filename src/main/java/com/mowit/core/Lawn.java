package com.mowit.core;

import java.util.HashSet;
import java.util.Set;

public class Lawn {
    private final Position maxPosition;
    private final Set<Position> occupiedPositions = new HashSet<>();

    public Lawn(int maxX, int maxY) {
        this.maxPosition = new Position(maxX, maxY);
    }

    public void addMower(Mower mower, int x, int y, char orientation) {
        Position initialPosition = new Position(x, y);
        if (isValidPosition(initialPosition) && isPositionAvailable(initialPosition)) {
            mower.deploy(x, y, orientation);
            occupiedPositions.add(initialPosition);
        } else {
            throw new IllegalArgumentException("Invalid initial position for mower");
        }
    }

    public void moveMower(Mower mower, Position newPosition) {
        occupiedPositions.remove(mower.getCurrentPosition());
        occupiedPositions.add(newPosition);
    }

    public boolean isValidPosition(Position position) {
        int x = position.x();
        int y = position.y();
        return (x >= 0 && x <= maxPosition.x()) && (y >= 0 && y <= maxPosition.y()) && isPositionAvailable(position);
    }

    public boolean isPositionAvailable(Position position) {
        return !occupiedPositions.contains(position);
    }

}
