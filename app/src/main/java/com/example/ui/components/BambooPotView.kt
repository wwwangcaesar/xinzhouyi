package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldContainer
import com.example.ui.theme.OnImperialGold
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHighest
import kotlinx.coroutines.launch

@Composable
fun BambooPotView(
    signNumber: Int = 64,
    signHexagram: String = "未济",
    modifier: Modifier = Modifier,
    onShakeComplete: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val stickOffset = remember { Animatable(30f) }
    val shakeRotation = remember { Animatable(0f) }

    fun shakeCylinder() {
        coroutineScope.launch {
            // Rapid shake oscillation
            repeat(3) {
                shakeRotation.animateTo(6f, tween(80))
                shakeRotation.animateTo(-6f, tween(80))
            }
            shakeRotation.animateTo(0f, tween(80))
            // Stick floats out
            stickOffset.animateTo(-55f, tween(500, easing = FastOutSlowInEasing))
            onShakeComplete()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.9f))
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.6f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top tags
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "☸ 文王六十四灵签 · 乾坤法阵",
                style = MaterialTheme.typography.labelSmall,
                color = ImperialGold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "甲辰年 · 丙寅月",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF4CD7F6)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Bamboo cylinder stage with stick emerging
        Box(
            modifier = Modifier
                .height(220.dp)
                .width(180.dp)
                .rotate(shakeRotation.value)
                .clickable { shakeCylinder() },
            contentAlignment = Alignment.BottomCenter
        ) {
            // Emerging Fortune Stick
            Column(
                modifier = Modifier
                    .offset { IntOffset(0, stickOffset.value.toInt()) }
                    .width(42.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFFFE088),
                                Color(0xFFD4AF37),
                                Color(0xFF996515)
                            )
                        )
                    )
                    .border(1.dp, Color(0xFFFFD700).copy(alpha = 0.8f), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Crimson crown dot
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCC1100))
                )
                Spacer(modifier = Modifier.height(6.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "第\n六\n十\n四\n签",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF241A00),
                        lineHeight = 11.sp,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = signHexagram,
                        fontSize = 8.sp,
                        color = Color(0xFF3C2F00),
                        fontFamily = FontFamily.Serif
                    )
                }
            }

            // Cylinder Body
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF1E140A),
                                Color(0xFF0F0B06),
                                Color(0xFF050302)
                            )
                        )
                    )
                    .border(1.5.dp, Color(0xFFB8860B).copy(alpha = 0.8f), RoundedCornerShape(14.dp))
                    .shadow(16.dp, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Decorative rim line
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(2.dp)
                            .background(ImperialGold.copy(alpha = 0.7f))
                    )
                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "文 王 靈 筒",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = ImperialGold,
                        letterSpacing = 4.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Small decorative trigram symbols
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("☲", fontSize = 11.sp, color = ImperialGold.copy(alpha = 0.6f))
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CD7F6))
                        )
                        Text("☵", fontSize = 11.sp, color = ImperialGold.copy(alpha = 0.6f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Shake action button
        Button(
            onClick = { shakeCylinder() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("shake_bamboo_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = ImperialGold,
                contentColor = OnImperialGold
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Vibration,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "诚心摇动签筒 · 叩问天机",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = AlchemicalJade,
                modifier = Modifier.size(13.dp)
            )
            Text(
                text = " 心念纯一，一事只起一签",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD0C5AF)
            )
        }
    }
}
