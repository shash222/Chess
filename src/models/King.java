package models;

public class King extends Piece{
    boolean castle;

    King(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wK" : "bK";
    }

    private boolean castleIsBlocked(int c, Piece[][] locationBoard) {
        int increment = (c < this.location[1]) ? -1 : 1;
        for (int i = this.location[1] + increment; i != c; i += increment) {
            System.out.println(i + "    " + c);
            if (i < locationBoard.length && i >= 0) {
                if (locationBoard[this.location[0]][i] != null) return true;
            } else {
                break;
            }
        }
        return false;
    }

    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard, int moveNumber) {
        // Haven't handled check positions yet
        castle = false;
        int temp = this.moveNumber;
        this.moveNumber = moveNumber;
        if (Math.abs(r - this.location[0]) <= 1 && Math.abs(c - this.location[1]) <= 1) return true;
        if (!this.moved &&
                ((r == this.location[0] && c == 1)
                && locationBoard[this.location[0]][0] instanceof Rook
                && !locationBoard[this.location[0]][0].moved
                && !castleIsBlocked(1, locationBoard)
            ||
                (r == this.location[0] && c == 6)
                && locationBoard[this.location[0]][7] instanceof Rook
                && !locationBoard[this.location[0]][7].moved
                && !castleIsBlocked(7, locationBoard))) {
            castle = true;
            return true;
        }
        this.moveNumber = temp;
        return false;
    }
}
