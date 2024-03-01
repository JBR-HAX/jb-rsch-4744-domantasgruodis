package org.jetbrains.assignment.services;

import lombok.NoArgsConstructor;
import org.jetbrains.assignment.dtos.Coordinates;
import org.jetbrains.assignment.dtos.Direction;
import org.jetbrains.assignment.dtos.Movement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RobotService {

    public List<Coordinates> getCoordinatesForMoves(List<Movement> moves) {
        // Initializing origin with zero coordinates
        List<Coordinates> coordinates = new ArrayList<>();
        coordinates.add(Coordinates.builder().x(0).y(0).build());

        for(Movement move : moves) {
            Coordinates previousCoordinate = coordinates.get(coordinates.size() - 1);
            Coordinates.CoordinatesBuilder coordinatesBuilder = Coordinates.builder();
            switch (move.direction()) {
                case NORTH:
                    coordinatesBuilder.x(previousCoordinate.x()).y(previousCoordinate.y() + move.steps());
                    break;
                case EAST:
                    coordinatesBuilder.x(previousCoordinate.x() + move.steps()).y(previousCoordinate.y());
                    break;
                case SOUTH:
                    coordinatesBuilder.x(previousCoordinate.x()).y(previousCoordinate.y() - move.steps());
                    break;
                case WEST:
                    coordinatesBuilder.x(previousCoordinate.x() - move.steps()).y(previousCoordinate.y());
            }
            coordinates.add(coordinatesBuilder.build());
        }
        return coordinates;
    }

    public List<Movement> getMovesForCoordinates(List<Coordinates> coordinates) {
        List<Movement> moves = new ArrayList<>();
        for(int i = 0; i < coordinates.size() - 1; i++) {
            Movement.MovementBuilder movementBuilder = Movement.builder();
            Coordinates coordinates1 = coordinates.get(i);
            Coordinates coordinates2 = coordinates.get(i + 1);
            if(!Objects.equals(coordinates1.x(), coordinates2.x())) {
                int steps = coordinates1.x() - coordinates2.x();
                if(steps > 0) {
                    movementBuilder.steps(steps).direction(Direction.EAST);
                } else {
                    movementBuilder.steps(Math.abs(steps)).direction(Direction.WEST);
                }
            } else if(!Objects.equals(coordinates1.y(), coordinates2.y())) {
                int steps = coordinates1.y() - coordinates2.y();
                if(steps > 0) {
                    movementBuilder.steps(steps).direction(Direction.NORTH);
                } else {
                    movementBuilder.steps(Math.abs(steps)).direction(Direction.SOUTH);
                }
            } else {
                movementBuilder.direction(Direction.NONE).steps(0);
            }
            moves.add(movementBuilder.build());
        }
        return moves;
    }
}
