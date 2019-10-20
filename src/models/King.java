package models;

public class King extends Piece{

    King(int x, int y, String color) {
        super(x, y, color);
        this.name = color.equals("white") ? "wK" : "bK";
    }

    @Override
    boolean isValidMove(int x, int y, Piece[][] locationBoard) {
        // Haven't handled check positions yet
        if (Math.abs(x - this.location[0]) <= 1 && Math.abs(y - this.location[1]) <= 1) return false;
        return false;
    }
}
