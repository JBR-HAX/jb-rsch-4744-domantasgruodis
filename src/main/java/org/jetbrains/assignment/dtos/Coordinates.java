package org.jetbrains.assignment.dtos;

import lombok.Builder;

@Builder
public record Coordinates(Integer x, Integer y) {
}
