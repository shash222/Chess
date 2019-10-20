package models;

public class Queen extends Piece {

	Queen(int x, int y, String color) {
		super(x, y, color);
 		if (color.equals("white")) {
			this.name = "wQ";
		} else {
			this.name = "bQ";
		}
	}

	private boolean isBlockedX(int x, Piece[][] locationBoard) {
		int increment = (x < this.location[0]) ? -1 : 1;
		for (int i = this.location[0]; i != x; i += increment) {
			if (locationBoard[i][this.location[1]] != null) return true;
		}
		return false;
	}

	private boolean isBlockedY(int y, Piece[][] locationBoard) {
		int increment = (y < this.location[0]) ? -1 : 1;
		for (int i = this.location[1]; i != y; i += increment) {
			if (locationBoard[this.location[0]][i] != null) return true;
		}
		return false;
	}

	private boolean isBlockedDiagonal(int x, int y, Piece[][] locationBoard) {
		int incrementX = (x < this.location[0]) ? -1 : 1;
		int incrementY = (y < this.location[1]) ? -1 : 1;
		for (int i = this.location[0], j = this.location[1]; i != x && j != y; i += incrementX, j += incrementY) {
			if (locationBoard[j][i] != null) return true;
		}
		return false;
	}

	@Override
	boolean isValidMove(int x, int y, Piece[][] locationBoard) {
		if (x == this.location[0] && !isBlockedY(y, locationBoard)
				|| y == this.location[1] && !isBlockedX(x, locationBoard)) return true;
		else if (Math.abs(this.location[0] - x) == Math.abs(this.location[1] - y) && !isBlockedDiagonal(x, y, locationBoard))
			return true;
		return false;
	}

}
