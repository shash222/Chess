/**
 * @author Salman Hashmi
 * @author Mohammed Alnadi
 */
package models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
/**
 * Chessboard object, serves as Controller for Chess Game
 */
public class ChessBoard {

	/**
	 * Chessboard is an 8x8 matrix, where locationBoard is the placement of each piece on the board, colorBoard is used
	 * to keep track of which spot on the board is black and which is white, aliveWhitePieces and aliveBlackPieces keeps
	 * track of all pieces on each side that are alive. bKing and wKing keep track of the king pieces for each side.
	 * promotablePieces is a set of all of the legal promotions that can occur for a pawn, drawRequested keeps track of
	 * whether a draw was requested by a player so that if the other player enters "draw" in the next turn, the game
	 * ends.
	 */
	private Piece[][] locationBoard = new Piece[8][8];
	private String[][] colorBoard = new String[8][8];
	private Set<Piece> aliveWhitePieces = new HashSet();
	private Set<Piece> aliveBlackPieces = new HashSet();
	private Piece bKing = new King(0, 4, "black");
	private Piece wKing = new King(7, 4, "white");

	Set<String> promotablePieces = new HashSet<>(Arrays.asList("N", "R", "B", "Q"));
	boolean drawRequested = false;
	int moveCounter = 0;
	String promotionPiece;


