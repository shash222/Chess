/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

/**
 * Queen Piece implementation
 */
public class Queen extends Piece {

	/**
	 * Constructor
	 * @param r row on which the Piece currently resides
	 * @param c column on which the Piece currently resides
	 * @param color color of current piece
	 */
	Queen(int r, int c, String color) {
		super(r, c, color);
 		if (color.equals("white")) {
			this.name = "wQ";
		} else {
			this.name = "bQ";
		}
	}

	/**
	 * Checks if piece is blocked when moving horizontally
	 * @param c column on which the player is attempting to move the piece
	 * @param locationBoard chessboard with Piece locations
	 * @return boolean determining if piece is blocked or not
	 */
	private boolean isBlockedAcrossR(int c, Piece[][] locationBoard) {
		int increment = (c < this.location[1]) ? -1 : 1;
		for (int i = this.location[1] + increment; i != c; i += increment) {
			if (i < locationBoard.length && i >= 0) {
				if (locationBoard[this.location[0]][i] != null) return true;
			} else {
				break;
			}
		}
		return false;
	}

	/**
	 * Checks if piece is blocked when moving vertically
	 * @param r row on which the player is attempting to move the piece
	 * @param locationBoard chessboard with Piece locations
	 * @return boolean determining if piece is blocked or not
	 */
	private boolean isBlockedAcrossC(int r, Piece[][] locationBoard) {
		int increment = (r < this.location[0]) ? -1 : 1;
		for (int i = this.location[0] + increment; i != r; i += increment) {
			if (i < locationBoard.length && i >= 0) {
				if (locationBoard[i][this.location[1]] != null) return true;
			} else {
				break; 
			}
		}
		return false;
	}

	/**
	 * Checks if piece is blocked when moving diagonally
	 * @param r row on which the player is attempting to move the piece
	 * @param c column on which the player is attempting to move the piece
	 * @param locationBoard chessboard with Piece locations
	 * @return boolean determining if piece is blocked or not
	 */
	private boolean isBlockedDiagonal(int r, int c, Piece[][] locationBoard) {
		int incrementX = (r < this.location[0]) ? -1 : 1;
		int incrementY = (c < this.location[1]) ? -1 : 1;
		for (int i = this.location[0] + incrementX, j = this.location[1] + incrementY; i != r && j != c; i += incrementX, j += incrementY) {
			if((i < locationBoard.length && j < locationBoard[i].length) && i >= 0 && j >= 0) {
                if (locationBoard[i][j] != null) return true;
        	} else {
        		break; 
        	}
		}

		return false;
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
		if (r == this.location[0] && !isBlockedAcrossR(c, locationBoard)
				|| c == this.location[1] && !isBlockedAcrossC(r, locationBoard)) return true;
		else if (Math.abs(this.location[0] - r) == Math.abs(this.location[1] - c) && !isBlockedDiagonal(r, c, locationBoard))
			return true;
		this.moveNumber = temp;
		return false;
	}

}
