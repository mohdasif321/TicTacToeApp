package com.example.tictactoeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoeapp.common.Player
import com.example.tictactoeapp.domain.model.GameState
import com.example.tictactoeapp.domain.usecase.GameRuleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameBoardViewModel @Inject constructor(val gameRuleUseCase: GameRuleUseCase): ViewModel() {

    private var _board = MutableStateFlow(List(9) { "" }.toMutableList())
    val board: StateFlow<List<String>>
        get() = _board

    private var _gameStatus= MutableStateFlow<GameState>(GameState.INPROGRESS)
    val gameStatus: StateFlow<GameState>
        get() = _gameStatus

    var currentPlayer = MutableStateFlow<Player>(Player.PLAYER_X)
        private set

    fun play(move: Int) {
        viewModelScope.launch {
            val updatedBoard = _board.value.toMutableList().apply {
                this[move] = if (currentPlayer.value == Player.PLAYER_X) "X" else "O"
            }

            _board.emit(updatedBoard)
            getGameStatus(_board.value)
            if (gameStatus.value is GameState.INPROGRESS)
                if (currentPlayer.value == Player.PLAYER_X) {
                    currentPlayer.value = Player.PLAYER_O
                } else {
                    currentPlayer.value = Player.PLAYER_X
                }
        }
    }

    fun resetBoard() {
        val updatedBoard = _board.value.map { "" }
        _board.value = updatedBoard.toMutableList()
    }

    private fun getGameStatus(board: MutableList<String>) {
        _gameStatus.value = gameRuleUseCase.getGameStatus(board)
    }
}