import java.util.*;
import static java.lang.System.out;

public class TicTacToe {
	
	//used so people can play with their names
	public static class Player{
		String name;
		int number;
		
		Player(String name, int order) {
			this.name = name;
			this.number = order;
		}
		
	}
		
	//creates board
	private static char[][] new_board() {
		char[][] board = new char[3][3];
		int i,j;
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
		return board;		
	}
	
	private static void printBoard(char[][] board) {
		int i,j;
		System.out.println("    0     1     2");
		System.out.println("-------------------");
		for(i = 0; i < 3; i++) {
			System.out.print(i);
			System.out.print("|");
			for(j = 0; j < 3; j++) {
				System.out.print("  " + board[i][j] + "  " + "|");
			}
			System.out.print(i);
			System.out.println();
			System.out.println("-------------------");
			}
		System.out.println("    0     1     2");
		}
	
	
	//asks user for location to place mark
	private static int[] get_move() {
		int row;
		int column;
		int x = 1;
		int[] coord = new int[2];
		do {
			try {
				System.out.print("What row would you like to select: ");
				Scanner scan = new Scanner(System.in);
				row = scan.nextInt();
				System.out.print("What column would you like to select: ");
				column = scan.nextInt();
				coord[0] = row;
				coord[1] = column;
				
				x = 2;
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, try again");
			}
		}while(x == 1);
		return coord;
	}
	
	//creates a new board and updates intended move on board
	private static char[][] make_move(char[][] old_board,int[] coords, int player) {
		char[][] new_board = old_board;
		if(!is_valid_move(old_board,coords)) {
			System.out.println("Invalid move, try again");
			do {
				coords = get_move();
			}while(!is_valid_move(old_board,coords));
		}
		if(player == 1) {
			new_board[coords[0]][coords[1]] = 'X';
		}else if(player == 2) {
			new_board[coords[0]][coords[1]] = 'O';
		}
		return new_board;
	}
	
	//checks if location to move is valid and is unmarked
	private static boolean is_valid_move(char[][] board,int []coords) {
		
		if(coords[0] >= 3 || coords[1] >= 3) {
			return false;
		}
		
		if(board[coords[0]][coords[1]] != '-') {
			return false;
		}
		return true;
	}
	
	//checks to see if three characters are the same
	private static boolean checkRowCol(char a, char b, char c) {
		if(a == '-' || b == '-' || c == '-') {
			return false;
		}
		if(a == b && b == c && a == c) {
			return true;
		}
		return false;
	}
	
	//check for row winner.
	private static boolean checkRow(char[][] board) {
		int i;
		for(i = 0; i < 3; i++) {
			if(checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkCol(char[][] board) {
		int i;
		for(i = 0; i < 3; i++) {
			if(checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkDiag(char[][] board) {
		if(checkRowCol(board[0][0],board[1][1],board[2][2]) == true) {
			return true;
		}
		
		if(checkRowCol(board[0][2],board[1][1],board[2][0]) == true) {
			return true;
		}
		return false;
	}
	
	private static boolean isBoardFull(char[][] board) {
		int i,j;
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 3; j++) {
				if(board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
	
	private static int winCheck(char[][] board) {
		//0 means keep going, no winner
		//1 means winner
		//2 means draw --- board is full
		int statusOfGame;
		if(checkRow(board) == true || checkCol(board) == true || checkDiag(board) == true) {
			statusOfGame = 1;
		}else {
			statusOfGame = 0;
		}
		
		if(isBoardFull(board)) {
			statusOfGame = 2;
		}
		return statusOfGame;
	}

	
	public static void main(String args[]) {
		System.out.print("Enter name of player1: ");
		Scanner sc = new Scanner(System.in);
		Player player1 = new Player(sc.nextLine(),1);
		System.out.print("Enter name of player2: ");
		Player player2 = new Player(sc.nextLine(),2);
		char[][] board = new_board();
		//int player1 = 1;
		//int player2 = 2;
		int player;
		int turn = 1;
		printBoard(board);
		boolean active = true;
		int[] coords = new int[2];
		while(active) {
			if(turn % 2 == 1) {
				player = player1.number;
			}else {
				player = player2.number;
			}
			coords = get_move();
			board = make_move(board,coords,player);
			System.out.println(" ");
			System.out.println("This is the current board: ");
			printBoard(board);
			int status = winCheck(board);
			if(status == 1) {
				active = false;
				if(turn % 2 == 1) {
					//System.out.println("Player1 is the WINNER");
					System.out.println("Congratulations, " + player1.name +" " + "is the WINNER");
				}else {
					System.out.println("Congratulations, " + player2.name + "is the WINNER");
					//System.out.println("Player2 is the WINNER");
				}
				break;
			}else if(status == 2) {
				active = false;
				System.out.println("DRAW");
				break;
			}else {
				turn++;
			}
		}
		
	}
	

}
