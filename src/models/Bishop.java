package models;

public class Bishop extends Piece{

    Bishop(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wB" : "bB";
    }

    private boolean isBlocked(int r, int c, Piece[][] locationBoard) {
        int incrementX = (r < this.location[0]) ? -1 : 1;
        int incrementY = (c < this.location[1]) ? -1 : 1;
        for (int i = this.location[0] + incrementX, j = this.location[1] + incrementY; i != r; i += incrementX, j += incrementY) {
        	if((i < locationBoard.length && j < locationBoard[i].length) && i >= 0 && j >= 0) {
                if (locationBoard[i][j] != null) return true;
        	} else {
        		break; 
        	}
        }
        return false;
    }

    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard, int moveNumber) {
        int temp = this.moveNumber;
        this.moveNumber = moveNumber;
        if (Math.abs(this.location[0] - r) == Math.abs(this.location[1] - c) && !isBlocked(r, c, locationBoard))
            return true;
        this.moveNumber = temp;
        return false;
    }

}
