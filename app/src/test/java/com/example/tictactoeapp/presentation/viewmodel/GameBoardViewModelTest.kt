package com.example.tictactoeapp.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoeapp.domain.model.GameState
import com.example.tictactoeapp.domain.usecase.GameRuleUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameBoardViewModelTest {

    private lateinit var gameBoardViewModel: GameBoardViewModel

    private var gameRuleUseCase: GameRuleUseCase = mockk()

    @Before
    fun setup() {
        gameBoardViewModel = GameBoardViewModel(gameRuleUseCase)
    }

    @Test
    fun test_PlayerXClickOnCell_N() = runTest {
        val tappedCellByPlayer = 2

        every { gameRuleUseCase.getGameStatus(any()) } returns GameState.INPROGRESS

        gameBoardViewModel.board.test {
            Assert.assertEquals(awaitItem()[tappedCellByPlayer], gameBoardViewModel.board.value[tappedCellByPlayer])
            gameBoardViewModel.play(tappedCellByPlayer)
            Assert.assertTrue(awaitItem()[tappedCellByPlayer] == gameBoardViewModel.board.value[tappedCellByPlayer])

            cancel()
        }
    }

    @Test
    fun test_ResetBoard() = runTest {
        val tappedCellByPlayer = 2

        every { gameRuleUseCase.getGameStatus(any()) } returns GameState.INPROGRESS

        gameBoardViewModel.board.test {
            Assert.assertEquals(awaitItem()[tappedCellByPlayer], gameBoardViewModel.board.value[tappedCellByPlayer])
            gameBoardViewModel.play(tappedCellByPlayer)
            Assert.assertTrue(awaitItem()[tappedCellByPlayer] == gameBoardViewModel.board.value[tappedCellByPlayer])
            gameBoardViewModel.resetBoard()
            Assert.assertTrue(awaitItem().reduce { acc, string -> acc + string } == "")

            cancel()
        }
    }

    @Test
    fun test_getGameStatus_GAMEWON() {
        val sut = GameBoardViewModel(gameRuleUseCase)
        val board = gameBoardViewModel.board.value

        every { gameRuleUseCase.getGameStatus(board) } returns GameState.GAMEWON(listOf(0, 1, 2))
        val validateGetGameStatusMethod = GameBoardViewModel::class.java.getDeclaredMethod(
            "getGameStatus",
            List::class.java
        )
        validateGetGameStatusMethod.isAccessible = true
        validateGetGameStatusMethod.invoke(sut,board)

        Assert.assertTrue(sut.gameStatus.value is GameState.GAMEWON)
    }
}