package com.tictactoe;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //ask user to enter difficulty
        var game = new TicTacToeGame("easy");
        game.run();
    }
}
