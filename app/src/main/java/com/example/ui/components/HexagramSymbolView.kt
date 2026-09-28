package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.ImperialGold

@Composable
fun HexagramSymbolView(
    lines: List<Boolean>, // 6 lines from bottom (index 0) to top (index 5)
    modifier: Modifier = Modifier,
    width: Dp = 48.dp,
    lineHeight: Dp = 5.dp,
    spacing: Dp = 4.dp,
    activeColor: Color = ImperialGold,
    movingYaoIndex: Int? = null, // 1..6 (1 = bottom)
    movingColor: Color = AlchemicalJade
) {
    // We render from top (line 6) to bottom (line 1)
    val displayLines = lines.reversed()

    Column(
        modifier = modifier.width(width),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        displayLines.forEachIndexed { revIdx, isYang ->
            val yaoNumber = 6 - revIdx // 6 down to 1
            val isMoving = (movingYaoIndex != null && movingYaoIndex == yaoNumber)
            val color = if (isMoving) movingColor else activeColor

            SingleYaoLine(
                isYang = isYang,
                width = width,
                height = lineHeight,
                color = color
            )

            if (revIdx < displayLines.lastIndex) {
                Spacer(modifier = Modifier.height(spacing))
            }
        }
    }
}

@Composable
private fun SingleYaoLine(
    isYang: Boolean,
    width: Dp,
    height: Dp,
    color: Color
) {
    Canvas(
        modifier = Modifier
            .width(width)
            .height(height)
    ) {
        val corner = CornerRadius(height.toPx() / 2f, height.toPx() / 2f)
        if (isYang) {
            // Continuous solid Yang bar
            drawRoundRect(
                color = color,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, size.height),
                cornerRadius = corner
            )
        } else {
            // Split Yin bar (two parts with center gap)
            val segmentWidth = (size.width - 6.dp.toPx()) / 2f
            // Left segment
            drawRoundRect(
                color = color,
                topLeft = Offset(0f, 0f),
                size = Size(segmentWidth, size.height),
                cornerRadius = corner
            )
            // Right segment
            drawRoundRect(
                color = color,
                topLeft = Offset(segmentWidth + 6.dp.toPx(), 0f),
                size = Size(segmentWidth, size.height),
                cornerRadius = corner
            )
        }
    }
}
