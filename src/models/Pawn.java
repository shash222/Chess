package models;

public class Pawn extends Piece {

	public Pawn(int x, int y, String color) {
		super(x,y,color);
  		if(color == "white") {
			this.name = "wP";
		} else {
			this.name = "bP";
		}
	}

	boolean isValidMove(int x, int y, Piece[][] locationBoard) {
		if (this.color.equals("white")) {
			if (x == this.location[0] && y == this.location[1] - 1 && locationBoard[y][x] == null) return true;
			if (x == this.location[0] && y == this.location[1] - 2 && !moved && locationBoard[y][x - 1] == null  && locationBoard[y][x] == null) return true;
			if (Math.abs(x - this.location[0]) == 1 && y == this.location[1] - 1 && locationBoard[y][x] != null) return true;
		} else {
			if (x == this.location[0] && y == this.location[1] + 1 && locationBoard[y][x] == null) return true;
			if (x == this.location[0] && y == this.location[1] + 2 && !moved && locationBoard[y][x + 1] == null  && locationBoard[y][x] == null) return true;
			if (Math.abs(x - this.location[0]) == 1 && y == this.location[1] + 1 && locationBoard[y][x] != null) return true;
		}
		return false; 
	}
	
	
}
