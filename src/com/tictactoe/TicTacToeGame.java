package com.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    static Scanner scan = new Scanner(System.in);

    private final GameGrid gameGrid;
    private GameMode gameMode;
    private String difficulty;

    private void setDifficulty (String playerOne, String playerTwo) {
        if (playerOne.matches("(easy)") || playerTwo.matches("(easy)")) {
            this.difficulty = "easy";
        }
    }

    private void setGameMode(String playerOne, String playerTwo) {
        if (playerOne.equalsIgnoreCase("easy") && playerTwo.equals("easy"))
            gameMode = GameMode.EASY_EASY;
        else if (playerOne.equalsIgnoreCase("easy") && playerTwo.equals("user"))
            gameMode = GameMode.EASY_USER;
        else if (playerOne.equalsIgnoreCase("user") && playerTwo.equals("user"))
            gameMode = GameMode.USER_USER;
        else if (playerOne.equalsIgnoreCase("user") && playerTwo.equals("easy"))
            gameMode = GameMode.USER_EASY;
    }

    public TicTacToeGame(String playerOne, String playerTwo, GameGrid gameGrid) {
        setGameMode(playerOne, playerTwo);
        setDifficulty(playerOne, playerTwo);
        this.gameGrid = gameGrid;
    }

    public void run(){
        if(gameMode == GameMode.EASY_EASY) {
            while(checkGameStatus() == 0 && !isGameFinished()) {
                gameGrid.printGrid();
                computerMove();
            }
            gameGrid.printGrid();
            printResult();
        } else {
            while (checkGameStatus() == 0 && !isGameFinished()) {
                gameGrid.printGrid();
                playerMove();
                gameGrid.printGrid();
                computerMove();
            }
            printResult();
        }
    }

    private void computerMove() {
        if (checkGameStatus() == 0) {
            System.out.println("Making move level \"" + difficulty + "\"");
            Random rng = new Random();

            while(true) {
                int i = rng.nextInt(3);
                int j = rng.nextInt(3);
                if(gameGrid.getCell(i,j).equals(" ")) {
                    gameGrid.setCell(i, j, getPlaySign());
                    break;
                }
            }
        }
    }

    public int checkGameStatus() {
        boolean winX = gameGrid.checkForWin("X");
        boolean winO = gameGrid.checkForWin("O");

        if(winX) { return 1; }
        else if(winO) { return 2; }

        return 0;
    }

    private void playerMove() {
        while(true) {
            int i, j;
            System.out.print("Enter the coordinates: ");
            String[] coordinatesInput = scan.nextLine().split(" ");

            try {
                i = Integer.parseInt(coordinatesInput[0]) - 1;
                j = Integer.parseInt(coordinatesInput[1]) - 1;
            }
            catch (NumberFormatException | IndexOutOfBoundsException exception) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (!(i >= 0 && i < 3 && j >= 0 && j < 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else if(gameGrid.getCell(i,j).equals(" ")) {
                gameGrid.setCell(i, j, getPlaySign());
                break;
            }
            else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private boolean isGameFinished() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameGrid.getCell(i, j).equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getPlaySign() {
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameGrid.getCell(i, j).equals("X")) {
                    countX++;
                } else if(gameGrid.getCell(i, j).equals("O")) {
                    countO++;
                }
            }
        }

        return countX == countO ? "X" : "O";
    }

    public void printResult() {
        if(checkGameStatus() == 1) { System.out.println("X wins"); }
        else if(checkGameStatus() == 2) { System.out.println("O wins"); }
        else { System.out.println("Draw"); }
    }
}
