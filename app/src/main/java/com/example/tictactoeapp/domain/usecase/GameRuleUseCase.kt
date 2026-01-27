package com.example.tictactoeapp.domain.usecase

import com.example.tictactoeapp.domain.model.GameState

class GameRuleUseCase {
    fun getGameStatus(board: List<String>): GameState {
        return GameState.GAMEDRAW
    }
}