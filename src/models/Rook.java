package models;

public class Rook extends Piece {

    Rook(int x, int y, String color) {
        super(x, y, color);
        this.name = color.equals("white") ? "wR" : "bR";
    }

    private boolean isBlockedX(int x, Piece[][] locationBoard) {
        int increment = (x < this.location[0]) ? -1 : 1;
        for (int i = this.location[0]; i != x; i += increment) {
            if (locationBoard[i][this.location[1]] != null) return true;
        }
        return false;
    }

    private boolean isBlockedY(int y, Piece[][] locationBoard) {
        int increment = (y < this.location[0]) ? -1 : 1;
        for (int i = this.location[1]; i != y; i += increment) {
            if (locationBoard[this.location[0]][i] != null) return true;
        }
        return false;
    }

    @Override
    boolean isValidMove(int x, int y, Piece[][] locationBoard) {
        // Assumes any out of bounds input and self-selection is pre-checked
        if (x == this.location[0] && !isBlockedY(y, locationBoard)
            || y == this.location[1] && !isBlockedX(x, locationBoard)) return true;
        return false;
    }
}
