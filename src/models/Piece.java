/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

/**
 * Abstract class that each piece will extend
 */
public abstract class Piece {

	/**
	 * name: name of piece
	 * location: current location of piece on the board
	 * color: color of piece
	 * moved: flag to see if piece has moved. Necessary for castling and pawns moving two spaces up
	 * moveNumber: number move that instance was moved last, necessary for enpassant
	 */
	public String name;
	int[] location = new int[2];
	String color;
	boolean moved;
	int moveNumber;

	/**
	 * Constructor
	 * @param r row on which the Piece currently resides
	 * @param c column on which the Piece currently resides
	 * @param color color of current piece
	 */
	Piece(int r, int c, String color) {
		this.color = color;
		this.location[0] = r;
		this.location[1] = c; 
		this.moved = false; 
	}

	/**
	 * Abstract method required by all extending classes to determine if move was valid
	 * @param r row on which the player is attempting to move the piece
	 * @param c column on which the player is attempting to move the piece
	 * @param locationBoard chessboard with Piece locations
	 * @param moveNumber current move number to assign to piece if move is successful
	 * @return boolean to determine whether move was successful
	 */
	abstract boolean isValidMove(int r, int c, Piece[][] locationBoard, int moveNumber);

}
