package com.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class GamePlayerHandler {
    static Scanner scan = new Scanner(System.in);
    private final GameGrid gameGrid;
    private final String difficulty;
    private GameSign currentGameSign = GameSign.X;

    public GamePlayerHandler(String difficulty) {
        this.gameGrid = new GameGrid_2DMatrix();
        this.difficulty = difficulty;
    }

    protected void playUserUser() {
        while (checkGameStatus() == 0 && gameStillPlaying()) {
            gameGrid.printGrid();
            playerMove();
        }
        gameGrid.printGrid();
    }

    protected void playUserEasy() {
        while (checkGameStatus() == 0 && gameStillPlaying()) {
            gameGrid.printGrid();
            playerMove();
            gameGrid.printGrid();
            computerMove();
        }
    }

    protected void playEasyUser() {
        while (checkGameStatus() == 0 && gameStillPlaying()) {
            gameGrid.printGrid();
            computerMove();
            gameGrid.printGrid();
            playerMove();
        }
        gameGrid.printGrid();
    }

    protected void playEasyEasy() {
        while (checkGameStatus() == 0 && gameStillPlaying()) {
            gameGrid.printGrid();
            computerMove();
        }
        gameGrid.printGrid();
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

    private boolean gameStillPlaying() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameGrid.getCell(i, j).equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getPlaySign() {
        if (currentGameSign == GameSign.X) {
            currentGameSign = GameSign.O;
            return "X";
        }

        currentGameSign = GameSign.X;
        return "O";
    }

    int checkGameStatus() {
        boolean winX = gameGrid.checkForWin("X");
        boolean winO = gameGrid.checkForWin("O");

        if (winX) { return 1; }
        else if (winO) { return 2; }

        return 0; // draw
    }
}
