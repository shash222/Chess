package models;

public abstract class Piece {
	
	boolean isAlive;
	public String name;
	int[] location = new int[2];
	String color;
	boolean moved;

	Piece(int r, int c, String color) {
		this.isAlive = true;
		this.color = color; 
		this.location[0] = r;
		this.location[1] = c; 
		this.moved = false; 
	}
	
	abstract boolean isValidMove(int r, int c, Piece[][] locationBoard);





}
