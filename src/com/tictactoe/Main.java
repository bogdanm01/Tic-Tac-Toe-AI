package com.tictactoe;

import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            menuLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void menuLoop() throws InterruptedException {
        while (true) {
            System.out.print("Input command: ");
            String userInput = scan.nextLine();
            String[] inputArgumentsTokenized = userInput.split(" ");

            if (inputArgumentsTokenized[0].equalsIgnoreCase("exit"))
                break;
            
            if (!userInput.matches("(start) (easy|user) (easy|user)")) {
                System.out.println("Bad parameters");
            } else {
                // TODO: Run game in main method
                TicTacToeGame game = new TicTacToeGame(
                        inputArgumentsTokenized[1],
                        inputArgumentsTokenized[2],
                        new GameGrid_2DMatrix());
                game.run();
            }
        }
    }

}
