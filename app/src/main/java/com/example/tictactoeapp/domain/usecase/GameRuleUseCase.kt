package com.example.tictactoeapp.domain.usecase

import com.example.tictactoeapp.domain.model.GameState

class GameRuleUseCase {
    fun getGameStatus(board: List<String>): GameState {
        val winningLines = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (line in winningLines) {
            val (a, b, c) = line
            val value = board[a]

            if (value.isNotEmpty() && value == board[b] && value == board[c]) {
                return GameState.GAMEWON(line.toMutableList())
            }
        }
        return if (isBoardFull(board)) GameState.GAMEDRAW else GameState.INPROGRESS
    }

    private fun isBoardFull(board: List<String>): Boolean {
        return board.firstOrNull(predicate = {
            it.isEmpty()
        }) == null
    }
}