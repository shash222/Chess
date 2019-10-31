/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

/**
 * Knight Piece implementation
 */
public class Knight extends Piece{

    /**
     * Constructor
     * @param r row on which the Piece currently resides
     * @param c column on which the Piece currently resides
     * @param color color of current piece
     */
    Knight(int r, int c, String color) {
        super(r, c, color);
        this.name = color.equals("white") ? "wN" : "bN";
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
        if (Math.abs(r - this.location[0]) == 1 && Math.abs(c - this.location[1]) == 2
            || Math.abs(r - this.location[0]) == 2 && Math.abs(c - this.location[1]) == 1) {
        	return true;
        }
        this.moveNumber = temp;
        return false;
    }

}
