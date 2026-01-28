package com.example.tictactoeapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tictactoeapp.domain.model.GameState
import com.example.tictactoeapp.presentation.components.Cell_O
import com.example.tictactoeapp.presentation.components.Cell_X
import com.example.tictactoeapp.presentation.components.Cell_empty
import com.example.tictactoeapp.presentation.viewmodel.GameBoardViewModel
import kotlinx.coroutines.launch

@Composable
fun GameBoardScreen(padding: PaddingValues,
                    gameBoardViewModel: GameBoardViewModel = hiltViewModel()) {

    val gameBoard = gameBoardViewModel.board.collectAsStateWithLifecycle().value
    val gameStatus = gameBoardViewModel.gameStatus.collectAsStateWithLifecycle().value
    val currentPlayer = gameBoardViewModel.currentPlayer.collectAsStateWithLifecycle().value

    Column(Modifier
        .fillMaxSize()
        .padding(padding)
        .background(Color.LightGray)) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            text = "Tic Tac Toe",
            color = Color.DarkGray,
            fontSize = 48.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.Center, contentPadding = PaddingValues(10.dp)) {
                itemsIndexed(gameBoard) {
                        index, item ->
                    when(item) {
                        "X" -> Cell_X()
                        "O" -> Cell_O()
                        else -> Cell_empty { gameBoardViewModel.play(index)}
                    }
                }
            }
        }

        val statusString = when(gameStatus) {
            is GameState.GAMEWON ->
                "Game Won by Player $currentPlayer"

            is GameState.GAMEDRAW ->
                "Game Draw!"
            else -> ""
        }

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            text = statusString,
            color = Color.DarkGray,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        val coroutineScope = rememberCoroutineScope()
        Button(
            modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
            onClick = {
                coroutineScope.launch { gameBoardViewModel.resetBoard() }
            }
        ) {
            Text(
                text = "Restart",
                color = Color.Black,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, )
@Composable
private fun GameBoardScreenPreview() {
    GameBoardScreen(PaddingValues(20.dp))
}