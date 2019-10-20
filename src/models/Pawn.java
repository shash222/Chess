package models;

public class Pawn extends Piece {

	public Pawn(int r, int c, String color) {
		super(r,c,color);
  		if(color == "white") {
			this.name = "wP";
		} else {
			this.name = "bP";
		}
	}

	boolean isValidMove(int r, int c, Piece[][] locationBoard) {
		if (this.color.equals("white")) {
			if (c == this.location[1] && r == this.location[0] - 1 && locationBoard[r][c] == null) 	return true;
			if (c == this.location[1] && r == this.location[0] - 2 && !moved && locationBoard[r][c - 1] == null  && locationBoard[r][c] == null) return true;
			if (Math.abs(c - this.location[0]) == 1 && r == this.location[1] - 1 && locationBoard[r][c] != null) return true;
		} else {
			if (c == this.location[1] && r == this.location[0] + 1 && locationBoard[r][c] == null) return true;
			if (c == this.location[1] && r == this.location[0] + 2 && !moved && locationBoard[r][c + 1] == null  && locationBoard[r][c] == null) return true;
			if (Math.abs(c - this.location[1]) == 1 && r == this.location[0] + 1 && locationBoard[r][c] != null) return true;
		}
		return false; 
		
	}
	
	
}
