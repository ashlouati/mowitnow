package com.mowit;

import com.mowit.core.Orientation;
import com.mowit.core.Position;
import com.mowit.dto.MowerInfo;
import com.mowit.dto.MowerInput;
import com.mowit.io.InputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {

    @TempDir
    public Path tmpFolder;

    private static final List<String> FILE_CONTENT = Arrays.asList(
            "5 5",
            "1 2 N",
            "GAGAGAGAA",
            "3 3 E",
            "AADAADADDA"
    );

    private static final List<String> FILE_CONTENT_MISSING_DATA = Arrays.asList(
            "5 5",
            "1 2 N",
            "GAGAGAGAA",
            "3 3 E"
    );

    @Test
    void testParseFromPath() throws IOException {
        Path filePath = tmpFolder.resolve("commands.txt");
        Files.write(filePath, FILE_CONTENT);
        MowerInput inputDTO = InputParser.parseFromPath(filePath.toString());

        // Assert
        assertNotNull(inputDTO);

        assertEquals(new Position(5, 5), inputDTO.maxPosition());

        assertEquals(2, inputDTO.mowerInfoList().size());

        assertEquals(new MowerInfo(1, 2, Orientation.N, "GAGAGAGAA"), inputDTO.mowerInfoList().get(0));
        assertEquals(new MowerInfo(3, 3, Orientation.E, "AADAADADDA"), inputDTO.mowerInfoList().get(1));
    }

    @Test
    void parseFromPath_invalidInputFile_shouldThrowIOException() {
        String fileName = "nonexistentFile.txt";

        //Assert
        assertThrows(IOException.class, () -> InputParser.parseFromPath(fileName));
    }

    @Test
    void parseFromPath_missingData_shouldThrowIllegalArgumentException() throws IOException {
        Path filePath = tmpFolder.resolve("commands.txt");
        Files.write(filePath, FILE_CONTENT_MISSING_DATA);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseFromPath(filePath.toString()));
    }
}
