package com.example.tictactoeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoeapp.presentation.domain.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameBoardViewModel: ViewModel() {

    private var _board = MutableStateFlow(List(9) { "" }.toMutableList())
    val board: StateFlow<List<String>>
        get() = _board

    private var _gameStatus= MutableStateFlow<GameState>(GameState.INPROGRESS)
    val gameStatus: StateFlow<GameState>
        get() = _gameStatus

    fun play(move: Int) {
        _board.value[move] = "X"
    }

    fun resetBoard() {
        val updatedBoard = _board.value.map { "" }
        _board.value = updatedBoard.toMutableList()
    }

    private fun getGameStatus(board: MutableList<String>) {
        _gameStatus.value = GameState.GAMEWON(listOf(1, 2, 3))
    }
}