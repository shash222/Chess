package models;

public class Knight extends Piece{

    Knight(int x, int y, String color) {
        super(x, y, color);
        this.name = color.equals("white") ? "wN" : "bN";
    }

    @Override
    boolean isValidMove(int x, int y, Piece[][] locationBoard) {
        if (Math.abs(x - this.location[0]) == 1 && Math.abs(y - this.location[1]) == 2
            || Math.abs(x - this.location[0]) == 2 && Math.abs(y - this.location[1]) == 1) return true;
        return false;
    }

}
