package org.scrum.psd.battleship.ascii;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.scrum.psd.battleship.controller.GameController;
import org.scrum.psd.battleship.controller.dto.Direction;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<Ship> myFleet;
    private static List<Ship> enemyFleet;
    private static ColoredPrinter console;
    private static List<Position> listHitted = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        console = new ColoredPrinter.Builder(1, false).build();

        // print kapal
        ConsoleOut.kapal();

        InitializeGame();

        boolean playAgain;
        do {
            StartGame();
            console.println("Do you want to play again? [Y/N]");
            playAgain = scanner.next().equalsIgnoreCase("Y");
        } while (playAgain);
    }

    private static void StartGame() {
        // print meriam
        ConsoleOut.meriam();

        boolean isEnd = false;
        boolean isAllEnemisSink = true;
        boolean isAllYourShipSink = true;

        do {
            console.println("");
            console.println("Player, it's your turn");
            console.println("Enter coordinates for your shot :");
            try {
                Position position = parsePosition(scanner.next());
                Ship shipTarget = null;

                if (!position.isValidPosition())
                    console.println("Coordinate Outside the playing field, you should try again!");
                else
                    shipTarget = GameController.checkIsHit(enemyFleet, position);

                boolean isHit = (shipTarget != null);
                if (isHit) {
                    ConsoleOut.beep();

                    // print boom to me
                    ConsoleOut.boom("enemy");

                    if (shipTarget.isShink()) {
                        console.setForegroundColor(Ansi.FColor.BLUE);
                        console.println(String.format("HOUREEEEEE: Enemy's %s was sank", shipTarget.getName()));

                        for (Ship ship : enemyFleet) {
                            console.println(String.format("%s was %s", ship.getName(), ship.isShink().toString()));
                            if (!ship.isShink()) isAllEnemisSink = false;
                        }

                        if (isAllEnemisSink) {
                            console.println("All enemy's ships was sank!");
                            isEnd = true;
                        }
                    }
                }

                console.println(isHit ? "Yeah ! Nice hit !" : "Miss");

                position = getRandomPosition();
                shipTarget = GameController.checkIsHit(myFleet, position);
                isHit = (shipTarget != null);
                console.println("");
                console.println(String.format("Computer shoot in %s%s and %s", position.getColumn(), position.getRow(), isHit ? "hit your ship !" : "miss"));
                if (isHit) {
                    ConsoleOut.beep();

                    // print boom to me
                    ConsoleOut.boom("me");

                    if (shipTarget.isShink()) {
                        console.setForegroundColor(Ansi.FColor.RED);
                        console.println(String.format("OH NOOOOOOO Your %s was sank", shipTarget.getName()));

                        for (Ship ship : myFleet) {
                            console.println(String.format("%s was %s", ship.getName(), ship.isShink().toString()));
                            if (!ship.isShink()) isAllYourShipSink = false;
                        }

                        if (isAllYourShipSink) {
                            console.println("All your ships was sank!");
                            isEnd = true;
                        }
                    }
                }
            } catch (Exception ex) {
                console.println("Please enter a valid coordinate (Game board has size from A to H and 1 to 8) e.g. A1");
            }

        } while (!isEnd);

        if (isAllYourShipSink && isAllEnemisSink) {
            console.println("!!!!!!DRAW!!!!!!!");
        } else if (isAllEnemisSink) {
            console.println("!!!!!!YOU WON!!!!!");
        } else {
            console.println("!!!!!!YOU LOSE!!!!!");
        }
    }

    protected static Position parsePosition(String input) {
        Letter letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
        int number = Integer.parseInt(input.substring(1));
        return new Position(letter, number);
    }

    private static Position getRandomPosition() {
    	 int rows = 8;
        int lines = 8;
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(lines)];
        int number = random.nextInt(rows);
        Position position = new Position(letter, number);
        
        if(listHitted.contains(position)) 
        	position=getRandomPosition();
        else
        	listHitted.add(position);
        
        console.println(listHitted.add(position));
        return position;
    }

    private static void InitializeGame() {
        InitializeMyFleet();

        InitializeEnemyFleet();
    }

    private static void InitializeMyFleet() {
        Scanner scanner = new Scanner(System.in);
        myFleet = GameController.initializeShips();

        console.println("Please position your fleet (Game board has size from A to H and 1 to 8) :");

        for (Ship ship : myFleet) {
            boolean shipValid = false;
            do {
                console.println("");
                console.println(String.format("Please enter the head positions for the %s (size: %s)", ship.getName(), ship.getSize()));
                console.println(String.format("Enter Head Position (i.e A3):"));
                String positionInput = scanner.next();
                try {
                    Position position = parsePosition(positionInput);

                    if (position.isValidPosition()) {
                        console.println("");
                        console.println(String.format("Please enter the Position Direction for the %s (size: %s) - [H]orizontal / [V]ertical", ship.getName(), ship.getSize()));
                        console.println(String.format("Enter direction (H/V):", ship.getSize()));
                        String arahInput = scanner.next();
                        Direction arah = Direction.valueOf(arahInput);
                        ship.setUpPosition(position, arah);
                    }

                    shipValid = GameController.isShipValid(ship);
                    if (!shipValid) {
                        ConsoleOut.print(String.format("%s position %s ---> Ship position is Over outside!", ship.getName(), ship.getPositions()));
                    } else {
                        ConsoleOut.print(String.format("%s position %s", ship.getName(), ship.getPositions()));
                    }
                } catch (Exception ex) {
                    console.println("Please enter a valid position (Game board has size from A to H and 1 to 8) or valid direction (H/V) e.g. A1");
                }
            } while (!shipValid);
        }
    }

    private static void InitializeEnemyFleet() {
        enemyFleet = GameController.initializeShips();

//        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
//        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 5));
//        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 6));
//        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 7));
//        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 8));
//
//        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 5));
//        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 6));
//        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 7));
//        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 8));
//
//        enemyFleet.get(2).getPositions().add(new Position(Letter.A, 3));
//        enemyFleet.get(2).getPositions().add(new Position(Letter.B, 3));
//        enemyFleet.get(2).getPositions().add(new Position(Letter.C, 3));
//
//        enemyFleet.get(0).getPositions().add(new Position(Letter.D, 1));
//        enemyFleet.get(0).getPositions().add(new Position(Letter.D, 2));
//        enemyFleet.get(0).getPositions().add(new Position(Letter.D, 3));

        enemyFleet.get(0).getPositions().add(new Position(Letter.C, 5));
        enemyFleet.get(0).getPositions().add(new Position(Letter.C, 6));
    }
}
