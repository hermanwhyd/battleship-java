package org.scrum.psd.battleship.controller;

import org.scrum.psd.battleship.controller.dto.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GameController {
    public static int gameBoardField = 8;
    public static Ship checkIsHit(Collection<Ship> ships, Position shot) {
        if (ships == null) {
            throw new IllegalArgumentException("ships is null");
        }

        if (shot == null) {
            throw new IllegalArgumentException("shot is null");
        }

        for (Ship ship : ships) {
            for (Position position : ship.getPositions()) {
                if (position.equals(shot)) {
                    position.setHitted();
                    return ship;
                }
            }
        }

        return null;
    }

    public static List<Ship> initializeShips() {
        return Arrays.asList(
//                new Ship("Aircraft Carrier", 5, Color.CADET_BLUE),
//                new Ship("Battleship", 4, Color.RED),
//                new Ship("Submarine", 3, Color.CHARTREUSE),
                new Ship("Destroyer", 3, Color.YELLOW),
                new Ship("Patrol Boat", 2, Color.ORANGE));
    }

    public static boolean isShipValid(Ship ship) {
        Position head = ship.getPositions().get(0);
        Position tail = ship.getPositions().get(ship.getPositions().size() - 1);

        // jika depan & belakang dalam koridor aman
        if (head.isValidPosition() && tail.isValidPosition()) return true;

        return true;
    }

    public static Position getRandomPosition(int size) {
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(size)];
        int number = random.nextInt(size);
        Position position = new Position(letter, number);
        return position;
    }
}
