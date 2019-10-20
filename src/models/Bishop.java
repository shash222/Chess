package models;

public class Bishop extends Piece{

    Bishop(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wB" : "bB";
    }

    private boolean isBlocked(int r, int c, Piece[][] locationBoard) {
        int incrementX = (r < this.location[0]) ? -1 : 1;
        int incrementY = (c < this.location[1]) ? -1 : 1;
        for (int i = this.location[0], j = this.location[1]; i != r && j != c; i += incrementX, j += incrementY) {
            if (locationBoard[j][i] != null) return true;
        }
        return false;
    }


    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard) {
        // Assumes self-selection is already checked
        if (Math.abs(this.location[0] - r) == Math.abs(this.location[1] - c) && !isBlocked(r, c, locationBoard))
            return true;
        return false;
    }

}
