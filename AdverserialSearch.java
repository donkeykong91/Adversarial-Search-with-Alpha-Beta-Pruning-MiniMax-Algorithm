package com.cs499.project3;

import java.util.Scanner;

public class AdverserialSearch {
	static final int DIMENSION = 8, MAX = 30;
	static int[] board = new int[DIMENSION];
	static int[] newBoard = new int[DIMENSION];
	static String[][] edgeLabels = 
		{
				{"  1", " 2", " 3", " 4", " 5", " 6", " 7", " 8"},
				{"A ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"B ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"C ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"D ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"E ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"F ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"G ", "-", " -", " -", " -", " -", " -", " -", " -"},
				{"H ", "-", " -", " -", " -", " -", " -", " -", " -"},
		};	
	
	public static void main(String[] args) {
	    System.out.print("Adverserial Search\n\n" + 
	    				 "IDS, MiniMax Algorithm, Alpha-Beta Search\n\n" +
	    				 "Please enter max time to make a move in seconds(0 to 30): ");
	    
	    try(Scanner cin = new Scanner(System.in)) {
	    	while (true) {
	    		int timeLimit = cin.nextInt();
	    		if (timeLimit <= MAX){
	    			System.out.print("\nWould you like to go first?(y or n): ");
	    			while (true) {
	    				String answer = cin.next();
		    			if (answer.equals("y")) {
		    				System.out.println();
		    				printBoard(edgeLabels);
		    		    	System.out.print("\nMy current move is: ");
		    		    	String move = cin.next();
		    		    	
		    				break;
		    			}else if (answer.equals("n")) {
		    				System.out.println();
		    				printBoard(edgeLabels);
		    		    	System.out.print("\nMy current move is: ");
		    		    	String move = cin.next();
		    		    	
		    		    	break;
		    			}else{System.out.print("\nPlease enter either \"y\" or \"n\": ");}

					}
	    			break;
	    		} else {System.out.print("\nPlease enter a number in between or including 1 and 30: ");}
	    	}
	    }
		System.out.println();
	}

	public static void printBoard(int[] board) {
		String[][] board2D = new String[DIMENSION][DIMENSION];
		
		for (int row = 0; row < board2D.length; row++) {
			for (int col = 0; col < board2D[row].length; col++) {
				board2D[row][col] = "- ";
			}
		}
		
		for (int col = 0; col < board2D.length; col++) {
			board2D[board[col]][col] = "Q ";
		}
		
		for(int row = 0; row < board2D.length; row++) {
			for (int col = 0; col < board2D[row].length; col++) {
				System.out.print(board2D[row][col]);
			}
			System.out.println();
		}
	}
	
	public static void printBoard(String[][] edgeLabels) {
		for (int row = 0; row < edgeLabels.length; row++) {
			for (int col = 0; col < edgeLabels[row].length; col++) {
				System.out.print(edgeLabels[row][col]);
			}
			System.out.println();
		}
	}
}











































