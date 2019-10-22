package models;

public class Rook extends Piece {

    Rook(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wR" : "bR";
    }

    private boolean isBlockedAcrossR(int c, Piece[][] locationBoard) {
        int increment = (c < this.location[1]) ? -1 : 1;
        for (int i = this.location[1] + increment; i != c; i += increment) {
            if (locationBoard[this.location[0]][i] != null) return true;
        }
        return false;
    }

    private boolean isBlockedAcrossC(int r, Piece[][] locationBoard) {
        int increment = (r < this.location[0]) ? -1 : 1;
        for (int i = this.location[0] + increment; i != r; i += increment) {
            if (locationBoard[i][this.location[1]] != null) return true;
        }
        return false;
    }

    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard) {
        // Assumes any out of bounds input and self-selection is pre-checked
        if (r == this.location[0] && !isBlockedAcrossR(c, locationBoard)
            || c == this.location[1] && !isBlockedAcrossC(r, locationBoard)) return true;
        return false;
    }
}
