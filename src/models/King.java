/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

import java.util.Iterator;
import java.util.Set;

/**
 * King Piece implementation
 */
public class King extends Piece{
    /**
     * Boolean flag to tell controller if King is attempting to castle
     */
    boolean castle;

    /**
     * Constructor
     * @param r row on which the Piece currently resides
     * @param c column on which the Piece currently resides
     * @param color color of current piece
     */
    King(int r, int c, String color) {
        super(r, c, color);
                this.name = color.equals("white") ? "wK" : "bK";
            }

            public boolean castlingThroughCheck(Set<Piece> aliveEnemyPieces, int[] startLocation, Piece[][] locationBoard, int moveCounter) {
                Iterator<Piece> w = aliveEnemyPieces.iterator();
                int increment = (startLocation[1] < this.location[1]) ? -1 : 1;
//        System.out.println(startLocation[1] + "   " + this.location[1]);
//        System.out.println(increment);
//        System.out.println(aliveEnemyPieces.size());
                while (w.hasNext()) {
                    Piece piece = w.next();
//            System.out.println(piece);
                    for (int i = this.location[1]; i != startLocation[1]; i += increment) {
//                System.out.println(piece + " cannot go");
                if (piece.isValidMove(this.location[0], i, locationBoard, moveCounter)) {
//                    System.out.println("return true: " + piece + "  " + this.location[0] + "   " + i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if King can successfully castle
     * @param c column on which the player is attempting to move the piece
     * @param locationBoard chessboard with Piece locations
     * @return boolean determining if piece is blocked or not
     */
    private boolean castleIsBlocked(int c, Piece[][] locationBoard) {
        int increment = (c < this.location[1]) ? -1 : 1;
        for (int i = this.location[1] + increment; i != c; i += increment) {
//            System.out.println(i + "    " + c);
            if (i < locationBoard.length && i >= 0) {
                if (locationBoard[this.location[0]][i] != null) return true;
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * Overrides isValidMove abstract method from Piece class
     * @param r row on which the player is attempting to move the piece
     * @param c column on which the player is attempting to move the piece
     * @param locationBoard chessboard with Piece locations
     * @param moveNumber current move number to assign to piece if move is successful
     * @return boolean to determine whether move was successful
     */
    @Override
    boolean isValidMove(int r, int c, Piece[][] locationBoard, int moveNumber) {
        // Haven't handled check positions yet
        castle = false;
        int temp = this.moveNumber;
        this.moveNumber = moveNumber;
        if (Math.abs(r - this.location[0]) <= 1 && Math.abs(c - this.location[1]) <= 1) return true;
        if (!this.moved &&
                ((r == this.location[0] && c == 1)
                && locationBoard[this.location[0]][0] instanceof Rook
                && !locationBoard[this.location[0]][0].moved
                && !castleIsBlocked(1, locationBoard)
            ||
                (r == this.location[0] && c == 6)
                && locationBoard[this.location[0]][7] instanceof Rook
                && !locationBoard[this.location[0]][7].moved
                && !castleIsBlocked(7, locationBoard))) {
            castle = true;
            return true;
        }
        this.moveNumber = temp;
        return false;
    }
}
