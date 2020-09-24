//Sibel Kahraman sibelkahraman235@gmail.com
//Ramazan Nejdet Sarıkaya necsrky@gmail.com

package com.kodluyoruz;

import lombok.Getter;
import java.util.Arrays;
import java.util.Random;

@Getter
public class TicTacToe {
    private int[][] board;
    private int emptyCellNumber;

    public void createBoard(int boardSize) {
        board = new int[boardSize][boardSize];
        emptyCellNumber = boardSize * boardSize;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
        }
     }

    public boolean isPlay(int x, int y) {
        int size = board.length;
        if (x >= size || y >= size)
            throw new IllegalArgumentException("Location Inputs Are Not Valid");
        return board[x][y] == 0;
    }

    public void play(int x, int y) {
        board[x][y] = 1;//X
        emptyCellNumber--;
        playComputer();
        showGameBoard();
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
        if(checkHorizontalGameStatus(board.length) != "") return checkHorizontalGameStatus(board.length);
        else if(checkVerticalGameStatus(board.length) != "") return checkVerticalGameStatus(board.length);
        else if(checkCrossBackslashGameStatus(board.length) != "") return checkCrossBackslashGameStatus(board.length);
        else if(checkCrossForwardSlashGameStatus(board.length) != "") return checkCrossForwardSlashGameStatus(board.length);
        return "Draw";
    }
    public String checkHorizontalGameStatus(int size){
        int totalHorizontalValue = 0;
        for(int i = 0;i<size;i++){
            totalHorizontalValue = Arrays.stream(board[i]).sum();
            if(totalHorizontalValue == size ) return "X";
            if(totalHorizontalValue == -size ) return "O";
        }
        return "";
    }
    public String checkVerticalGameStatus(int size){
        int totalVerticalValue = 0;
        int [][] transposeBoard = transposeArray(size);
        for(int i = 0;i<size;i++){
            totalVerticalValue = Arrays.stream(transposeBoard[i]).sum();
            if(totalVerticalValue == size ) return "X";
            if(totalVerticalValue == -size ) return "O";
        }
        return "";
    }

    public String checkCrossBackslashGameStatus(int size){
        int totalCrossValue = 0;
        System.out.println(size);
        for(int i = 0;i<size;i++){
            totalCrossValue += board[i][i];
        }
        showGameBoard();
        System.out.println(totalCrossValue);
        if(totalCrossValue == size ) return "X";
        if(totalCrossValue == -size ) return "O";
        return "";
    }
    public String checkCrossForwardSlashGameStatus(int size){
        int totalCrossValue = 0;
        for(int i = 0;i<size;i++){
            totalCrossValue += board[i][size-1-i];
        }
        if(totalCrossValue == size ) return "X";
        if(totalCrossValue == -size ) return "O";
        return "";
    }

    private int[][] transposeArray(int size){
        int[][] transpose = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                transpose[i][j]=board[j][i];
            }
        }
        return transpose;
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

