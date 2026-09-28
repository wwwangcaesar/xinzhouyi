package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.CinnabarRed
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldFixed
import com.example.ui.theme.SurfaceContainerHighest

@Composable
fun ElementalBarGroup(
    metalRatio: Int = 32,
    woodRatio: Int = 18,
    waterRatio: Int = 14,
    fireRatio: Int = 22,
    earthRatio: Int = 14,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ElementRow(label = "金", percentage = metalRatio, color = ImperialGold)
        Spacer(modifier = Modifier.height(8.dp))
        ElementRow(label = "木", percentage = woodRatio, color = AlchemicalJade)
        Spacer(modifier = Modifier.height(8.dp))
        ElementRow(label = "水", percentage = waterRatio, color = EtherealCyan)
        Spacer(modifier = Modifier.height(8.dp))
        ElementRow(label = "火", percentage = fireRatio, color = CinnabarRed)
        Spacer(modifier = Modifier.height(8.dp))
        ElementRow(label = "土", percentage = earthRatio, color = ImperialGoldFixed)
    }
}

@Composable
private fun ElementRow(
    label: String,
    percentage: Int,
    color: Color
) {
    val animatedPercent by animateFloatAsState(
        targetValue = percentage / 100f,
        animationSpec = tween(durationMillis = 800),
        label = "ElementPercent"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = color,
            modifier = Modifier.width(24.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(SurfaceContainerHighest)
                .padding(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedPercent.coerceIn(0.02f, 1f))
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(color.copy(alpha = 0.65f), color)
                        )
                    )
            )
        }

        Text(
            text = "$percentage%",
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFFD0C5AF),
            modifier = Modifier
                .width(36.dp)
                .padding(start = 8.dp)
        )
    }
}
