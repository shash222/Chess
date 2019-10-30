/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

/**
 * Rook Piece implementation
 */
public class Rook extends Piece {

    /**
     * Constructor
     * @param r row on which the Piece currently resides
     * @param c column on which the Piece currently resides
     * @param color color of current piece
     */
    Rook(int r, int c, String color) {
		super(r, c, color);
		this.name = color.equals("white") ? "wR" : "bR";
	}

    /**
     * Checks if piece is blocked when moving horizontally
     * @param c column on which the player is attempting to move the piece
     * @param locationBoard chessboard with Piece locations
     * @return boolean determining if piece is blocked or not
     */
	private boolean isBlockedAcrossR(int c, Piece[][] locationBoard) {
//	    System.out.println("This: " + this.location[1]);
		int increment = (c < this.location[1]) ? -1 : 1;
		for (int i = this.location[1] + increment; i != c; i += increment) {
//		    System.out.println(i);
			if (i >= 0 && i < locationBoard.length) {
				if (locationBoard[this.location[0]][i] != null) {
//				    System.out.println("Blocked R " + this.location[0] + "   " + this.location[i] + "   " + locationBoard[this.location[0]][i]);
				    return true;
                }
			} else {
				break;
			}
		}
		return false;
	}

    /**
     * Checks if piece is blocked when moving horizontally
     * @param r row on which the player is attempting to move the piece
     * @param locationBoard chessboard with Piece locations
     * @return boolean determining if piece is blocked or not
     */
	private boolean isBlockedAcrossC(int r, Piece[][] locationBoard) {
		int increment = (r < this.location[0]) ? -1 : 1;
		for (int i = this.location[0] + increment; i != r; i += increment) {
			if (i < locationBoard.length && i >= 0) {
				if (locationBoard[i][this.location[1]] != null) {
//                    System.out.println("Blocked C");
                    return true;
                }
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
		// Assumes any out of bounds input and self-selection is pre-checked
		if (r == this.location[0] && !isBlockedAcrossR(c, locationBoard)
				|| c == this.location[1] && !isBlockedAcrossC(r, locationBoard))
			return true;
		this.moveNumber = temp;
		return false;
	}
}
