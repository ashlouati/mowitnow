package com.mowit.dto;

import com.mowit.core.Position;

import java.util.List;

public record MowerInput(Position maxPosition, List<MowerInfo> mowerInfoList) {
}
