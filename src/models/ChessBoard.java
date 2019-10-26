package models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ChessBoard {

	private Piece[][] locationBoard = new Piece[8][8];
	private String[][] colorBoard = new String[8][8];
	private Set<Piece> aliveWhitePieces = new HashSet();
	private Set<Piece> aliveBlackPieces = new HashSet();
	private Piece bKing = new King(0, 4, "black");
	private Piece wKing = new King(7, 4, "white");
	boolean drawRequested = false;

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
			System.out.print(Integer.toString(locationBoard.length - i));
		}
		System.out.println("");
		System.out.print(" a  b  c  d  e  f  g  h");
		System.out.println("");

	}

	// Check if input is syntactically valid
	private boolean checkValidString(String input) {
		// Splits inputted string by space
		String[] splitInput = input.split(" ");
		// Greater than 3 in case 3rd word is "draw?"
		if (splitInput.length > 3) {
			return false;
		} else if (splitInput.length == 3 && !splitInput[2].equals("draw?")
				|| splitInput.length == 1 && !splitInput[0].equals("draw")) {
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

	// Convert letter squares to numbers on board
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

	private String check(boolean print) {

		Iterator<Piece> w = aliveWhitePieces.iterator();
		String checkPlayer = "";
		while (w.hasNext()) {
			Piece piece = w.next();
			if (piece.isValidMove(bKing.location[0], bKing.location[1], locationBoard)) {
				if(print) System.out.println("Check black");
				checkPlayer = "black";
			}
		}

		Iterator<Piece> b = aliveBlackPieces.iterator();
		while (b.hasNext()) {
			Piece piece = b.next();
			if (piece.isValidMove(wKing.location[0], wKing.location[1], locationBoard)) {
				if(print) System.out.println("Check white");
				checkPlayer = "white";
			}
		}

		return checkPlayer;

	}

	// move the requested piece
	private boolean moveSuccessful(int[] result, String playerColor) {
		Piece selected = locationBoard[result[1]][result[0]];
		Piece target = locationBoard[result[3]][result[2]];
		if (selected == null || !selected.color.equalsIgnoreCase(playerColor)
				|| target != null && target.color.equals(selected.color)) {
			return false;
		} else if (selected.isValidMove(result[3], result[2], locationBoard)) {
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
			String playerChecked = check(false); 
			if (playerChecked.equalsIgnoreCase(playerColor)) { // reverse the move
				selected.location = selectedLocation;
				locationBoard[result[1]][result[0]] = selected;
				if(target != null) {
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

	public void play() {
		Scanner scanner = new Scanner(System.in);
		int i = 0;
		printLocationBoard();
		while (true) {
			boolean moveSuccessful = false;
			String move;
			String playerColor = "";
			if (i % 2 == 0) {
				playerColor = "White";
			} else {
				playerColor = "Black";
			}
			System.out.printf("%s's Move: %n", playerColor);
			move = scanner.nextLine();
			String[] splitMove = move.split(" ");

			if (checkValidString(move) && splitMove.length > 1) {
				moveSuccessful = moveSuccessful(interpretString(move), playerColor);
			}
			while(check(true).equalsIgnoreCase(playerColor)) {
				System.out.printf("%s's Move: %n", playerColor);
				move = scanner.nextLine();
				splitMove = move.split(" ");
				if (checkValidString(move) && splitMove.length > 1) {
					moveSuccessful = moveSuccessful(interpretString(move), playerColor);
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
					drawRequested = true;
				} else {
					drawRequested = false;
				}
				printLocationBoard();
				System.out.println("");
				i++;
			} else {
				System.out.println("Illegal move, try again");
			}
		}
		scanner.close();
	}

}
