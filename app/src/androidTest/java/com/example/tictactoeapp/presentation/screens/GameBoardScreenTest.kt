package com.example.tictactoeapp.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameBoardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTitleVisibility_isVisible() {
        composeTestRule.setContent {
            GameBoardScreen(PaddingValues(20.dp))
        }
        composeTestRule.onNodeWithText("Tic Tac Toe").assertIsDisplayed()
    }
}