package com.tictactoe;

public class TicTacToeGame {
    private GameMode gameMode;
    private final GamePlayerHandler gph;

    public TicTacToeGame(String playerOne, String playerTwo) {
        setGameMode(playerOne, playerTwo);
        setDifficulty(playerOne, playerTwo);
        this.gph = new GamePlayerHandler(setDifficulty(playerOne, playerTwo));
    }

    private void setGameMode(String playerOne, String playerTwo) {
        if (playerOne.equalsIgnoreCase("easy") && playerTwo.equals("easy")) {
            gameMode = GameMode.EASY_EASY;
        } else if (playerOne.equalsIgnoreCase("easy") && playerTwo.equals("user")) {
            gameMode = GameMode.EASY_USER;
        } else if (playerOne.equalsIgnoreCase("user") && playerTwo.equals("user")) {
            gameMode = GameMode.USER_USER;
        } else if (playerOne.equalsIgnoreCase("user") && playerTwo.equals("easy")) {
            gameMode = GameMode.USER_EASY;
        }
    }

    public void run() {
        switch (gameMode) {
            case EASY_EASY -> gph.playEasyEasy();
            case EASY_USER -> gph.playEasyUser();
            case USER_EASY -> gph.playUserEasy();
            case USER_USER -> gph.playUserUser();
        }
        printResult();
    }

    public void printResult() {
        if (gph.checkGameStatus() == 1) { System.out.println("X wins"); }
        else if(gph.checkGameStatus() == 2) { System.out.println("O wins"); }
        else { System.out.println("Draw"); }
    }

    private String setDifficulty (String playerOne, String playerTwo) {
        if (playerOne.matches("(easy)") || playerTwo.matches("(easy)")) {
            return "easy";
        }
        return "hard";
    }
}
