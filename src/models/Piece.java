package models;

public abstract class Piece {
	
	boolean isAlive;
	public String name;
	int[] location = new int[2];
	String color;
	boolean moved;

	Piece(int x, int y, String color) {
		this.isAlive = true;
		this.color = color; 
		this.location[0] = x;
		this.location[1] = y; 
	}
	
	abstract boolean isValidMove(int x, int y, Piece[][] locationBoard);





}
