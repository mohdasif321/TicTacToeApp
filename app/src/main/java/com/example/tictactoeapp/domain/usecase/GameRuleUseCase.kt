package com.example.tictactoeapp.domain.usecase

import com.example.tictactoeapp.domain.model.GameState

class GameRuleUseCase {
    fun getGameStatus(board: List<String>): GameState {
        return if (isBoardFull(board)) GameState.GAMEDRAW else GameState.INPROGRESS
    }

    private fun isBoardFull(board: List<String>): Boolean {
        return board.firstOrNull(predicate = {
            it.isEmpty()
        }) == null
    }
}