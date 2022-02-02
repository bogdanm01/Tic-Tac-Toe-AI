package com.tictactoe;

import javax.swing.plaf.ScrollBarUI;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
    private static String[][] gameGrid;
    static Scanner scan = new Scanner(System.in);

    public static void run() {
        String input = getInitialCells();
        char[] charArray = input.toCharArray();
        gameGrid = createGrid(charArray);
        printGrid();

        playMove();

        printGrid();

        printResult();
    }

    private static void printResult(){
        if(checkGameStatus() == 1) {
            System.out.println("X wins");
        } else if(checkGameStatus() == 2) {
            System.out.println("O wins");
        } else if(checkGameStatus() == 3) {
            System.out.println("Game not finished");
        } else {
            System.out.println("Draw");
        }
    }

    private static int checkGameStatus() {
        boolean winX = checkForWin("X");
        boolean winO = checkForWin("O");
        boolean notFinished = isGridFull();

        if(winX) { return 1; }
        else if(winO) { return 2; }
        else if(!notFinished) { return 3; }

        return 0;
    }

    private static boolean isGridFull(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameGrid[i][j].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkForWin(String sign) {
        for(int i = 0; i < 3; i++) {
            if((gameGrid[i][0].equals(sign) && gameGrid[i][1].equals(sign) && gameGrid[i][2].equals(sign))) {
                return true;
            }
            if((gameGrid[0][i].equals(sign) && gameGrid[1][i].equals(sign) && gameGrid[2][i].equals(sign))) {
                return true;
            }
        }

        if((gameGrid[0][0].equals(sign) && gameGrid[1][1].equals(sign) && gameGrid[2][2].equals(sign))) {
            return true;
        }

        return gameGrid[2][0].equals(sign) && gameGrid[1][1].equals(sign) && gameGrid[0][2].equals(sign);
    }

    private static void playMove() {
        while(true) {
            System.out.print("Enter the coordinates: ");
            String[] coordinatesInput = scan.nextLine().split(" ");

            int i=0, j=0;

            try {
                i = Integer.parseInt(coordinatesInput[0]) - 1;
                j = Integer.parseInt(coordinatesInput[1]) - 1;
            }
            catch (NumberFormatException NFE) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (!(i >= 0 && i < 3 && j >= 0 && j < 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                if(gameGrid[i][j].equals(" ")) {
                    gameGrid[i][j] = getPlaySign();
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        }
    }

    private static String getPlaySign() {
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameGrid[i][j].equals("X")) {
                    countX++;
                } else if(gameGrid[i][j].equals("O")) {
                    countO++;
                }
            }
        }

        return countX == countO ? "X" : "O";
    }

    private static String[][] createGrid(char[] places) {
        String[][] grid = new String[3][3];
        int k = 0;

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = String.valueOf(places[k++]);
            }
        }

        return grid;
    }

    private static void printGrid() {
        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| ");
                }
                if (j != 2) {
                    System.out.print(gameGrid[i][j] + " ");
                } else {
                    System.out.print(gameGrid[i][j] + " |");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    private static String getInitialCells() {
        String acceptedChars = "_XO";
        String cellsStr;

        while(true) {
            System.out.print("Enter the cells: ");
            String temp = scan.nextLine();

            if(temp.toUpperCase().matches(".*[XO_].*") && temp.length() == 9) {
                cellsStr = temp.replace('_',' ');
                break;
            } else {
                System.out.println("Invalid input!\n");
            }
        }

        return cellsStr;
    }
}
