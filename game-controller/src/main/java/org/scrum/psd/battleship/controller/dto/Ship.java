package org.scrum.psd.battleship.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private boolean isPlaced;
    private String name;
    private int size;
    private List<Position> positions;
    private Color color;

    public Ship() {
        this.positions = new ArrayList<>();
    }

    public Ship(String name, int size) {
        this();

        this.name = name;
        this.size = size;
    }

    /**
     * Get Ship status was Sink or Not yet
     * @return
     */
    public Boolean isShink() {
        Boolean result = Boolean.TRUE;
        for(Position p : positions) if (!p.isHitted()) result = Boolean.FALSE;
        return result;
    }

    public Ship(String name, int size, List<Position> positions) {
        this(name, size);

        this.positions = positions;
    }

    public Ship(String name, int size, Color color) {
        this(name, size);

        this.color = color;
    }

    public void addPosition(Position pos) {
        if (positions == null) {
            positions = new ArrayList<>();
        }

        positions.add(pos);
    }

    public void addPosition(String input) {
        if (positions == null) {
            positions = new ArrayList<>();
        }

        Letter letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
        int number = Integer.parseInt(input.substring(1));

        positions.add(new Position(letter, number));
    }

    public void setUpPosition(Position headPosition, Direction direction) {
        positions = positions = new ArrayList<>();
        positions.add(headPosition);

        for(int i = 1; i<this.size; i++) {
            if (direction.equals(Direction.H)) {
                positions.add(new Position(Letter.values()[headPosition.getColumn().ordinal() + i] ,headPosition.getRow()));
            } else {
                positions.add(new Position(headPosition.getColumn(), headPosition.getRow() + i));
            }
        }

    }

//    public String printPosition() {
//        StringBuilder sb = new StringBuilder();
//        if (positions != null) {
//            positions.forEach(position -> {
//                sb.append(positions.toString());
//                sb.append("\n");
//            });
//        }
//
//        return sb.toString();
//    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
