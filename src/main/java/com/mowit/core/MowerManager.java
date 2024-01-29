package com.mowit.core;

import java.util.HashMap;
import java.util.Map;

public class MowerManager {
    private final Map<Mower, Position> mowerPositionMap = new HashMap<>();

    public void addMower(Mower mower, Position position) {
        mowerPositionMap.put(mower, position);
    }

    public void moveMower(Mower mower, Position newPosition) {
        mowerPositionMap.put(mower, newPosition);
    }

    public boolean isPositionAvailable(Position position) {
        return !mowerPositionMap.containsValue(position);
    }

}
