package com.cs499.project3;

import java.util.*;

public class AdverserialSearch {
	static final int DIMENSION = 8, MAX = 30, DEPTH = 64;;
	static String[][] board = 
		{
				{" ", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8"},
				{"A", " X", " X", " X", " -", " -", " -", " -", " -"},
				{"B", " O", " O", " -", " -", " -", " -", " -", " -"},
				{"C", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"D", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"E", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"F", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"G", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"H", " -", " -", " -", " -", " -", " -", " -", " -"},
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
	    				String answer = cin.next(), move;
		    			if (answer.equals("y")) {
		    				while (true) {
		    					System.out.println();
			    				printBoard(board);
			    		    	System.out.print("\nMy current move is: ");
			    		    	move = cin.next();
			    		    	markOponentMoveOnBoard(move);
			    		    	if (isGameOver()) {
			    		    		System.out.println("\nOpponent wins");
			    		    		break;
			    		    	}
			    		    	miniMaxAlgorithm();
			    		    	if (isGameOver()) {
			    		    		System.out.println("\nComputer wins");
			    		    		break;
			    		    	}
		    				}
		    				break;
		    			}else if (answer.equals("n")) {
		    				while (true) {
		    					System.out.println();
			    				printBoard(board);
   			    		    	miniMaxAlgorithm();
			    		    	if (isGameOver()) {
			    		    		System.out.println("\nComputer wins");
			    		    		break;
			    		    	}
			    		    	System.out.println("\nChoose your next move: ");
			    		    	move = cin.next();
			    		    	markOponentMoveOnBoard(move);
			    		    	if (isGameOver()) {
			    		    		System.out.println("\nOpponent wins");
			    		    		break;
			    		    	}
		    				}
		    		    	break;
		    			}else{System.out.print("\nPlease enter either \"y\" or \"n\": ");}
					}
	    			break;
	    		} else {System.out.print("\nPlease enter a number in between or including 1 and 30: ");}
	    	}
	    }
		System.out.println();
	}

	
	public static void printBoard(String[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}
	
	public static void miniMaxAlgorithm() {
		int depth = DEPTH, currentValue = 0, bestValue = Integer.MIN_VALUE, 
				    newRow = 0, newCol = 0;
		String player = " X";
		
		if (isBoardEmpty()) markRandMoveOnBoard();
		else {
			for (int row = 1; row < board.length; row++) {
				for (int col = 1; col < board.length; col++) {
					if (board[row][col].equals(" -")) {
						board[row][col] = player;
						currentValue = minValue(depth-1);
						if (currentValue > bestValue) {
							newRow = row;
							newCol = col;
							bestValue = currentValue;
						}
						board[row][col] = " -";
					}
				}
			}
			System.out.println("\nMy current move is: " + newRow + newCol);
			board[newRow][newCol] = player;
			printBoard(board);
		}		
	}
	
	public static int minValue(int depth) {
		int bestValue = Integer.MAX_VALUE, currentValue = 0;
		String player = " O";
		if (isWinner() != 0) return isWinner();
		if (depth == 0) return 0;
		
		for (int row = 1; row < board.length; row++) {
			for (int col = 1; col < board.length; col++) {
				if (board[row][col].equals(" -")) {
					board[row][col] = player;
					currentValue = maxValue(depth-1);
					if (currentValue < bestValue) bestValue = currentValue;
					board[row][col] = " -";
				}
			}
		}
		return bestValue;
	}
	
	public static int maxValue(int depth) {
		int bestValue = Integer.MIN_VALUE, currentValue = 0;
		String player = " X";
		if (isWinner() != 0) return isWinner();
		if (depth == 0) return 0;
		
		for (int row = 1; row < board.length; row++) {
			for (int col = 1; col < board.length; col++) {
				if (board[row][col].equals(" -")) {
					board[row][col] = player;
					currentValue = minValue(depth-1);
					if (currentValue > bestValue) bestValue = currentValue;
					board[row][col] = " -";
				}
			}
		}
		return bestValue;
	}
		
	public static boolean isBoardEmpty() {
		for (int row = 1; row < board.length; row++) {
			for (int col = 1; col < board.length; col++) {
				if (!board[row][col].equals(" -")) return false;
			}
		}
		return true;
	}
	
	public static boolean isGameOver() {
		if (isWinner() != 0) return true;
		return false;
	}
	
	public static int isWinner() {
		int count = 0;
		String opponent = " O", computer = " X";
		
		for (int row = 1; row < board.length; row++) {
			for (int col = 1; col < board.length; col++) {
				if (board[row][col].equals(computer)) count++;
				else count = 0;
				
				if (count == 4) return 1;
			}
		}
		
		for (int col = 1; col < board.length; col++) {
			for (int row = 1; row < board.length; row++) {
				if (board[row][col].equals(computer)) count++;
				else count = 0;
				
				if (count == 4) return 1;
			}
		}

		for (int row = 1; row < board.length; row++) {
			for (int col = 1; col < board.length; col++) {
				if (board[row][col].equals(opponent)) count++;
				else count = 0;
				
				if (count == 4) return -1;
			}
		}
		
		for (int col = 1; col < board.length; col++) {
			for (int row = 1; row < board.length; row++) {
				if (board[row][col].equals(opponent)) count++;
				else count = 0;
				
				if (count == 4) return -1;
			}
		}
		
		return 0;
	}
	
	public static void markOponentMoveOnBoard(String move) {
		String letter = String.valueOf(move.charAt(0));
		String number = String.valueOf(move.charAt(1));
		
		addToBoardForPerson(letter, number);
	}
	
	public static void markRandMoveOnBoard() {
		int low = 97, high = 100;
		String letter = String.valueOf((char) (new Random().nextInt(high-low+1) + low)), 
			   number = String.valueOf(new Random().nextInt(3)+1);
		addToBoardForComp(letter, number);
	}
	
	public static void addToBoardForComp(String letter, String number) {
		int i1 = letter.charAt(0) - 'a' + 1;
		int i2 = Integer.parseInt(number);
		board[i1][i2] = board[i1][i2].replace("-", "X");
		System.out.println();
		printBoard(board);
	}
	
	public static void addToBoardForPerson(String letter, String number) {
		int i1 = letter.charAt(0) - 'a' + 1;
		int i2 = Integer.parseInt(number);
		board[i1][i2] = board[i1][i2].replace("-", "O");
		System.out.println();
		printBoard(board);
	}
}







































