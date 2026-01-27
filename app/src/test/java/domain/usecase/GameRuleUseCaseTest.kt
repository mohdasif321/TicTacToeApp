package domain.usecase

import com.example.tictactoeapp.domain.model.GameState
import com.example.tictactoeapp.domain.usecase.GameRuleUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameRuleUseCaseTest {

    lateinit var sut: GameRuleUseCase

    @Before
    fun setUp() {
        sut = GameRuleUseCase()
    }

    @Test
    fun test_getGameStatus_GAMEDRAW() {
        val board = listOf("X", "X", "O", "O", "O", "X", "X", "O", "X")

        val gameStatus = sut.getGameStatus(board)

        Assert.assertTrue(gameStatus is GameState.GAMEDRAW)
    }
}