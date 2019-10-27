package models;

public class Knight extends Piece{

    Knight(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wN" : "bN";
    }

    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard) {
    	
        if (Math.abs(r - this.location[0]) == 1 && Math.abs(c - this.location[1]) == 2
            || Math.abs(r - this.location[0]) == 2 && Math.abs(c - this.location[1]) == 1) {
        	return true;
        }
        return false;
    }

}
