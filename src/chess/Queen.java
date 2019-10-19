package chess;

public class Queen extends Piece {

	boolean firstMove;

	Queen(int x, int y, String color) {
		super(x, y, color);
		this.firstMove = false;
		if (color == "white") {
			this.name = "wQ";
		} else {
			this.name = "bQ";
		}
	}

	boolean isValidMove(int x, int y, Piece[][] locationBoard) {
		
		return false;
	}

}
