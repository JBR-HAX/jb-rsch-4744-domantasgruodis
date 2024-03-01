package org.jetbrains.assignment.controllers;

import lombok.RequiredArgsConstructor;
import org.jetbrains.assignment.dtos.Coordinates;
import org.jetbrains.assignment.dtos.Movement;
import org.jetbrains.assignment.services.RobotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RobotController.URL)
@RequiredArgsConstructor
public class RobotController {

    public static final String URL = "robot";

    private final RobotService robotService;

    @PostMapping("/coordinates")
    public List<Coordinates> getCoordinates(@RequestBody List<Movement> moves) {
        return robotService.getCoordinatesForMoves(moves);
    }

    @PostMapping("/moves")
    public List<Movement> getMoves(@RequestBody List<Coordinates> coordinates) {
        return robotService.getMovesForCoordinates(coordinates);
    }
}
