package chess;

public class Pawn extends Piece {
	
	boolean firstMove; 
	
	Pawn(int x, int y, String color) {
		super(x,y,color); 
		this.firstMove = false;
		if(color == "white") {
			this.name = "wp";
		} else {
			this.name = "bp"; 
		}
	}
	
	boolean isValidMove(int x, int y, Piece[][] locationBoard) {
		
		
		return false; 
	}
	
	
}
