package models;

public class King extends Piece{

    King(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wK" : "bK";
    }

    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard) {
        // Haven't handled check positions yet
        if (Math.abs(r - this.location[0]) <= 1 && Math.abs(c - this.location[1]) <= 1) return true;
        return false;
    }
}
