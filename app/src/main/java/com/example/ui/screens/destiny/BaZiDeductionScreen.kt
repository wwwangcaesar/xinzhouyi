package com.example.ui.screens.destiny

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldFixed
import com.example.ui.theme.OnImperialGold
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.SurfaceVoid
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TianYanViewModel

@Composable
fun BaZiDeductionScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val activeProfile by viewModel.activeProfile.collectAsStateWithLifecycle()

    val infiniteTransition = rememberInfiniteTransition(label = "DeductionOrbits")
    val orbitRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 60000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "OrbitSpin"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceVoid)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("bazi_deduction_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateTo(Screen.GanzhiSetup) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerHigh)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "King Wen 64 Divinat...",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "TAOIST SANCTUM · 玄枢灵应",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGold
                )
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(ImperialGold),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = OnImperialGold,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Deduction Status Strip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(ImperialGold)
                )
                Text(
                    text = " 天机贯注 · 推演中",
                    style = MaterialTheme.typography.labelMedium,
                    color = ImperialGold,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerHigh)
                    .clickable { viewModel.navigateTo(Screen.Destiny) }
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "跳过演运 >>",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF)
                )
            }
        }

        // 4 Steps Stepper
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StepPill("01. 肃心", "已归元", isDone = true, isActive = false, modifier = Modifier.weight(1f))
            StepPill("02. 定盘", "凝神中", isDone = false, isActive = true, modifier = Modifier.weight(1f))
            StepPill("03. 融气", "候令", isDone = false, isActive = false, modifier = Modifier.weight(1f))
            StepPill("04. 昭示", "待破", isDone = false, isActive = false, modifier = Modifier.weight(1f))
        }

        // Circular Astrolabe
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            // Rotating dashed rings and celestial points
            Canvas(
                modifier = Modifier
                    .size(190.dp)
                    .rotate(orbitRotation)
            ) {
                val c = center
                val r = size.width / 2f
                drawCircle(color = ImperialGold.copy(alpha = 0.2f), radius = r - 10, center = c, style = Stroke(width = 1.dp.toPx()))
                drawCircle(color = EtherealCyan.copy(alpha = 0.2f), radius = r - 35, center = c, style = Stroke(width = 0.8.dp.toPx()))

                // Planetary nodes
                drawCircle(color = ImperialGold, radius = 3.dp.toPx(), center = Offset(c.x + r * 0.7f, c.y - r * 0.4f))
                drawCircle(color = EtherealCyan, radius = 2.5.dp.toPx(), center = Offset(c.x - r * 0.6f, c.y + r * 0.5f))
            }

            // Central Taiji
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerLowest)
                    .border(1.dp, ImperialGold.copy(alpha = 0.7f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "☯", fontSize = 28.sp, color = ImperialGold)
            }
        }

        // 四柱归元天盘
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(width = 4.dp, height = 14.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(ImperialGold)
                        )
                        Text(
                            text = " 四柱归元天盘",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(SurfaceContainerHigh)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "真太阳时校准",
                            style = MaterialTheme.typography.labelSmall,
                            color = EtherealCyan
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DeductionPillar("年柱", activeProfile.yearGanzhi, activeProfile.yearNayin, "${activeProfile.yearZodiac} · ${activeProfile.yearStage}", EtherealCyan, Modifier.weight(1f))
                    DeductionPillar("月柱", activeProfile.monthGanzhi, activeProfile.monthNayin, "${activeProfile.monthZodiac} · ${activeProfile.monthStage}", ImperialGoldFixed, Modifier.weight(1f))
                    DeductionPillar("日主元神", activeProfile.dayGanzhi, activeProfile.dayNayin, "${activeProfile.dayZodiac} · ${activeProfile.dayStage}", ImperialGold, Modifier.weight(1f), isDayMaster = true)
                    DeductionPillar("时柱", activeProfile.hourGanzhi, activeProfile.hourNayin, "${activeProfile.hourZodiac} · ${activeProfile.hourStage}", EtherealCyan, Modifier.weight(1f))
                }
            }
        }

        // 天机真诀 · 命象初显
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = ImperialGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = " 天机真诀 · 命象初显",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Text(
                        text = "● 玄枢逐字推演",
                        style = MaterialTheme.typography.labelSmall,
                        color = AlchemicalJade
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLowest.copy(alpha = 0.8f))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "【定局初验】${activeProfile.gender}生于${activeProfile.yearGanzhi}季秋，${activeProfile.dayGanzhi.take(1)}土日元坐${activeProfile.dayGanzhi.takeLast(1)}木长生，虽逢金秋肃杀，得${activeProfile.monthGanzhi.take(1)}${activeProfile.yearGanzhi.take(1)}重火透干温煦，骨重四两四钱，位列上中命格……▌",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    )
                }

                // 五行生克纳音权重
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "五行生克纳音权重",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                        Text(
                            text = "● 相生聚势中",
                            style = MaterialTheme.typography.labelSmall,
                            color = EtherealCyan
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        NayinWeightPill("火 32%", "旺盛", Color(0xFFFF8364), Modifier.weight(1f))
                        NayinWeightPill("土 24%", "充盈", ImperialGoldFixed, Modifier.weight(1f))
                        NayinWeightPill("金 20%", "敛藏", ImperialGold, Modifier.weight(1f))
                        NayinWeightPill("木 14%", "生发", AlchemicalJade, Modifier.weight(1f))
                        NayinWeightPill("水 10%", "待润", EtherealCyan, Modifier.weight(1f))
                    }
                }
            }
        }

        // Progress bar
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "☼ 正在交融天地灵气与真太阳时",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF)
                )
                Text(
                    text = "88%",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGold,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { 0.88f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = ImperialGold,
                trackColor = SurfaceContainerHigh
            )
        }

        // Bottom CTA Buttons
        Button(
            onClick = {
                viewModel.navigateTo(Screen.Destiny)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("complete_deduction_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = ImperialGold,
                contentColor = OnImperialGold
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Visibility,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = " 心诚礼毕 · 查看完整本命盘",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        OutlinedButton(
            onClick = { viewModel.navigateTo(Screen.GanzhiSetup) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFFD0C5AF)
            ),
            border = ButtonDefaults.outlinedButtonBorder().copy(brush = Brush.horizontalGradient(listOf(Color(0xFF4D4635), Color(0xFF4D4635))))
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = " 重纳生辰时空演变",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun StepPill(
    stepNum: String,
    title: String,
    isDone: Boolean,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isActive) SurfaceContainerHigh else SurfaceContainerLow)
            .border(
                1.dp,
                if (isActive) ImperialGold else if (isDone) AlchemicalJade.copy(alpha = 0.5f) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stepNum, fontSize = 9.sp, color = if (isActive) ImperialGold else Color(0xFFD0C5AF))
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                color = if (isActive) ImperialGold else if (isDone) MaterialTheme.colorScheme.onSurface else Color(0xFF99907C)
            )
        }
    }
}

@Composable
private fun DeductionPillar(
    title: String,
    ganzhi: String,
    nayin: String,
    stage: String,
    color: Color,
    modifier: Modifier = Modifier,
    isDayMaster: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .border(
                1.dp,
                if (isDayMaster) ImperialGold.copy(alpha = 0.8f) else Color(0xFF4D4635).copy(alpha = 0.3f),
                RoundedCornerShape(10.dp)
            )
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 9.sp, color = Color(0xFFD0C5AF), style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = ganzhi,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = nayin, fontSize = 9.sp, color = color.copy(alpha = 0.85f))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = stage, fontSize = 8.sp, color = Color(0xFFD0C5AF))
        }
    }
}

@Composable
private fun NayinWeightPill(
    weight: String,
    desc: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceContainerLow)
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = weight, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = color)
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(2.dp)
                    .background(color)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = desc, fontSize = 8.sp, color = Color(0xFFD0C5AF))
        }
    }
}
