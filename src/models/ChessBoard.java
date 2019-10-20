package models;

public class ChessBoard {

    private Piece[][] locationBoard = new Piece[8][8];

    public ChessBoard() {
        for(int i = 0; i <= 7; i++) {
            locationBoard[1][i] = new Pawn(1,i,"black");
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

}
