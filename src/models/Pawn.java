package models;

public class Pawn extends Piece {
	boolean movedTwo;
	boolean performedEnpassant;

	public Pawn(int r, int c, String color) {
		super(r,c,color);
  		if(color == "white") {
			this.name = "wP";
		} else {
			this.name = "bP";
		}
	}

	boolean isValidMove(int r, int c, Piece[][] locationBoard, int moveNumber) {
		int temp = this.moveNumber;
		this.moveNumber = moveNumber;
		if (this.color.equals("white")) {
			if (c == this.location[1] && r == this.location[0] - 1 && locationBoard[r][c] == null) return true;
			if (c == this.location[1] && r == this.location[0] - 2 && !moved && locationBoard[r + 1][c] == null  && locationBoard[r][c] == null) {
				this.movedTwo = true;
				return true;
			}
			if (Math.abs(c - this.location[1]) == 1 && r == this.location[0] - 1) {
				if (locationBoard[r][c] != null) return true;
				if (locationBoard[r + 1][c] instanceof Pawn) {
					Pawn destinationPawn = (Pawn) locationBoard[r + 1][c];
					if (destinationPawn.movedTwo && this.moveNumber == destinationPawn.moveNumber + 1) {
						performedEnpassant = true;
						return true;
					}
				}
			}

		} else {
			if (c == this.location[1] && r == this.location[0] + 1 && locationBoard[r][c] == null) return true;
			if (c == this.location[1] && r == this.location[0] + 2 && !moved && locationBoard[r - 1][c] == null  && locationBoard[r][c] == null) {
				movedTwo = true;
  				return true;
			}
			if (Math.abs(c - this.location[1]) == 1 && r == this.location[0] + 1) {
				if (locationBoard[r][c] != null) return true;
				if (locationBoard[r - 1][c] instanceof Pawn) {
					Pawn destinationPawn = (Pawn) locationBoard[r - 1][c];
					if (destinationPawn.movedTwo && this.moveNumber == destinationPawn.moveNumber + 1) {
						performedEnpassant = true;
						return true;
					}
				}
			}
		}
		this.moveNumber = temp;
		return false;

	}
	
	
}
