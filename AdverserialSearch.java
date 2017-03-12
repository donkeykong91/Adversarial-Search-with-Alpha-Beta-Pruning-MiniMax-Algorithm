package com.cs499.project3;

import java.util.*;

public class AdverserialSearch {
	static final int DIMENSION = 9, MAX = 30;
	static String opponent = " O";
;
	static String[][] board = 
		{
				{" ", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8"},
				{"A", " X", " X", " X", " -", " -", " -", " -", " -"},
				{"B", " -", " -", " -", " -", " -", " -", " -", " -"},
				{"C", " O", " O", " O", " -", " -", " -", " -", " -"},
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
	    				Node state = new Node();
	    				String answer = cin.next(), move;
		    			if (answer.equals("y")) {
		    				while (true) {
		    					System.out.println();
			    				printBoard(board);
			    		    	System.out.print("\nMy current move is: ");
			    		    	move = cin.next();
			    		    	markOponentMoveOnBoard(move);
			    		    	if (isWinner(opponent, move)) break;
	//		    		    	miniMaxAlgorithm(state);
//			    		    	System.out.print("\nChoose your next move: " + state.printMove());
			    		    	if (isWinner(state.getPlayer(), state.getPlayer())) break;
		    				}
		    				System.out.println("\nOpponent wins");
		    				break;
		    			}else if (answer.equals("n")) {
		    				while (true) {
		    					System.out.println();
			    				printBoard(board);
   	//		    		    	miniMaxAlgorithm(state);
//			    		    	System.out.print("\nMy current move is: " + state.printMove());
			    		    	if (isWinner(state.getPlayer(), state.getPlayer())) break;
			    		    	System.out.println("\nChoose your next move: ");
			    		    	move = cin.next();
			    		    	markOponentMoveOnBoard(move);
			    		    	if (isWinner(state.getPlayer(), move)) break;
//			    				state.setBoard(board);
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
	
	public static void miniMaxAlgorithm(Node state) {
		state = minValue();
	}
	
	public static Node minValue() {
		return null;
	}
	
	public static Node maxValue() {
		return null;
	}
	
	public static boolean terminalTest() {
		return true;
	}
	
	public static int utility() {
		return 0;
	}
	
	public static boolean isWinner(String player, String move) {
		int row = move.charAt(0) - 'a' + 1;
		int col = Integer.parseInt(String.valueOf(move.charAt(1)));
		int count = 0;

		for (int i = 1; i < DIMENSION; i++) {
			if (board[row][i].equals(player)) count++;
			else count = 0;
			
			if (count == 4) return true;
		}
		for (int i = 1; i < DIMENSION; i++) {
			if (board[i][col].equals(player)) count++;
			else count = 0;
			
			if (count == 4) return true;
		}
		return false;
	}
	
	public static void markOponentMoveOnBoard(String move) {
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

class Node {
	String player;
	private int value;
	String [][] newCurrentBoard = new String[5][5];
	Queue<Node> successorStates = new PriorityQueue<>();
	
	Node(){}
	
	Node (String[][] currentBoard) {
		System.arraycopy(currentBoard, 0, newCurrentBoard, 0, currentBoard.length);
	}
	
	Node (String[][] currentBoard, String player) {
		System.arraycopy(currentBoard, 0, newCurrentBoard, 0, currentBoard.length);
		this.player = player;
	}
	
	public void setBoard(String[][] newCurrentBoard) {
		System.arraycopy(newCurrentBoard, 0, this.newCurrentBoard, 0, newCurrentBoard.length);
	}
	
	public void setValue(int value) {this.value = value;}
	
	public void setPlayer(String player) {this.player = player;}
	
	public String[][] getBoard() {return newCurrentBoard;}
	
	public int getValue() {return value;}
	
	public String getPlayer() {return player;}
	
	public void printMove() {
		//print move by using array indeces that position "X"
	}
}

class SuccessorComparator implements Comparator<Node> {
	public int compare (Node firstSuccessor, Node secondSuccessor) {
		if (firstSuccessor.getValue() > secondSuccessor.getValue()) return -1;
		else if (secondSuccessor.getValue() > firstSuccessor.getValue()) return 1;
		return 0;
	}
}







































