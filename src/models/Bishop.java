package models;

public class Bishop extends Piece{

    Bishop(int x, int y, String color) {
        super(x, y, color);
        this.name = color.equals("white") ? "wB" : "bB";
    }

    private boolean isBlocked(int x, int y, Piece[][] locationBoard) {
        int incrementX = (x < this.location[0]) ? -1 : 1;
        int incrementY = (y < this.location[1]) ? -1 : 1;
        for (int i = this.location[0], j = this.location[1]; i != x && j != y; i += incrementX, j += incrementY) {
            if (locationBoard[j][i] != null) return true;
        }
        return false;
    }


    @Override
    boolean isValidMove(int x, int y, Piece[][] locationBoard) {
        // Assumes self-selection is already checked
        if (Math.abs(this.location[0] - x) == Math.abs(this.location[1] - y) && !isBlocked(x, y, locationBoard))
            return true;
        return false;
    }

}
