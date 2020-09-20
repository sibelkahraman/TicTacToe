package com.kodluyoruz;


import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public class GameBoard {
    String[][] board;
    private int emptyCellNumber;
    public String[][] createBoard(int boardSize) {
        board = new String[boardSize][boardSize];
        emptyCellNumber = boardSize*boardSize;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++)
                board[i][j] = "";
        }
        return board;
    }

    public boolean isPlay(int x, int y) {
        int size = board.length;
        if(x >= size || y >= size)
            throw new IllegalArgumentException("Location Inputs Are Not Valid");
        return board[x][y].isEmpty();
    }

    public String[][] play(int x, int y) {
        board[x][y] = "X";
        emptyCellNumber--;
        playComputer();
        return board;
    }
    private void playComputer(){
        Random rand = new Random();
        while (true){
            int x = rand.nextInt(board.length);
            int y = rand.nextInt(board.length);
            if(isPlay(x,y)){
                board[x][y] = "O";
                emptyCellNumber--;
                break;
            }

        }
    }

    public int getEmptyCellNumber() {
        return emptyCellNumber;
    }

    public void setBoard(String[][] newBoard){
        board = newBoard;
    }

    public String checkGameStatus() {
        return null;
    }
}
