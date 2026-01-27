package com.example.tictactoeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameBoardViewModel: ViewModel() {

    private var _board = MutableStateFlow(List(9) { "" }.toMutableList())
    val board: StateFlow<List<String>>
        get() = _board

    fun play(move: Int) {
        _board.value[move] = "X"
    }

    fun resetBoard() {
        val updatedBoard = _board.value.map { "" }
        _board.value = updatedBoard.toMutableList()
    }
}