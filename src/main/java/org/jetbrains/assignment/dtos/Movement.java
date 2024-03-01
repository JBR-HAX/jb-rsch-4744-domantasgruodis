package org.jetbrains.assignment.dtos;

import lombok.Builder;

@Builder
public record Movement(Direction direction, Integer steps) {
}
