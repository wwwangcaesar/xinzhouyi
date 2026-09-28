package com.example.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh

@Composable
fun ShengBeiView(
    modifier: Modifier = Modifier,
    isShengBei: Boolean = true,
    onToss: () -> Unit = {}
) {
    var tossRot by remember { mutableStateOf(0f) }
    val animatedRot by animateFloatAsState(
        targetValue = tossRot,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "TossRotation"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainer.copy(alpha = 0.85f))
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .padding(14.dp)
            .testTag("sheng_bei_container")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "🌱 掷筊验卦 · 圣意昭示",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF003824).copy(alpha = 0.7f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "圣杯 · 允准",
                    style = MaterialTheme.typography.labelSmall,
                    color = AlchemicalJade
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Two Jiao Bei Blocks
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    tossRot += 360f
                    onToss()
                }
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Bei: Flat / Yang face up
            Box(
                modifier = Modifier
                    .size(width = 84.dp, height = 52.dp)
                    .rotate(animatedRot),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(width = 84.dp, height = 50.dp)) {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.7f)
                        cubicTo(size.width * 0.1f, 0f, size.width * 0.9f, 0f, size.width, size.height * 0.7f)
                        lineTo(size.width * 0.85f, size.height)
                        cubicTo(size.width * 0.5f, size.height * 0.4f, size.width * 0.15f, size.height * 0.4f, 0f, size.height * 0.7f)
                        close()
                    }
                    drawPath(path, color = Color(0xFFC48A2C))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("阳 · FLAT", fontSize = 9.sp, color = Color(0xFFFFE088), style = MaterialTheme.typography.labelSmall)
                    Text("阳面朝天", fontSize = 8.sp, color = Color.White.copy(alpha = 0.8f))
                }
            }

            Icon(
                imageVector = Icons.Default.SyncAlt,
                contentDescription = "互变",
                tint = ImperialGold.copy(alpha = 0.7f),
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .size(20.dp)
            )

            // Right Bei: Round / Yin face up
            Box(
                modifier = Modifier
                    .size(width = 84.dp, height = 52.dp)
                    .rotate(-animatedRot),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(width = 84.dp, height = 50.dp)) {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.3f)
                        cubicTo(size.width * 0.15f, size.height, size.width * 0.85f, size.height, size.width, size.height * 0.3f)
                        lineTo(size.width * 0.9f, 0f)
                        cubicTo(size.width * 0.7f, size.height * 0.5f, size.width * 0.3f, size.height * 0.5f, 0f, size.height * 0.3f)
                        close()
                    }
                    drawPath(path, color = Color(0xFF6B4513))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("阴 · ROUND", fontSize = 9.sp, color = Color(0xFFDFE2F1), style = MaterialTheme.typography.labelSmall)
                    Text("阴面覆地", fontSize = 8.sp, color = Color.White.copy(alpha = 0.7f))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Explanation footer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceContainerHigh.copy(alpha = 0.6f))
                .padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = null,
                tint = ImperialGold,
                modifier = Modifier
                    .size(16.dp)
                    .padding(top = 1.dp)
            )
            Text(
                text = "一阴一阳 · 掷得圣杯：神明感念至诚，此签顺合天理，卦象纯正准验。",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}
