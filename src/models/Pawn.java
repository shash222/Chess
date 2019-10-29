/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

/**
 * Pawn Piece implementation
 */
public class Pawn extends Piece {
	/**
	 * movedTwo: flag to keep track of if pawn moved 2 spaces in its first turn to determine if opponent can enpassant
	 * performedEnpassant: flag to keep track of if Pawn performed enpassant
	 */
	boolean movedTwo;
	boolean performedEnpassant;

	/**
	 * Constructor
	 * @param r row on which the Piece currently resides
	 * @param c column on which the Piece currently resides
	 * @param color color of current piece
	 */
	public Pawn(int r, int c, String color) {
		super(r,c,color);
  		if(color == "white") {
			this.name = "wP";
		} else {
			this.name = "bP";
		}
	}

	/**
	 * Overrides isValidMove abstract method from Piece class
	 * @param r row on which the player is attempting to move the piece
	 * @param c column on which the player is attempting to move the piece
	 * @param locationBoard chessboard with Piece locations
	 * @param moveNumber current move number to assign to piece if move is successful
	 * @return boolean to determine whether move was successful
	 */
	@Override
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
