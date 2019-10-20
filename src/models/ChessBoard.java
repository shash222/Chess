package models;

import java.util.Scanner;


public class ChessBoard {

    private Piece[][] locationBoard = new Piece[8][8];
    private String[][] colorBoard = new String[8][8];


    public ChessBoard() {
        for(int i = 0; i <= 7; i++) {
            locationBoard[1][i] = new Pawn(1,i,"black");
            locationBoard[6][i] = new Pawn(6,i,"white");
        }
        locationBoard[0][0] = new Rook(0,0, "black"); 
        locationBoard[0][7] = new Rook(0,7, "black"); 
        locationBoard[7][0] = new Rook(7,0, "white"); 
        locationBoard[7][7] = new Rook(7,7, "white"); 
        
        locationBoard[7][1] = new Knight(7,1, "white"); 
        locationBoard[7][6] = new Knight(7,6, "white"); 
        locationBoard[0][6] = new Knight(0,6, "black"); 
        locationBoard[0][1] = new Knight(0,1, "black"); 
        
        locationBoard[0][2] = new Bishop(0,2, "black"); 
        locationBoard[0][5] = new Bishop(0,5, "black"); 
        locationBoard[7][2] = new Bishop(7,2, "white"); 
        locationBoard[7][5] = new Bishop(7,5, "white"); 
        
        locationBoard[0][4] = new King(0,4, "black"); 
        locationBoard[7][4] = new King(7,4, "white"); 
        
        locationBoard[0][3] = new Queen(0,3, "black"); 
        locationBoard[7][3] = new Queen(7,3, "white"); 
        
        for(int i = 0; i < colorBoard.length; i++) {
        	for(int j = 0; j < colorBoard[i].length; j++) {
        		if(i % 2 == 0) {
        			if(j % 2 == 1) {
        				colorBoard[i][j] = "##"; 
        			} else {
        				colorBoard[i][j] = "  ";
        			}
        		} else {
        			if(j % 2 == 0) {
        				colorBoard[i][j] = "##"; 
        			} else {
        				colorBoard[i][j] = "  ";
        			}
        		}
        		
        	}
        }
    }

    public void printLocationBoard() {
        for(int i = 0; i < locationBoard.length; i++) {
            if(i != 0) System.out.println("");
            
            for(int j = 0; j < locationBoard[i].length; j++) {
                if(locationBoard[i][j] != null) {
                    System.out.print(locationBoard[i][j].name + " ");
                } else {
                    System.out.print(colorBoard[i][j] + " ");
                }
            }
            System.out.print(Integer.toString(locationBoard.length-i));
        }
        System.out.println(""); 
        System.out.print(" a  b  c  d  e  f  g  h");
		System.out.println(""); 

    }
    
    // Check if input is syntatically valid
    private boolean checkValidString(String str) {
    	str = str.replaceAll("\\s+","");
    	if(str.length() > 4) {
    		return false; 
    	}
        boolean[] check =  new boolean[4];

    	for(int i = 0; i < str.length(); i++) {
            String letters = "abcdefgh"; 

            // letter
            if(i % 2 == 0) {
            	 for(int z = 0; z < letters.length();z++) {
                 	if(str.charAt(i)==letters.charAt(z)) {
             			check[i] = true; 
             		}
                 }
            } else {
            	// number
            	String numbers = "12345678"; 
            	 for(int z = 0; z < numbers.length(); z++) {
                  	if(str.charAt(i)==numbers.charAt(z)) {
              			check[i] = true; 
              		}
                  }
            }
    	}
    	for(int i = 0; i < check.length; i++) {
    		if(check[i] == false) return false; 
    	}
    	return true; 
    }
    
    
    // Convert letter squares to numbers on board
    private int[] interpretString(String str) {
    	str = str.replaceAll("\\s+","");
    	int[] result = new int[4]; 
    	for(int i = 0; i < str.length(); i++) {
    		if(str.charAt(i) == 'a') {
    			result[i] = 0; 
        	} else if(str.charAt(i) == 'b') {
    			result[i] = 1; 
        	} else if(str.charAt(i) == 'c') {
    			result[i] = 2; 
        	} else if(str.charAt(i) == 'd') {
    			result[i] = 3; 
        	} else if(str.charAt(i) == 'e') {
    			result[i] = 4; 
        	} else if(str.charAt(i) == 'f') {
    			result[i] = 5; 
        	} else if(str.charAt(i) == 'g') {
    			result[i] = 6; 
        	} else if(str.charAt(i) == 'h') {
    			result[i] = 7; 
        	} else {
    			result[i] = Math.abs(locationBoard.length - Integer.parseInt(String.valueOf(str.charAt(i)))); 
          	}
        	// System.out.println(result[i]);

    	}

    	return result; 
    	
    }
    
    
    
  
    
    // move the requested piece 
    private void move(int[] result) {
    	
    	if(locationBoard[result[1]][result[0]] == null) {
    		System.out.println("No piece is available on your selection."); 
    		return; 
    	}
    	Piece selected = locationBoard[result[1]][result[0]]; 
    	if(selected.isValidMove(result[3], result[2], locationBoard)) {
    		// move the piece 
    		selected.location[0] = result[3]; 
    		selected.location[1] = result[2]; 
    		locationBoard[result[1]][result[0]] = null; 
    		locationBoard[result[3]][result[2]] = selected; 
    		selected.moved = true; 


    	} else {
    		System.out.println("Invalid move per Chess regulations."); 
    		return; 
    	}
    	
    	
    }
    
    public void play() {
        Scanner scanner = new Scanner(System.in);
    	int i = 0;
    	while(true) {
    		String move; 
        	printLocationBoard(); 
			System.out.println("");

    		if(i % 2 == 0) {
    			System.out.print("White's Move: "); 
    			move = scanner.nextLine(); 
    		} else {
    			System.out.print("Black's Move: "); 
    			move = scanner.nextLine(); 
    		}
			if(checkValidString(move)) {
				move(interpretString(move)); 
			}; 
    		i++; 
    		if(move == "quit") {
    			break; 
    		}
			System.out.println("");

    	}
    	scanner.close(); 
    }

    public Piece[][] getLocationBoard() {
        return this.locationBoard;
    }


}
