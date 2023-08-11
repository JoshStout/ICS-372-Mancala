package edu.metrostate.ics372.p5;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean gameOver = false;
		boolean anotherTurn = false;
		boolean seedsAdded;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("how many seeds per pit?");
			int answer = scanner.nextInt();
			scanner.nextLine();
			Mancala mancala = new Mancala(answer);
			System.out.println(mancala.toString());
			System.out.println();
			while(!gameOver) {
				System.out.println("player " + mancala.getCurrentPlayer().getEnum() + " select a pit");
				answer = scanner.nextInt();
				scanner.nextLine();
				seedsAdded = mancala.selectPit(answer);
				if(seedsAdded) {
					System.out.println(mancala.toString());
					System.out.println();
					anotherTurn = false;
					anotherTurn = mancala.anotherTurn();
					if(anotherTurn) {
						System.out.println("player " + mancala.getCurrentPlayer().getEnum() + " gets another turn");
					}
					gameOver = mancala.gameOver();
				}else {
					System.out.println("incorrect pit, try again");
				}
			}
			System.out.println("GAME OVER");
			System.out.println("FINAL SCORE");
			System.out.println("PLAYER ONE: " + mancala.getPlayerOneScore());
			System.out.println("PLAYER TWO: " + mancala.getPlayerTwoScore());
		}		
	}
}
