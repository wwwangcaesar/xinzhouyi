package com.example.ui.screens.destiny

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.calculator.BaZiData
import com.example.ui.components.CyberLuopan
import com.example.ui.components.ElementalBarGroup
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.CinnabarRed
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldFixedDim
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TianYanViewModel

@Composable
fun DestinyChartScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val activeProfile by viewModel.activeProfile.collectAsStateWithLifecycle()
    val dailyHours = BaZiData.getDailyHours()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp)
            .testTag("destiny_chart_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Ambient Status Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerLow)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(AlchemicalJade)
                    )
                    Text(
                        text = " 甲辰龙年 · 惊蛰 · 庚午日",
                        style = MaterialTheme.typography.labelSmall,
                        color = AlchemicalJade
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerHigh)
                    .clickable { viewModel.navigateTo(Screen.GanzhiSetup) }
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .testTag("precise_calibrate_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "精微校准",
                        tint = ImperialGold,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = " 精微校准",
                        style = MaterialTheme.typography.labelSmall,
                        color = ImperialGold
                    )
                }
            }
        }

        // Active Profile Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                .clickable { viewModel.navigateTo(Screen.GanzhiSetup) }
                .padding(12.dp)
                .testTag("active_profile_bar")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerHighest),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = ImperialGold,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${activeProfile.name} · ${activeProfile.gender}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceContainerHighest)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = activeProfile.fateNayin,
                                    fontSize = 9.sp,
                                    color = ImperialGoldFixedDim
                                )
                            }
                        }
                        Text(
                            text = "${activeProfile.yearGanzhi} ${activeProfile.monthGanzhi} ${activeProfile.dayGanzhi} ${activeProfile.hourGanzhi}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "切换",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFD0C5AF)
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "切换",
                        tint = Color(0xFFD0C5AF),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // Real-time AI Reasoning Feed (Micro-Pill)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainerLow.copy(alpha = 0.8f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ImperialGold.copy(alpha = 0.15f))
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = ImperialGold,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "天衍AI推演 · 即时局象",
                            style = MaterialTheme.typography.labelSmall,
                            color = ImperialGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "真太阳时 08:42",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "日主戊土生于酉月，金旺得气，泄秀有情；地支寅辰遥拱，藏干通透。现辰时正值天乙贵人伏位，气机升腾。",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 17.sp
                    )
                }
            }
        }

        // Centerpiece: Cybernetic Luopan
        CyberLuopan(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            initialAngle = 182f
        )

        // Auspicious Spirits Badges (喜神 & 财神)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // 喜神
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainer.copy(alpha = 0.85f))
                    .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerHigh),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "喜",
                            style = MaterialTheme.typography.titleMedium,
                            color = ImperialGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = "喜神方位",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                        Text(
                            text = "正南 · 丙位",
                            style = MaterialTheme.typography.titleSmall,
                            color = ImperialGold,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "宜嫁娶、纳福、商谈",
                            fontSize = 9.sp,
                            color = AlchemicalJade
                        )
                    }
                }
            }

            // 财神
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainer.copy(alpha = 0.85f))
                    .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerHigh),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "财",
                            style = MaterialTheme.typography.titleMedium,
                            color = EtherealCyan,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = "财神方位",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                        Text(
                            text = "正东 · 震方",
                            style = MaterialTheme.typography.titleSmall,
                            color = EtherealCyan,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "宜入库、开市、签约",
                            fontSize = 9.sp,
                            color = ImperialGoldFixedDim
                        )
                    }
                }
            }
        }

        // Daily Auspicious Timeline (十二时辰吉凶演运)
        Column(modifier = Modifier.fillMaxWidth()) {
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
                        text = " 今日十二时辰吉凶演运",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = "庚午日建除值神",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Horizontal Scrollable Timeline
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dailyHours.forEach { hour ->
                    val isCurrent = hour.isCurrent
                    Box(
                        modifier = Modifier
                            .width(if (isCurrent) 146.dp else 126.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isCurrent) SurfaceContainerHigh else SurfaceContainerLow.copy(alpha = 0.75f))
                            .border(
                                1.dp,
                                if (isCurrent) ImperialGold.copy(alpha = 0.6f) else Color(0xFF4D4635).copy(alpha = 0.3f),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = hour.branch,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (isCurrent) ImperialGold else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (isCurrent) ImperialGold else SurfaceContainerHighest)
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = if (isCurrent) "当前 · ${hour.level}" else hour.level,
                                        fontSize = 8.sp,
                                        color = if (isCurrent) Color(0xFF3C2F00) else Color(0xFFD0C5AF),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = hour.deity,
                                style = MaterialTheme.typography.titleSmall,
                                fontSize = 12.sp,
                                color = if (isCurrent) ImperialGold else MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = hour.desc,
                                fontSize = 10.sp,
                                color = Color(0xFFD0C5AF),
                                lineHeight = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // Daily Yi & Ji (宜 / 忌) Strip
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .padding(10.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(AlchemicalJade.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "宜",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AlchemicalJade
                        )
                    }
                    Text(
                        text = " 签约 · 商务会谈 · 求谋开拓 · 祈福安康",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(SurfaceContainerHighest)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(CinnabarRed.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "忌",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = CinnabarRed
                        )
                    }
                    Text(
                        text = " 大兴动土 · 长途远涉 · 争讼争执 · 借贷担保",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFD0C5AF),
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }
        }

        // Five Elements Balance (本命五行元气权衡)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceContainer.copy(alpha = 0.9f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Column {
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
                                .background(EtherealCyan)
                        )
                        Text(
                            text = " 本命五行元气权衡",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = "五行相生 · 土金双显",
                        style = MaterialTheme.typography.labelSmall,
                        color = ImperialGold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                ElementalBarGroup(
                    metalRatio = 32,
                    woodRatio = 18,
                    waterRatio = 14,
                    fireRatio = 22,
                    earthRatio = 14
                )
            }
        }

        // Daily Core Revelation Card (今日卦策核心启示)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceContainerHigh.copy(alpha = 0.9f))
                .border(1.dp, ImperialGold.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                .padding(14.dp)
                .testTag("revelation_card")
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = ImperialGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = " 今日卦策核心启示",
                            style = MaterialTheme.typography.labelMedium,
                            color = ImperialGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainer)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "主卦: 火地晋",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLowest.copy(alpha = 0.7f))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "“火土相生，顺德而丽大明。今日利于开拓新局，宜在未时（15:00）前定夺商约重事，顺应天心，自见天朗。”",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = AlchemicalJade,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = " 命局顺畅指数: 94%",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(Screen.GanzhiDeduction) }
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "查看全盘大运",
                            style = MaterialTheme.typography.labelMedium,
                            color = ImperialGold
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = ImperialGold,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}
