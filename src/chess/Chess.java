package chess;

public class Chess {
	
	Piece[][] locationBoard = new Piece[8][8];
	Chess() {
		for(int i = 0; i <= 7; i++) {
			locationBoard[1][i] = new Pawn(1,i,"black"); 
		}
		for(int i = 0; i <= 7; i++) {
			locationBoard[6][i] = new Pawn(1,i,"white"); 
		}
	}
	
		

	
	
	public void printLocationBoard() {
		for(int i = 0; i < locationBoard.length; i++) {
			System.out.println(""); 
			for(int j = 0; j < locationBoard[i].length; j++) {
				if(locationBoard[i][j] != null) {
					System.out.print(locationBoard[i][j].name + " "); 
				} else {
					System.out.print(" "); 
				}
			}
		}
	}

	public static void main(String[] args) {

		// System.out.println("Main class"); 
		Chess chess = new Chess(); 
		chess.printLocationBoard(); 
		
		

	}

}
