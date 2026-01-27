package com.example.tictactoeapp.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoeapp.presentation.domain.GameState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameBoardViewModelTest {

    private lateinit var gameBoardViewModel: GameBoardViewModel

    @Before
    fun setup() {
        gameBoardViewModel = GameBoardViewModel()
    }

    @Test
    fun test_PlayerXClickOnCell_N() = runTest {
        val tappedCellByPlayer = 2

        gameBoardViewModel.board.test {
            Assert.assertTrue(gameBoardViewModel.board.value[tappedCellByPlayer] == "")

            gameBoardViewModel.play(tappedCellByPlayer)
            Assert.assertTrue(awaitItem()[tappedCellByPlayer] == gameBoardViewModel.board.value[tappedCellByPlayer])

            cancel()
        }
    }

    @Test
    fun test_ResetBoard() = runTest {
        val tappedCellByPlayer = 2

        gameBoardViewModel.board.test {
            gameBoardViewModel.play(tappedCellByPlayer)
            Assert.assertTrue(awaitItem()[tappedCellByPlayer] == gameBoardViewModel.board.value[tappedCellByPlayer])

            gameBoardViewModel.resetBoard()
            Assert.assertTrue(awaitItem().reduce { acc, string -> acc + string } == "")

            cancel()
        }
    }

    @Test
    fun test_getGameStatus_GAMEWON() {
        val sut = GameBoardViewModel()
        val board = gameBoardViewModel.board.value

        val validateGetGameStatusMethod = GameBoardViewModel::class.java.getDeclaredMethod(
            "getGameStatus",
            List::class.java
        )
        validateGetGameStatusMethod.isAccessible = true
        validateGetGameStatusMethod.invoke(sut,board)

        Assert.assertTrue(sut.gameStatus.value is GameState.GAMEWON)
    }
}