/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

/**
 * Bishop Piece implementation
 */
public class Bishop extends Piece{

    /**
     * Constructor
     * @param r row on which the Piece currently resides
     * @param c column on which the Piece currently resides
     * @param color color of current piece
     */
    Bishop(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wB" : "bB";
    }
    /**
     * Checks if piece is blocked when moving diagonally
     * @param locationBoard chessboard with Piece locations
     * @return boolean determining if piece is blocked or not
     */

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
        if (Math.abs(this.location[0] - r) == Math.abs(this.location[1] - c) && !isBlocked(r, c, locationBoard))
            return true;
        this.moveNumber = temp;
        return false;
    }

}
