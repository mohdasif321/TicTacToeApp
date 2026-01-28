package com.example.tictactoeapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Cell_X() {
    Box(Modifier.wrapContentSize().padding(5.dp)) {
        Card(shape = RectangleShape, border = BorderStroke(4.dp, color = Color.DarkGray)) {
            Canvas(modifier = Modifier.size(90.dp).padding(15.dp)) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawLine(
                    start = Offset(x = canvasWidth, y = 0f),
                    end = Offset(x = 0f, y = canvasHeight),
                    color = Color.Red,
                    cap = Stroke.DefaultCap,
                    strokeWidth = 20F
                )

                drawLine(
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = canvasHeight, y = canvasHeight),
                    color = Color.Red,
                    strokeWidth = 20F,
                )
            }
        }
    }
}

@Composable
fun Cell_O() {
    Box(Modifier.wrapContentSize().padding(5.dp)) {
        Card(
            shape = RectangleShape,
            border = BorderStroke(4.dp, color = Color.DarkGray)
        ) {
            Canvas(modifier = Modifier.size(90.dp).padding(15.dp)) {
                val canvasWidth = size.width

                drawCircle(
                    radius = canvasWidth / 2,
                    color = Color.Yellow,
                    alpha = 1f,
                    style = Stroke(
                        width = 20f
                    )
                )
            }
        }
    }
}

@Composable
fun Cell_empty(onClick : () -> Unit) {
    Box(Modifier.wrapContentSize().padding(5.dp)) {
        Card(
            shape = RectangleShape,
            onClick = onClick,
            border = BorderStroke(4.dp, color = Color.DarkGray)
        ) {
            Canvas(modifier = Modifier.size(90.dp).padding(5.dp)) {

                drawRect(
                    color = Color.White,
                    style = Stroke(
                        width = 20f
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GameBoardScreenPreview() {
    Cell_X()
    Cell_O()
    Cell_empty() {}
}
