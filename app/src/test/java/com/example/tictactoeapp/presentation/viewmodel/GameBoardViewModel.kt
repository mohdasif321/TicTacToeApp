package com.example.tictactoegame.presentation.viewmodel

import com.example.tictactoeapp.presentation.viewmodel.GameBoardViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameBoardViewModelTest {

    lateinit var gameBoardViewModel: GameBoardViewModel

    @Before
    fun setup() {
        gameBoardViewModel = GameBoardViewModel()
    }

    @Test
    fun test_PlayerXClickOnCell_N() {
        val tappedCellByPlayer = 2

        gameBoardViewModel.play(tappedCellByPlayer)

        Assert.assertTrue(gameBoardViewModel.board.value[tappedCellByPlayer] == "X")
    }
}