package com.mowit.dto;

import com.mowit.core.Orientation;

public record MowerInfo(int x, int y, Orientation orientation, String commands) {
}
