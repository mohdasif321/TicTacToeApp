package com.example.tictactoeapp.domain.model

sealed class GameState() {
    object INPROGRESS: GameState()
    data class GAMEWON(val winningCells: List<Int>): GameState()
    object GAMEDRAW: GameState()
}