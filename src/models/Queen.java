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

	private boolean isBlockedAcrossR(int c, Piece[][] locationBoard) {
		int increment = (c < this.location[1]) ? -1 : 1;
		for (int i = this.location[1] + increment; i != c; i += increment) {
			if (locationBoard[this.location[0]][i] != null) return true;
		}
		return false;
	}

	private boolean isBlockedAcrossC(int r, Piece[][] locationBoard) {
		int increment = (r < this.location[0]) ? -1 : 1;
		for (int i = this.location[0] + increment; i != r; i += increment) {
			if (locationBoard[i][this.location[1]] != null) return true;
		}
		return false;
	}

	private boolean isBlockedDiagonal(int r, int c, Piece[][] locationBoard) {
		int incrementX = (r < this.location[0]) ? -1 : 1;
		int incrementY = (c < this.location[1]) ? -1 : 1;
		for (int i = this.location[0] + incrementX, j = this.location[1] + incrementY; i != r && j != c; i += incrementX, j += incrementY) {
			if (locationBoard[j][i] != null) return true;
		}
		return false;
	}

	@Override
	boolean isValidMove(int r, int c, Piece[][] locationBoard) {
		if (r == this.location[0] && !isBlockedAcrossR(c, locationBoard)
				|| c == this.location[1] && !isBlockedAcrossC(r, locationBoard)) return true;
		else if (Math.abs(this.location[0] - r) == Math.abs(this.location[1] - c) && !isBlockedDiagonal(r, c, locationBoard))
			return true;
		return false;
	}

}
