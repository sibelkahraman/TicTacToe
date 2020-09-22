//Sibel Kahraman sibelkahraman235@gmail.com
//Ramazan Nejdet Sarıkaya necsrky@gmail.com

package com.kodluyoruz;

import java.util.Random;

public class TicTacToe {
    int[][] board;
    private int emptyCellNumber;

    public int[][] createBoard(int boardSize) {
        board = new int[boardSize][boardSize];
        emptyCellNumber = boardSize * boardSize;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
        }
        return board;
    }

    public boolean isPlay(int x, int y) {
        int size = board.length;
        if (x >= size || y >= size)
            throw new IllegalArgumentException("Location Inputs Are Not Valid");
        return board[x][y] == 0;
    }

    public int[][] play(int x, int y) {
        board[x][y] = 1;//X
        emptyCellNumber--;
        playComputer();
        showGameBoard();
        return board;
    }

    private void playComputer() {
        Random rand = new Random();
        while (true) {
            int x = rand.nextInt(board.length);
            int y = rand.nextInt(board.length);
            if (isPlay(x, y)) {
                board[x][y] = -1;//O
                emptyCellNumber--;
                break;
            }

        }
    }

    public int getEmptyCellNumber() {
        return emptyCellNumber;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    public String checkGameStatus() {
        int totalHorizontalValue = 0;
        int totalVerticalValue = 0;
        int totalCrossValue = 0;//    \ cross
        int totalCrossValue2 = 0;//   / cross
        int size = board.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    for (int k = 0; k < size; k++) {
                        totalHorizontalValue += board[k][j];
                        totalVerticalValue += board[i][k];
                        totalCrossValue += board[k][k];
                        totalCrossValue2 += board[k][size-1-k];
                    }
                    if (totalHorizontalValue == size || totalVerticalValue == size || totalCrossValue == size || totalCrossValue2 == size)
                        return "X";
                    if (totalHorizontalValue == -size || totalVerticalValue == -size || totalCrossValue == -size || totalCrossValue2 == -size)
                        return "O";
                    totalHorizontalValue = 0;
                    totalVerticalValue = 0;
                    totalCrossValue = 0;
                    totalCrossValue2 = 0;
                }
            }
        }
        return "Draw";
    }

    public void showGameBoard() {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != 0)
                    System.out.print(board[i][j] == 1 ? "X " : "O ");
                else
                    System.out.print("- ");
            }
            System.out.println("");
        }
    }
}

