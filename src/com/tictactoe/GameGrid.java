package com.tictactoe;

public interface GameGrid {
    void printGrid();
    void createGrid();
    boolean checkForWin(String sign);
    String getCell(int i, int j);
    void setCell(int i, int j, String s);
}
