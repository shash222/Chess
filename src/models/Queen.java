package models;

public class Queen extends Piece {

	Queen(int r, int c, String color) {
		super(r, c, color);
 		if (color.equals("white")) {
			this.name = "wQ";
		} else {
			this.name = "bQ";
		}
	}

	private boolean isBlockedX(int r, Piece[][] locationBoard) {
		int increment = (r < this.location[0]) ? -1 : 1;
		for (int i = this.location[0]; i != r; i += increment) {
			if (locationBoard[i][this.location[1]] != null) return true;
		}
		return false;
	}

	private boolean isBlockedY(int c, Piece[][] locationBoard) {
		int increment = (c < this.location[0]) ? -1 : 1;
		for (int i = this.location[1]; i != c; i += increment) {
			if (locationBoard[this.location[0]][i] != null) return true;
		}
		return false;
	}

	private boolean isBlockedDiagonal(int r, int c, Piece[][] locationBoard) {
		int incrementX = (r < this.location[0]) ? -1 : 1;
		int incrementY = (c < this.location[1]) ? -1 : 1;
		for (int i = this.location[0], j = this.location[1]; i != r && j != c; i += incrementX, j += incrementY) {
			if (locationBoard[j][i] != null) return true;
		}
		return false;
	}

	@Override
	boolean isValidMove(int r, int c, Piece[][] locationBoard) {
		if (r == this.location[0] && !isBlockedY(c, locationBoard)
				|| c == this.location[1] && !isBlockedX(r, locationBoard)) return true;
		else if (Math.abs(this.location[0] - r) == Math.abs(this.location[1] - c) && !isBlockedDiagonal(r, c, locationBoard))
			return true;
		return false;
	}

}
