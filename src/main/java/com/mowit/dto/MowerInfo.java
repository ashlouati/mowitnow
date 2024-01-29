package com.mowit.dto;

import com.mowit.core.Command;
import com.mowit.core.Mower;
import com.mowit.core.Orientation;

import java.util.List;

public record MowerInfo(int x, int y, Orientation orientation, String commands) {
}
