package com.kodluyoruz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class TicTacToeTest {
    GameBoard sut;
    String[][] board;
    int boardSize = 3;
    @BeforeEach
    public void Setup(){
        sut = new GameBoard();
        board = sut.createBoard(boardSize);
    }
    @Test
    public void createGameBoard_WhenGivenNForSize_ShouldReturnNxNBoard(){
        //Arrange
        //Act

        //Assert
        assertThat(board[0].length).isEqualTo(3);
        assertThat(board.length).isEqualTo(3);
    }
    @Test
    public void checkPlay_WhenGivenLocation_ShouldReturnLocationStatus(){
        //Arrange
        //Act
        boolean result = sut.isPlay(2,2);
        //boolean result2 = sut.isPlay(2,3);
        //Assert

        assertThat(result).isTrue();
        //assertThat(result2).isFalse();
    }

    @Test
    public void checkPlay_WhenGivenLocation_ShouldReturnGivenLocationIsValid(){
        //Arrange
        //Act
        Throwable throwable = catchThrowable(() -> sut.isPlay(3,3));
        //Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Location Inputs Are Not Valid");
    }
    @Test
    public void play_WhenGivenLocation_ShouldReturnNewGameBoard() {
        //Arrange
        //Act
        String[][] newBoard = sut.play(2, 2);
        //Assert
        assertThat(newBoard[2][2]).isEqualTo("X");
    }
    @Test
    public void checkComputerIsPlay_WhenAfterUserPlay_ShouldReturnNumberOfMove(){
        //Arrange
        //Act
        int remainEmptyCell_BeforeUserPlay = sut.getEmptyCellNumber();
        String[][] newBoard = sut.play(2, 2);
        int remainEmptyCell_AfterUserPlay = sut.getEmptyCellNumber();
        //Assert
        assertThat(remainEmptyCell_BeforeUserPlay - 2 ).isEqualTo(remainEmptyCell_AfterUserPlay);
    }
    @Test
    public void checkGameStatus_WhenUserOrComputerWin_ShouldReturnWinner(){
        //Arrange
        //Act
        String[][] finishedGameBoard = new String[3][3];
        finishedGameBoard[0][0] = "X";
        finishedGameBoard[0][1] = "X";
        finishedGameBoard[0][2] = "X";
        sut.setBoard(finishedGameBoard);
        String result = sut.checkGameStatus();
        //Assert
        assertThat(result).isEqualTo("X");


    }
}
