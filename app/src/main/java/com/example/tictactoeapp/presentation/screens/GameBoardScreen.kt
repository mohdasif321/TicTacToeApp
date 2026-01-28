package com.example.tictactoeapp.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameBoardScreen(padding: PaddingValues) {
    Text(modifier = Modifier.fillMaxWidth().padding(padding),
        text = "Tic Tac Toe",
        color = Color.Cyan,
        fontSize = 16.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, )
@Composable
private fun GameBoardScreenPreview() {
    GameBoardScreen(PaddingValues(20.dp))
}
