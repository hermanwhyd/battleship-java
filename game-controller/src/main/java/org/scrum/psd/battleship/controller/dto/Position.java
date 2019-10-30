package org.scrum.psd.battleship.controller.dto;

public class Position {
    private Letter column;
    private int row;
    private Boolean hitted;

    public Position() {
        super();
    }

    public Position(Letter column, int row) {
        this();

        this.column = column;
        this.row = row;
        this.hitted = Boolean.FALSE;
    }

    public Boolean isHitted() {
        return hitted;
    }

    public Boolean isValidPosition() {
        Boolean result = Boolean.TRUE;
        if (row < 0 || row > 8) return Boolean.FALSE;
        if (column.ordinal() > 8) return Boolean.FALSE;

        return result;
    }

    public void setHitted() {
        hitted = Boolean.TRUE;
    }

    public Letter getColumn() {
        return column;
    }

    public void setColumn(Letter column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override public boolean equals(Object o) {
        if(o instanceof Position) {
            Position position = (Position) o;

            if(position == null) {
                return false;
            }

            return position.column.equals(column) && position.row == row;
        }

        return false;
    }
}
