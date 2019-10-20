package chess;

import models.ChessBoard;
import models.Pawn;
import models.Piece;

public class Chess {
	
	public static void main(String[] args) {

		// System.out.println("Main class");
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.printLocationBoard();
	}

	/*
		All isValidMove methods do not check the desired position if there is a piece there, that should be handled by
		controller, and remove the piece from the board accordingly
		In this case, if there is a piece at desired location, and color matches that of piece being moved, immediately
		alert user of bad move
	 */

}
