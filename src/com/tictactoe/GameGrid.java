package com.tictactoe;

public class GameGrid {

    private static String[][] gameGrid;

    public GameGrid(){
        createGrid();
    }

    public void printGrid() {
        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) { System.out.print("| "); }
                if (j != 2) { System.out.print(gameGrid[i][j] + " "); }
                else { System.out.print(gameGrid[i][j] + " |"); }
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public void createGrid() {
        gameGrid = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameGrid[i][j] = " ";
            }
        }
    }

    public boolean checkForWin(String sign) {
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

    public String getCell(int i, int j) {
        return gameGrid[i][j];
    }

    public void setCell(int i, int j, String s) {
        gameGrid[i][j] = s;
    }
}
