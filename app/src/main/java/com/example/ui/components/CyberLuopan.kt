package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldFixedDim
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLowest
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CyberLuopan(
    modifier: Modifier = Modifier,
    initialAngle: Float = 182f,
    onCoreClick: () -> Unit = {}
) {
    var needleTargetAngle by remember { mutableFloatStateOf(initialAngle) }
    val animatedNeedleAngle by animateFloatAsState(
        targetValue = needleTargetAngle,
        animationSpec = tween(durationMillis = 650, easing = FastOutSlowInEasing),
        label = "NeedleAngle"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "LuopanOrbits")
    val outerRingRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 120000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "OuterSpin"
    )
    val middleRingRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 90000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "MiddleSpin"
    )
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseScale"
    )

    Column(
        modifier = modifier.testTag("cyber_luopan_component"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(280.dp),
            contentAlignment = Alignment.Center
        ) {
            // Ambient Nebula Glow
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                ImperialGold.copy(alpha = 0.12f),
                                EtherealCyan.copy(alpha = 0.08f),
                                Color.Transparent
                            )
                        )
                    )
            )

            // Outer Canvas: 24 Solar Terms Ticks & Dashed Orbit
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(outerRingRotation)
            ) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val outerRadius = size.width / 2f - 4.dp.toPx()
                val tickRadius = outerRadius - 10.dp.toPx()

                // Outer border dashed circle
                drawCircle(
                    color = ImperialGold.copy(alpha = 0.25f),
                    radius = outerRadius,
                    center = center,
                    style = Stroke(width = 1.dp.toPx())
                )

                // 24 Solar terms tick lines
                for (i in 0 until 24) {
                    val angleRad = Math.toRadians((i * 15).toDouble())
                    val isMajor = i % 3 == 0
                    val tickLen = if (isMajor) 9.dp.toPx() else 4.dp.toPx()
                    val p1 = Offset(
                        (center.x + (outerRadius - tickLen) * cos(angleRad)).toFloat(),
                        (center.y + (outerRadius - tickLen) * sin(angleRad)).toFloat()
                    )
                    val p2 = Offset(
                        (center.x + outerRadius * cos(angleRad)).toFloat(),
                        (center.y + outerRadius * sin(angleRad)).toFloat()
                    )
                    drawLine(
                        color = if (isMajor) ImperialGold.copy(alpha = 0.6f) else Color.White.copy(alpha = 0.2f),
                        start = p1,
                        end = p2,
                        strokeWidth = if (isMajor) 1.5.dp.toPx() else 0.8.dp.toPx()
                    )
                }

                // Inner fine guide
                drawCircle(
                    color = EtherealCyan.copy(alpha = 0.15f),
                    radius = tickRadius,
                    center = center,
                    style = Stroke(width = 0.75.dp.toPx())
                )
            }

            // Middle Bagua Ring
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .rotate(middleRingRotation),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFF171B26).copy(alpha = 0.85f),
                        radius = size.width / 2f,
                        center = center
                    )
                    drawCircle(
                        color = ImperialGold.copy(alpha = 0.2f),
                        radius = size.width / 2f,
                        center = center,
                        style = Stroke(width = 0.8.dp.toPx())
                    )
                }

                // Bagua Trigrams (8 directions)
                TrigramLabel("☰ 乾", 0f, 78.dp, ImperialGold)
                TrigramLabel("☷ 坤", 180f, 78.dp, ImperialGold)
                TrigramLabel("☵ 坎", 270f, 78.dp, EtherealCyan)
                TrigramLabel("☲ 离", 90f, 78.dp, ImperialGoldFixedDim)
                TrigramLabel("☴ 巽", 45f, 78.dp, Color(0xFFD0C5AF))
                TrigramLabel("☶ 艮", 315f, 78.dp, Color(0xFFD0C5AF))
                TrigramLabel("☱ 兑", 135f, 78.dp, Color(0xFFD0C5AF))
                TrigramLabel("☳ 震", 225f, 78.dp, Color(0xFFD0C5AF))
            }

            // Inner Celestial Horizon Glass Ring
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerHigh.copy(alpha = 0.85f))
                    .border(1.dp, ImperialGold.copy(alpha = 0.35f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Crosshairs
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val c = center
                    drawLine(
                        color = ImperialGold.copy(alpha = 0.22f),
                        start = Offset(0f, c.y),
                        end = Offset(size.width, c.y),
                        strokeWidth = 0.75.dp.toPx()
                    )
                    drawLine(
                        color = ImperialGold.copy(alpha = 0.22f),
                        start = Offset(c.x, 0f),
                        end = Offset(c.x, size.height),
                        strokeWidth = 0.75.dp.toPx()
                    )
                }

                // Dynamic Astrolabe Needle
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .rotate(animatedNeedleAngle),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val cx = size.width / 2f
                        val cy = size.height / 2f

                        // Gold North tip
                        val northPath = Path().apply {
                            moveTo(cx, cy - 42.dp.toPx())
                            lineTo(cx - 3.dp.toPx(), cy - 8.dp.toPx())
                            lineTo(cx + 3.dp.toPx(), cy - 8.dp.toPx())
                            close()
                        }
                        drawPath(northPath, color = ImperialGold)

                        // Cyan South tip
                        val southPath = Path().apply {
                            moveTo(cx, cy + 42.dp.toPx())
                            lineTo(cx - 3.dp.toPx(), cy + 8.dp.toPx())
                            lineTo(cx + 3.dp.toPx(), cy + 8.dp.toPx())
                            close()
                        }
                        drawPath(southPath, color = EtherealCyan)

                        // Central needle pin
                        drawCircle(
                            color = Color(0xFF0F131D),
                            radius = 4.dp.toPx(),
                            center = Offset(cx, cy)
                        )
                        drawCircle(
                            color = ImperialGold,
                            radius = 2.dp.toPx(),
                            center = Offset(cx, cy)
                        )
                    }
                }

                // Central Taiji (Yin-Yang) Core
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerLowest)
                        .border(1.dp, ImperialGold.copy(alpha = 0.6f), CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            needleTargetAngle += (Math.random() * 60 - 30).toFloat()
                            onCoreClick()
                        }
                        .testTag("luopan_taiji_core"),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .size((34 * pulseScale).dp)
                    ) {
                        val radius = size.width / 2f
                        val c = center

                        // Yang half (Gold)
                        drawArc(
                            color = ImperialGold,
                            startAngle = -90f,
                            sweepAngle = 180f,
                            useCenter = true
                        )
                        // Yin half (Dark)
                        drawArc(
                            color = SurfaceContainerLowest,
                            startAngle = 90f,
                            sweepAngle = 180f,
                            useCenter = true
                        )

                        // Upper semicircle (Gold)
                        drawCircle(
                            color = ImperialGold,
                            radius = radius / 2f,
                            center = Offset(c.x, c.y - radius / 2f)
                        )
                        // Lower semicircle (Dark)
                        drawCircle(
                            color = SurfaceContainerLowest,
                            radius = radius / 2f,
                            center = Offset(c.x, c.y + radius / 2f)
                        )

                        // Inner small dots
                        drawCircle(
                            color = SurfaceContainerLowest,
                            radius = radius / 6f,
                            center = Offset(c.x, c.y - radius / 2f)
                        )
                        drawCircle(
                            color = ImperialGold,
                            radius = radius / 6f,
                            center = Offset(c.x, c.y + radius / 2f)
                        )
                    }
                }
            }

            // Cardinal Direction Badges
            Text(
                text = "午 / 南 182°",
                style = MaterialTheme.typography.labelSmall,
                color = ImperialGold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
            )
            Text(
                text = "子 / 北 002°",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFD0C5AF),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = "卯 / 东",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFD0C5AF),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 2.dp)
            )
            Text(
                text = "酉 / 西",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFD0C5AF),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Compass Status
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Sensors,
                contentDescription = null,
                tint = ImperialGold,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = "磁偏角校对完成 · 丙午方位天心合一",
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFFD0C5AF),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Composable
private fun TrigramLabel(text: String, angleDegrees: Float, distance: androidx.compose.ui.unit.Dp, color: Color) {
    val rad = Math.toRadians((angleDegrees - 90).toDouble())
    val xOffset = distance * cos(rad).toFloat()
    val yOffset = distance * sin(rad).toFloat()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = color,
            modifier = Modifier.padding(
                start = if (xOffset.value > 0) (xOffset.value * 2).dp else 0.dp,
                end = if (xOffset.value < 0) (-xOffset.value * 2).dp else 0.dp,
                top = if (yOffset.value > 0) (yOffset.value * 2).dp else 0.dp,
                bottom = if (yOffset.value < 0) (-yOffset.value * 2).dp else 0.dp
            )
        )
    }
}
