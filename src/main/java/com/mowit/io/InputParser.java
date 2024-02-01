package com.mowit.io;

import com.mowit.core.Orientation;
import com.mowit.core.Position;
import com.mowit.dto.MowerInfo;
import com.mowit.dto.MowerInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static MowerInput parseFromPath(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Position maxPosition = parseMaxPosition(reader.readLine());
            List<MowerInfo> mowerInfoList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] mowerInfo = line.split(" ");
                    if (mowerInfo.length != 3) {
                        throw new IllegalArgumentException("Invalid mower information format");
                    }

                    int initialX = Integer.parseInt(mowerInfo[0]);
                    int initialY = Integer.parseInt(mowerInfo[1]);
                    char initialOrientation = mowerInfo[2].charAt(0);
                    Orientation orientation = Orientation.fromChar(initialOrientation);

                    String commands = reader.readLine();
                    if (commands == null) {
                        throw new IllegalArgumentException("Missing mower commands");
                    }

                    mowerInfoList.add(new MowerInfo(initialX, initialY, orientation, commands));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid mower information");
                }
            }
            return new MowerInput(maxPosition, mowerInfoList);
        }
    }

    private static Position parseMaxPosition(String line) {
        try {
            String[] maxPositions = line.split(" ");
            if (maxPositions.length != 2) {
                throw new IllegalArgumentException("Invalid max position format");
            }

            int maxX = Integer.parseInt(maxPositions[0]);
            int maxY = Integer.parseInt(maxPositions[1]);
            return new Position(maxX, maxY);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid max position information");
        }
    }
}