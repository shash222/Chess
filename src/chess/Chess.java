package chess;

import models.ChessBoard;
import models.Pawn;
import models.Piece;

public class Chess {

	private static ChessBoard chessBoard;

	private static boolean indexHasPiece(int[] indices) {
		return (chessBoard.getLocationBoard()[indices[0]][indices[1]] != null);
	}

	private static boolean isInputOutOfBounds(int[] indices) {
		return (indices[0] < 0 || indices[0] > 7 || indices[1] < 0 || indices[1] > 7);
	}

	private static int[] convertInputToArrayIndices(String input) {
		int[] indices = new int[2];
		indices[0] = Character.getNumericValue(input.charAt(0) - 'a');
		// 8 because the board is an 8x8 square matrix, and rows go from 1 - 8 (row 8 is the row with index 0)
		indices[1] = 8 - Character.getNumericValue(input.charAt(1));
		return indices;
	}

	public static void main(String[] args) {
		// System.out.println("Main class");
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.play();
	}


		// Check if desired location has a piece of the same color as the piece being moved
		// if so, move is invalid, if not, check if valid move using overridden method
	

}
