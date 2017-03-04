package com.cs499.project3;

public class AdverserialSearch {
	static final int DIMENSION = 8;
	static int[] board = new int[DIMENSION];
	static int[] newBoard = new int[DIMENSION];
	
	public static void main(String[] args) {
		

	}

	public static void printBoard(int[] board) {
		String[][] board2D = new String[DIMENSION][DIMENSION];
		
		for (int row = 0; row < board2D.length; row++) {
			for (int col = 0; col < board2D[row].length; col++) {
				board2D[row][col] = "-";
			}
		}
		
		for (int col = 0; col < board2D.length; col++) {
			board2D[board[col]][col] = "Q";
		}
		
		for(int row = 0; row < board2D.length; row++) {
			for (int col = 0; col < board2D[row].length; col++) {
				System.out.print(board2D[row][col]);
			}
			System.out.println();
		}
	}
}
