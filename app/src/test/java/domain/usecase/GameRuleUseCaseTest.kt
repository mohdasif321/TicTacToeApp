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

    @Test
    fun test_getGameStatus_INPROGRESS() {
        val board = listOf("X", "O", "X", "", "", "", "", "", "")

        val gameStatus = sut.getGameStatus(board)

        Assert.assertTrue(gameStatus is GameState.INPROGRESS)
    }

    @Test
    fun test_getGameStatus_GAMEWON() {
        val board = listOf("X", "X", "O", "X", "O", "", "O", "", "")

        val gameStatus = sut.getGameStatus(board)

        Assert.assertFalse(gameStatus is GameState.GAMEWON)
    }

    @Test
    fun test_isBoardFull_true() {
        val board = listOf("X", "X", "O", "O", "O", "X", "X", "O", "X")

        val validateIsBoardFullMethod = GameRuleUseCase::class.java.getDeclaredMethod(
            "isBoardFull",
            List::class.java
        )
        validateIsBoardFullMethod.isAccessible = true
        val result = validateIsBoardFullMethod.invoke(sut,board) as Boolean

        Assert.assertTrue(result)
    }

    @Test
    fun test_isBoardFull_false() {
        val board = listOf("X", "X", "O", "O", "O", "X", "", "", "")

        val validateIsBoardFullMethod = GameRuleUseCase::class.java.getDeclaredMethod(
            "isBoardFull",
            List::class.java
        )
        validateIsBoardFullMethod.isAccessible = true
        val result = validateIsBoardFullMethod.invoke(sut,board) as Boolean

        Assert.assertFalse(result)
    }
}