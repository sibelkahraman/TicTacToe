//Sibel Kahraman sibelkahraman235@gmail.com
//Ramazan Nejdet Sarıkaya necsrky@gmail.com

package com.kodluyoruz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class TicTacToeTest {
    TicTacToe sut;
    int[][] board;
    int boardSize = 6;

    @BeforeEach
    public void Setup() {
        sut = new TicTacToe();
        board = sut.createBoard(boardSize);
    }

    @Test
    public void createGameBoard_WhenGivenNForSize_ShouldReturnNxNBoard() {
        //Arrange
        //Act

        //Assert
        assertThat(board[0].length).isEqualTo(boardSize);
        assertThat(board.length).isEqualTo(boardSize);
    }

    @Test
    public void checkPlay_WhenGivenLocation_ShouldReturnLocationStatus() {
        //Arrange
        //Act
        boolean result = sut.isPlay(boardSize - 1, boardSize - 1);
        //boolean result2 = sut.isPlay(2,3);
        //Assert

        assertThat(result).isTrue();
        //assertThat(result2).isFalse();
    }

    @Test
    public void checkPlay_WhenGivenLocationIsNotValid_ShouldReturnException() {
        //Arrange
        //Act
        Throwable throwable = catchThrowable(() -> sut.isPlay(boardSize, boardSize));
        //Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Location Inputs Are Not Valid");
    }

    @Test
    public void play_WhenGivenLocation_ShouldReturnNewGameBoard() {
        //Arrange
        //Act
        int[][] newBoard = sut.play(boardSize - 1, boardSize - 1);
        //Assert
        assertThat(newBoard[boardSize - 1][boardSize - 1]).isEqualTo(1);
    }

    @Test
    public void checkComputerIsPlay_WhenAfterUserPlay_ShouldReturnNumberOfMove() {
        //Arrange
        //Act
        int remainEmptyCell_BeforeUserPlay = sut.getEmptyCellNumber();
        int[][] newBoard = sut.play(boardSize - 1, boardSize - 1);
        int remainEmptyCell_AfterUserPlay = sut.getEmptyCellNumber();
        //Assert
        assertThat(remainEmptyCell_BeforeUserPlay - 2).isEqualTo(remainEmptyCell_AfterUserPlay);
    }

    @Test
    public void checkHorizontalGameStatus_WhenUserOrComputerWin_ShouldReturnWinner() {
        //Arrange
        //Act
        int[][] finishedGameBoard = new int[3][3];
        finishedGameBoard[0][0] = 1;
        finishedGameBoard[0][1] = 1;
        finishedGameBoard[0][2] = 1;
        sut.setBoard(finishedGameBoard);
        String result = sut.checkGameStatus();
        //Assert
        assertThat(result).isEqualTo("X");
    }

    @Test
    public void checkVerticalGameStatus_WhenUserOrComputerWin_ShouldReturnWinner() {
        //Arrange
        //Act
        int[][] finishedGameBoard = new int[3][3];
        finishedGameBoard[0][0] = 1;
        finishedGameBoard[1][0] = 1;
        finishedGameBoard[2][0] = 1;
        sut.setBoard(finishedGameBoard);
        String result = sut.checkGameStatus();
        //Assert
        assertThat(result).isEqualTo("X");
    }

    @Test
    public void checkCrossGameStatus_WhenUserOrComputerWin_ShouldReturnWinner() {
        //Arrange
        //Act
        int[][] finishedGameBoard = new int[3][3];
        finishedGameBoard[0][0] = -1;
        finishedGameBoard[1][1] = -1;
        finishedGameBoard[2][2] = -1;
        sut.setBoard(finishedGameBoard);
        String result = sut.checkGameStatus();
        //Assert
        assertThat(result).isEqualTo("O");
    }
    @Test
    public void checkCrossGameStatus_WhenUserAndComputerNotWin_ShouldReturnDraw() {
        //Arrange
        //Act
        int[][] finishedGameBoard = new int[3][3];
        finishedGameBoard[0][0] = 1;
        finishedGameBoard[0][1] = 1;
        finishedGameBoard[0][2] = -1;
        finishedGameBoard[1][0] = -1;
        finishedGameBoard[1][1] = -1;
        finishedGameBoard[2][0] = 1;
        finishedGameBoard[1][2] = 1;
        finishedGameBoard[2][1] = -1;
        finishedGameBoard[2][2] = 1;
        /*sut.setBoard(finishedGameBoard);
        sut.showGameBoard();*/
        sut.setBoard(finishedGameBoard);
        String result = sut.checkGameStatus();
        //Assert
        assertThat(result).isEqualTo("Draw");
    }

    @ParameterizedTest
    @MethodSource("returnWinnerTestSource")
    public void checkArbitraryGameStatus_WhenUserOrComputerWin_ShouldReturnWinner(int[][] finishedGameBoard, String winner) {
        //Arrange
        //Act

        sut.setBoard(finishedGameBoard);
        String result = sut.checkGameStatus();
        //Assert
        assertThat(result).isEqualTo(winner);
    }

    private static Stream<Arguments> returnWinnerTestSource() {
        int[][] finishedGameBoard = new int[3][3];//Horizontal
        finishedGameBoard[2][0] = 1;
        finishedGameBoard[2][1] = 1;
        finishedGameBoard[2][2] = 1;

        int[][] finishedGameBoard2 = new int[4][4];//Vertical
        finishedGameBoard2[0][3] = -1;
        finishedGameBoard2[1][3] = -1;
        finishedGameBoard2[2][3] = -1;
        finishedGameBoard2[3][3] = -1;

        int[][] finishedGameBoard3 = new int[5][5];//Cross
        finishedGameBoard3[0][4] = 1;
        finishedGameBoard3[1][3] = 1;
        finishedGameBoard3[2][2] = 1;
        finishedGameBoard3[3][1] = 1;
        finishedGameBoard3[4][0] = 1;
        return Stream.of(
                Arguments.of(finishedGameBoard, "X"),
                Arguments.of(finishedGameBoard2, "O"),
                Arguments.of(finishedGameBoard3, "X"));
    }
}
