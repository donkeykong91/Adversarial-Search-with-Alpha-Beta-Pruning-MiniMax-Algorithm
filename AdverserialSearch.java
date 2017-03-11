package com.cs499.project3;

import java.util.*;

public class AdverserialSearch {
	static final int DIMENSION = 9, MAX = 30;
	static String player;
	static String[][] board = 
		{
				{" ", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8"},
				{"A", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"B", " -", " -", " -", " -", " -", " -", " -", " -"},
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
	    				String answer = cin.next();
		    			if (answer.equals("y")) {
		    				player = "opponent";
		    				System.out.println();
		    				printBoard(board);
		    		    	System.out.print("\nMy current move is: ");
		    		    	String move = cin.next();
		    		    	markMoveOnBoard(move);
		    		    	miniMaxAlgorithm(move);
		    				break;
		    			}else if (answer.equals("n")) {
		    				player = "computer";
		    				System.out.println();
		    				printBoard(board);
		    		    	System.out.print("\nMy current move is: " + markRandMoveOnBoard());
//		    		    	miniMaxAlgorithm(move);
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
	
	public static void miniMaxAlgorithm(String move) {
		// TODO Auto-generated method stub

	}
	
	public static void markMoveOnBoard(String move) {
		String letter = String.valueOf(move.charAt(0));
		String number = String.valueOf(move.charAt(1));
		
		addToBoardForPerson(letter, number);
	}
	
	public static String markRandMoveOnBoard() {
		int low = 97, high = 104;
		String letter = String.valueOf((char) (new Random().nextInt(high-low+1) + low)), 
			   number = String.valueOf(new Random().nextInt(7)+1);
		addToBoardForComp(letter, number);
		return letter+number;
	}
	
	public static void addToBoardForComp(String letter, String number) {
		int i1 = letter.charAt(0) - 'a' + 1;
		int i2 = Integer.parseInt(number);
		board[i1][i2] = board[i1][i2].replace("-", "X");
		printBoard(board);
	}
	
	public static void addToBoardForPerson(String letter, String number) {
		int i1 = letter.charAt(0) - 'a' + 1;
		int i2 = Integer.parseInt(number);
		board[i1][i2] = board[i1][i2].replace("-", "O");
		printBoard(board);
	}
}

class Node {
	private int value;
	String [][] newCurrentBoard = new String[9][9];
	Queue<Node> successorStates = new PriorityQueue<>();
	
	Node(){}
	
	Node (String[][] currentBoard) {
		System.arraycopy(currentBoard, 0, newCurrentBoard, 0, currentBoard.length);
	}
	
	public void setValue(int value) {this.value = value;}
	
	public int getValue() {return value;}
}

class SuccessorComparator implements Comparator<Node> {
	public int compare (Node firstSuccessor, Node secondSuccessor) {
		if (firstSuccessor.getValue() > secondSuccessor.getValue()) return -1;
		else if (secondSuccessor.getValue() > firstSuccessor.getValue()) return 1;
		return 0;
	}
}







