	/**
	 * Constructor, defines what pieces are originally placed where, and what color each spot is
	 */
	public ChessBoard() {
		for (int i = 0; i <= 7; i++) {
			locationBoard[1][i] = new Pawn(1, i, "black");
			locationBoard[6][i] = new Pawn(6, i, "white");
		}
		locationBoard[0][0] = new Rook(0, 0, "black");
		locationBoard[0][7] = new Rook(0, 7, "black");
		locationBoard[7][0] = new Rook(7, 0, "white");
		locationBoard[7][7] = new Rook(7, 7, "white");

		locationBoard[7][1] = new Knight(7, 1, "white");
		locationBoard[7][6] = new Knight(7, 6, "white");
		locationBoard[0][6] = new Knight(0, 6, "black");
		locationBoard[0][1] = new Knight(0, 1, "black");

		locationBoard[0][2] = new Bishop(0, 2, "black");
		locationBoard[0][5] = new Bishop(0, 5, "black");
		locationBoard[7][2] = new Bishop(7, 2, "white");
		locationBoard[7][5] = new Bishop(7, 5, "white");

		locationBoard[0][4] = bKing;
		locationBoard[7][4] = wKing;

		locationBoard[0][3] = new Queen(0, 3, "black");
		locationBoard[7][3] = new Queen(7, 3, "white");

		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < colorBoard.length; j++) {
				aliveBlackPieces.add(locationBoard[i][j]);
				aliveWhitePieces.add(locationBoard[colorBoard.length - i - 1][j]);
			}
		}

		for (int i = 0; i < colorBoard.length; i++) {
			for (int j = 0; j < colorBoard[i].length; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 1) {
						colorBoard[i][j] = "##";
					} else {
						colorBoard[i][j] = "  ";
					}
				} else {
					if (j % 2 == 0) {
						colorBoard[i][j] = "##";
					} else {
						colorBoard[i][j] = "  ";
					}
				}

			}
		}
	}

	/**
	 * Prints the chessboard
	 */
	public void printLocationBoard() {
		for (int i = 0; i < locationBoard.length; i++) {
			if (i != 0)
				System.out.println("");

			for (int j = 0; j < locationBoard[i].length; j++) {
				if (locationBoard[i][j] != null) {
					System.out.print(locationBoard[i][j].name + " ");
				} else {
					System.out.print(colorBoard[i][j] + " ");
				}
			}
			System.out.print(locationBoard.length - i);
		}
		System.out.println("");
		System.out.print(" a  b  c  d  e  f  g  h");
		System.out.println("");

	}


	/**
	 * 	Checks if input is syntactically valid. Checks the "number of inputs" by splitting the input string by a single
	 * 	space, and if the number of inputs is greater than 3, the input is illegal. The conditions for the input being
	 * 	legal are as follows:
	 * 	 - number of inputs is 1, and equates to "draw" only if draw was requested in the previous turn, or
	 * 	 - number of inputs is 2, and the first character of each input is a-h, and the second character is 1-8, or
	 * 	 - number of inputs is 3 where first two inputs correspond to when the number of inputs is 2
	 * 	 	- the piece being moved is a pawn, and is being promoted and the desired promoted value (third input) exists, or
	 * 	 	- the third input requests "draw?"
\ 	 */
	private boolean checkValidString(String input) {
		// Splits inputted string by space
		String[] splitInput = input.split(" ");
		if (splitInput.length == 3 && promotablePieces.contains(splitInput[2])) {
			promotionPiece = splitInput[2];
		}
		// Greater than 3 in case 3rd word is "draw?"
		if (splitInput.length > 3) {
			return false;
		} else if (splitInput.length == 3 && !splitInput[2].equals("draw?") && splitInput[2].length() != 1
				|| (splitInput.length == 1 && !splitInput[0].equals("draw"))) {
			return false;
		} else if (splitInput[0].equals("draw") && drawRequested) {
			return true;
		} else if (splitInput[0].equals("draw") && !drawRequested) {
			return false;
		}
		String str = splitInput[0] + splitInput[1];
		// str = str.replaceAll("\\s+","");

		// if(str.length() > 4) {
		// return false;
		// }
		boolean[] check = new boolean[4];

		try {
			for (int i = 0; i < str.length(); i++) {
				String letters = "abcdefgh";

				// letter
				if (i % 2 == 0) {
					for (int z = 0; z < letters.length(); z++) {
						if (str.charAt(i) == letters.charAt(z)) {
							check[i] = true;
						}
					}
				} else {
					// number
					String numbers = "12345678";
					for (int z = 0; z < numbers.length(); z++) {
						if (str.charAt(i) == numbers.charAt(z)) {
							check[i] = true;
						}
					}
				}
			}
			for (int i = 0; i < check.length; i++) {
				if (check[i] == false)
					return false;
			}
		} catch (Exception e) {
			return false;
		}
  		return true;
	}

	/**
	 * Convert input values to indices to work with on the board
	 * @param input String representing move desired
	 * @return int array of size 4 with the input values converted to indices to work with on the board
	 */
	private int[] interpretString(String input) {
		String[] splitInput = input.split(" ");
		String str = splitInput[0] + splitInput[1];
		// str = str.replaceAll("\\s+","");
		int[] result = new int[4];
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') {
				result[i] = 0;
			} else if (str.charAt(i) == 'b') {
				result[i] = 1;
			} else if (str.charAt(i) == 'c') {
				result[i] = 2;
			} else if (str.charAt(i) == 'd') {
				result[i] = 3;
			} else if (str.charAt(i) == 'e') {
				result[i] = 4;
			} else if (str.charAt(i) == 'f') {
				result[i] = 5;
			} else if (str.charAt(i) == 'g') {
				result[i] = 6;
			} else if (str.charAt(i) == 'h') {
				result[i] = 7;
			} else {
				result[i] = Math.abs(locationBoard.length - Integer.parseInt(String.valueOf(str.charAt(i))));
			}
			// System.out.println(result[i]);

		}

		return result;

	}

	/**
	 * Determines if King is in check
	 * @param print boolean whether to print if piece is in check or not
	 * @return String describing what player is in check, if any
	 */
	private String check(boolean print) {

		Iterator<Piece> w = aliveWhitePieces.iterator();
		String checkPlayer = "";
		while (w.hasNext()) {
			Piece piece = w.next();
			if (piece.isValidMove(bKing.location[0], bKing.location[1], locationBoard, moveCounter)) {
				if (print)
					System.out.println("Check black");
				checkPlayer = "black";
			}
		}

		Iterator<Piece> b = aliveBlackPieces.iterator();
		while (b.hasNext()) {
			Piece piece = b.next();
			if (piece.isValidMove(wKing.location[0], wKing.location[1], locationBoard, moveCounter)) {
				if (print)
					System.out.println("Check white");
				checkPlayer = "white";
			}
		}

		return checkPlayer;

	}

	/**
	 * Checks if there is the player has a piece that can block the checkmate
	 * @param playerColor player's turn color
	 * @return whether checkmate can be blocked by any of the player's pieces
	 */
	private boolean canBlockCheckmate(String playerColor) {

		return false;
	}

	/**
	 * Determines if player is in checkmate
	 * @param playerColor String representing player that we are looking for to determine if it is in check
	 * @return boolean whether player is in checkmate
	 */
	private boolean checkMate(String playerColor) {
		Set<Piece> tempSet;
		Piece king;
		if (playerColor.equalsIgnoreCase("white")) {
			king = wKing;
			tempSet = aliveWhitePieces;
		} else {
			king = bKing;
			tempSet = aliveBlackPieces;
		}

		// check if king can move to safe location
		int[] selectedLocation = {king.location[0], king.location[1]};
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ((king.location[0] + i) >= 0 && (king.location[0] + i) <  locationBoard.length) {

					if ((king.location[1] + j) >= 0
							&& (king.location[1] + j) < locationBoard[king.location[0] + i].length) {

						if (king.isValidMove(king.location[0] + i, king.location[1] + j, locationBoard, moveCounter)
								&& ((locationBoard[king.location[0] + i][king.location[1] + j] == null)
										|| locationBoard[king.location[0] + i][king.location[1] + j] != null
												&& !(locationBoard[king.location[0] + i][king.location[1] + j].color
														.equalsIgnoreCase(playerColor)))) {
							Piece temp = locationBoard[king.location[0] + i][king.location[1] + j];
							locationBoard[king.location[0] + i][king.location[1] + j] = king;
							king.location[0] = king.location[0] + i;
							king.location[1] = king.location[1] + j;
						
							if (check(false).equalsIgnoreCase(playerColor)) {
								king.location[0] = selectedLocation[0];
								king.location[1] = selectedLocation[1];
								locationBoard[king.location[0] + i][king.location[1] + j] = temp;
							} else {
								king.location[0] = selectedLocation[0];
								king.location[1] = selectedLocation[1];
								locationBoard[king.location[0] + i][king.location[1] + j] = temp;
								return false;
							}
						}
					}
				}
			}
		}

		// check if other pieces can stop checkmate
		Iterator<Piece> w = tempSet.iterator();
		while (w.hasNext()) {
			Piece piece = w.next();
			if (piece instanceof King) {
				continue;
			}
			int[] selectedLocationPiece = {piece.location[0], piece.location[1]};
			for (int i = 0; i < locationBoard.length; i++) {
				for (int j = 0; j < locationBoard[i].length; j++) {


					if (piece.isValidMove(i, j, locationBoard, moveCounter) && (locationBoard[i][j] == null || locationBoard[i][j] != null && !(locationBoard[i][j].color.equalsIgnoreCase(playerColor)))) {
						Piece temp = locationBoard[i][j];
						piece.location[0] = i;
						piece.location[1] = j;
						locationBoard[i][j] = piece;
						if (check(false).equalsIgnoreCase(playerColor)) {
							piece.location[0] = selectedLocationPiece[0];
							piece.location[1] = selectedLocationPiece[1];
							locationBoard[i][j] = temp;
						} else {
							piece.location[0] = selectedLocationPiece[0];
							piece.location[1] = selectedLocationPiece[1];
							locationBoard[i][j] = temp;

							return false;
						}
					}
				
				}
			}

		}
		if (canBlockCheckmate(playerColor)) {
			return false;
		}

		return true;
	}

	/**
	 * Determines if the piece can succesfully be moved, and if so, moves it
	 * @param result the converted value, from input string to indices that can be worked with
	 * @param playerColor color of player moving piece
	 * @return boolean to determine if move was successful or not
	 */
	private boolean moveSuccessful(int[] result, String playerColor) {
		Piece selected = locationBoard[result[1]][result[0]];
		Piece target = locationBoard[result[3]][result[2]];
   		if (selected == null || !selected.color.equalsIgnoreCase(playerColor)
				|| target != null && target.color.equals(selected.color)) {
			return false;
		} else if (selected.isValidMove(result[3], result[2], locationBoard, moveCounter)) {
			if (selected instanceof Pawn && ((Pawn) selected).performedEnpassant) {
				Piece enpassantedPawn;
				if (selected.color.equals("white")) {
					enpassantedPawn = locationBoard[result[3] + 1][result[2]];
					aliveBlackPieces.remove(enpassantedPawn);
					locationBoard[result[3] + 1][result[2]] = null;
 				} else {
					enpassantedPawn = locationBoard[result[3] - 1][result[2]];
 					aliveWhitePieces.remove(enpassantedPawn);
					locationBoard[result[3] - 1][result[2]] = null;
				}
			}
			if (target != null && !(target instanceof King)) {
				if (playerColor.equalsIgnoreCase("white"))
					aliveBlackPieces.remove(target);
				else
					aliveWhitePieces.remove(target);
			}
			// move the piece
			int[] selectedLocation = selected.location;
			selected.location[0] = result[3];
			selected.location[1] = result[2];
			locationBoard[result[1]][result[0]] = null;
			locationBoard[result[3]][result[2]] = selected;

			// Used in castlingThroughCheck method
			int[] startLocation = {result[1], result[0]};
			if (selected instanceof King && (((King) selected).castle)) {
				if (((King) selected).castlingThroughCheck(selected.color.equalsIgnoreCase("white")
														? aliveBlackPieces
														: aliveWhitePieces, startLocation, locationBoard, moveCounter)) {
					selected.location[0] = result[1];
					selected.location[1] = result[0];
					locationBoard[result[3]][result[2]] = null;
					locationBoard[result[1]][result[0]] = selected;
					return false;
				}
				 if (selected.location[1] == 1) {
				 	locationBoard[selected.location[0]][2] = locationBoard[selected.location[0]][0];
				 	locationBoard[selected.location[0]][2].location[1] = 2;
				 	locationBoard[selected.location[0]][0] = null;
				 	locationBoard[selected.location[0]][4] = null;
				 } else {
					 locationBoard[selected.location[0]][5] = locationBoard[selected.location[0]][7];
					 locationBoard[selected.location[0]][5].location[1] = 5;
					 locationBoard[selected.location[0]][7] = null;
					 locationBoard[selected.location[0]][4] = null;
				 }
				 return true;
			}
			if (selected instanceof Pawn && (selected.location[0] == 0 || selected.location[0] == 7)) {
				Piece promotedPiece;
				if (promotionPiece == null || promotionPiece.equalsIgnoreCase("Q")) {
					promotedPiece = new Queen(selected.location[0], selected.location[1], selected.color);
				} else if (promotionPiece.equalsIgnoreCase("N")) {
					promotedPiece = new Knight(selected.location[0], selected.location[1], selected.color);
				} else if (promotionPiece.equalsIgnoreCase("R")) {
					promotedPiece = new Rook(selected.location[0], selected.location[1], selected.color);
				} else {
					promotedPiece = new Bishop(selected.location[0], selected.location[1], selected.color);
				}
				if (selected.color.equalsIgnoreCase("white")) {
					aliveWhitePieces.remove(selected);
					aliveWhitePieces.add(promotedPiece);
				} else {
					aliveBlackPieces.remove(selected);
					aliveWhitePieces.add(promotedPiece);
				}
				locationBoard[promotedPiece.location[0]][promotedPiece.location[1]] = promotedPiece;
			}
			String playerChecked = check(false);
			if (playerChecked.equalsIgnoreCase(playerColor)) { // reverse the move
				selected.location = selectedLocation;
				locationBoard[result[1]][result[0]] = selected;
				if (target != null) {
					locationBoard[result[3]][result[2]] = target;
					if (playerColor.equalsIgnoreCase("white"))
						aliveBlackPieces.add(target);
					else
						aliveWhitePieces.add(target);
				} else {
					locationBoard[result[3]][result[2]] = null;
				}
				return false;
			}
			selected.moved = true;
			return true;

		}
		return false;
	}

	/**
	 * Primary logic of game, runs until Checkmate, Draw or Resignation
	 */
	public void play() {
		Scanner scanner = new Scanner(System.in);
		printLocationBoard();
		while (true) {
			promotionPiece = null;
			boolean moveSuccessful = false;
			String move;
			String playerColor = "";
			String oppositeColor = ""; 
			if (moveCounter % 2 == 0) {
				playerColor = "White";
				oppositeColor = "Black"; 
			} else {
				playerColor = "Black";
				oppositeColor = "White";
			}
			System.out.printf("%s's Move: %n", playerColor);
			move = scanner.nextLine();
			String[] splitMove = move.split(" ");
			int[] interpretedString = new int[4];
			Piece selected = null;
			if (checkValidString(move) && splitMove.length > 1) {
				interpretedString = interpretString(move);
				selected = locationBoard[interpretedString[1]][interpretedString[0]];
				moveSuccessful = moveSuccessful(interpretedString, playerColor);
			}
			boolean playerCheckmated = checkMate(playerColor);
			if (playerCheckmated || checkMate(oppositeColor)) {
				printLocationBoard();
				System.out.println("Checkmate");
				System.out.printf("%s Wins", (playerCheckmated) ? oppositeColor : playerColor);
				scanner.close();
				break;
			}
			while (check(true).equalsIgnoreCase(playerColor)) {
                System.out.printf("%s's Move: %n", playerColor);
                move = scanner.nextLine();
				splitMove = move.split(" ");
				if (checkValidString(move) && splitMove.length > 1) {
					interpretedString = interpretString(move);
					moveSuccessful = moveSuccessful(interpretedString, playerColor);
				}
			}
			if (move.equals("resign")) {
				System.out.printf("%s wins", (playerColor.equals("White")) ? "Black" : "White");
				break;
			}
			if (splitMove[0].equals("draw") && drawRequested) {
				break;
			}

			System.out.println("");
			if (moveSuccessful) {
				// This means that the user inputted proper selected and target locations, and
				// requested draw
				if (splitMove.length == 3) {
					if (splitMove[2].equals("draw?")){
						drawRequested = true;
					}
				} else {
					drawRequested = false;
				}
  				selected.moveNumber = moveCounter;
				printLocationBoard();
				System.out.println("");
				moveCounter++;
			} else {
				System.out.println("Illegal move, try again");
			}
		}
		scanner.close();
	}

}
