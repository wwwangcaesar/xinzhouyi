package com.example.ui.screens.treasure

import android.widget.Toast
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.Hexagram
import com.example.ui.components.HexagramSymbolView
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
fun MeiHuaScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val result by viewModel.meiHuaResult.collectAsStateWithLifecycle()
    val mode by viewModel.meiHuaMode.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var isSaved by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceVoid)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("meihua_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateTo(Screen.Treasure) },
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

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        viewModel.saveDivinationRecord(
                            "MEIHUA",
                            "梅花速占 · ${result.originalHexagram.name}",
                            result.relationVerdict,
                            result.judgment
                        )
                        isSaved = true
                        Toast.makeText(context, "已存入易数卷宗", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.Check else Icons.Default.BookmarkBorder,
                        contentDescription = "保存",
                        tint = if (isSaved) AlchemicalJade else ImperialGold
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
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
        }

        // Sub Status Strip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "● 机动即占 · 动爻定象", style = MaterialTheme.typography.labelSmall, color = AlchemicalJade)
            Text(text = "✨ 甲辰年 · 惊蛰", style = MaterialTheme.typography.labelSmall, color = ImperialGold)
        }

        // 起卦方式 3 Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainerLow)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ModeTabItem("时数局 · 年月日时", Icons.Default.AccessTime, isSelected = (mode == 0), Modifier.weight(1.2f)) {
                viewModel.setMeiHuaMode(0)
                viewModel.recalculateMeiHua(5, 2, 17, 5)
            }
            ModeTabItem("报数起卦", Icons.Default.FormatListNumbered, isSelected = (mode == 1), Modifier.weight(1f)) {
                viewModel.setMeiHuaMode(1)
                val r1 = (1..8).random()
                val r2 = (1..8).random()
                val r3 = (1..6).random()
                viewModel.recalculateMeiHua(r1, r2, r3, (1..12).random())
                Toast.makeText(context, "天机随数而现：$r1, $r2, $r3", Toast.LENGTH_SHORT).show()
            }
            ModeTabItem("物象触动", Icons.Default.CameraAlt, isSelected = (mode == 2), Modifier.weight(1f)) {
                viewModel.setMeiHuaMode(2)
                viewModel.recalculateMeiHua(7, 3, 19, 8)
                Toast.makeText(context, "物象感召：巽木遇火，天机萌动", Toast.LENGTH_SHORT).show()
            }
        }

        // 时空数理推演 Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
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
                            text = " 时空数理推演",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(text = "梅花数律 · 逢八取余", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
                }

                // Formula 1: 上卦数
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceContainerHighest)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("上卦数", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                            }
                            Text(
                                text = "  ${result.upperFormula.substringBefore("余")}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "余 ${result.upperNum} · ${result.originalHexagram.upperTrigram}地 ☷",
                            style = MaterialTheme.typography.labelMedium,
                            color = ImperialGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Formula 2: 下卦数
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceContainerHighest)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("下卦数", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                            }
                            Text(
                                text = "  ${result.lowerFormula.substringBefore("余")}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "余 ${result.lowerNum} · ${result.originalHexagram.lowerTrigram}风 ☴",
                            style = MaterialTheme.typography.labelMedium,
                            color = EtherealCyan,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Formula 3: 动爻数
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceContainerHighest)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("动爻数", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                            }
                            Text(
                                text = "  时空合数 29 ÷ 6 取余",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "动在五爻（六五）",
                            style = MaterialTheme.typography.labelMedium,
                            color = AlchemicalJade,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // 三维卦象阵列 (本卦, 互卦, 变卦)
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
                        text = " 三维卦象阵列",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(text = "动应枢纽：上坤下巽", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 本卦
                HexagramDisplayCard(
                    stage = "本卦",
                    stageSub = "初局",
                    hexagram = result.originalHexagram,
                    movingYaoIndex = result.movingYao,
                    color = ImperialGold,
                    tag = "木入土中 · 积小高大",
                    modifier = Modifier.weight(1f)
                )

                // 互卦
                HexagramDisplayCard(
                    stage = "互卦",
                    stageSub = "变机",
                    hexagram = result.mutualHexagram,
                    movingYaoIndex = null,
                    color = Color(0xFFDFE2F1),
                    tag = "情志暗动 · 守正则吉",
                    modifier = Modifier.weight(1f)
                )

                // 变卦
                HexagramDisplayCard(
                    stage = "变卦",
                    stageSub = "终局",
                    hexagram = result.transformedHexagram,
                    movingYaoIndex = null,
                    color = AlchemicalJade,
                    tag = "谦尊而光 · 终成大猷",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // 体用生克衡鉴 Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
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
                            text = " 体用生克衡鉴",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF003824).copy(alpha = 0.7f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = result.relationVerdict, style = MaterialTheme.typography.labelSmall, color = AlchemicalJade)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // 体卦
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(ImperialGold.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(result.tiElement, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ImperialGold)
                            }
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text("体卦（主） ${result.tiGuaName}", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                Text(result.tiDesc, fontSize = 9.sp, color = Color(0xFFD0C5AF), maxLines = 1)
                            }
                        }
                    }

                    // 用卦
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(EtherealCyan.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(result.yongElement, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EtherealCyan)
                            }
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text("用卦（客） ${result.yongGuaName}", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                Text(result.yongDesc, fontSize = 9.sp, color = Color(0xFFD0C5AF), maxLines = 1)
                            }
                        }
                    }
                }

                // Two phase gradient progress bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("初期气运承压（用克体 木克土）", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                    Text("变卦艮土比和（土土相生）", fontSize = 9.sp, color = AlchemicalJade)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                ) {
                    Box(modifier = Modifier.weight(1f).background(EtherealCyan))
                    Box(modifier = Modifier.weight(1.5f).background(ImperialGold))
                    Box(modifier = Modifier.weight(2f).background(AlchemicalJade))
                }
            }
        }

        // 仙机秘断与决断指引
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "● 仙机秘断与决断指引",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // 卦胆断语
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, ImperialGold.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, null, tint = ImperialGold, modifier = Modifier.size(16.dp))
                        Text(" 卦胆断语", style = MaterialTheme.typography.labelSmall, color = ImperialGold, fontWeight = FontWeight.Bold)
                    }
                    Text(
                        text = result.judgment,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 20.sp
                    )
                }
            }

            // 谋事求职
            MeiHuaDetailCard(
                icon = Icons.Default.Business,
                title = "谋事求职 · 步步升高",
                badge = "大吉",
                badgeColor = AlchemicalJade,
                content = result.careerAdvice
            )

            // 财运商洽
            MeiHuaDetailCard(
                icon = Icons.Default.Savings,
                title = "财运商洽 · 慎始丰终",
                badge = "次吉",
                badgeColor = EtherealCyan,
                content = result.wealthAdvice
            )

            // 缘分相处
            MeiHuaDetailCard(
                icon = Icons.Default.Favorite,
                title = "缘分相处 · 诚笃致和",
                badge = "和合",
                badgeColor = AlchemicalJade,
                content = result.harmonyAdvice
            )
        }

        // AI 深度推演 CTA
        Button(
            onClick = {
                Toast.makeText(context, "已接入天衍易理引擎，推演六神与三传...", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(25.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = ImperialGold,
                contentColor = OnImperialGold
            )
        ) {
            Icon(Icons.Default.AutoAwesome, null, modifier = Modifier.size(18.dp))
            Text(" ⚙ AI 深度推演应期吉凶 · 解锁六神全图", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }

        // Bottom Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    viewModel.recalculateMeiHua()
                    Toast.makeText(context, "已动念另择时空吉数", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SurfaceContainerHigh,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(Icons.Default.Refresh, null, modifier = Modifier.size(16.dp))
                Text(" 动念重启 · 另择吉数", style = MaterialTheme.typography.bodySmall)
            }

            Button(
                onClick = {
                    viewModel.saveDivinationRecord(
                        "MEIHUA",
                        "梅花速占 · ${result.originalHexagram.name}",
                        result.relationVerdict,
                        result.judgment
                    )
                    isSaved = true
                    Toast.makeText(context, "已存入易数卷宗", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SurfaceContainerHigh,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(Icons.Default.BookmarkBorder, null, modifier = Modifier.size(16.dp))
                Text(" 存入易数卷宗", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun ModeTabItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) ImperialGold else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) OnImperialGold else Color(0xFFD0C5AF),
                modifier = Modifier.size(13.dp)
            )
            Text(
                text = " $title",
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) OnImperialGold else Color(0xFFD0C5AF)
            )
        }
    }
}

@Composable
private fun HexagramDisplayCard(
    stage: String,
    stageSub: String,
    hexagram: Hexagram,
    movingYaoIndex: Int?,
    color: Color,
    tag: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .border(1.dp, color.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(color.copy(alpha = 0.2f))
                        .padding(horizontal = 4.dp, vertical = 1.dp)
                ) {
                    Text(text = stage, fontSize = 8.sp, color = color, fontWeight = FontWeight.Bold)
                }
                Text(text = stageSub, fontSize = 8.sp, color = Color(0xFFD0C5AF))
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = hexagram.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = color,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "${hexagram.upperTrigram}上 · ${hexagram.lowerTrigram}下",
                fontSize = 9.sp,
                color = Color(0xFFD0C5AF)
            )

            Spacer(modifier = Modifier.height(10.dp))

            HexagramSymbolView(
                lines = hexagram.lines,
                width = 54.dp,
                lineHeight = 4.dp,
                spacing = 3.dp,
                activeColor = color,
                movingYaoIndex = movingYaoIndex
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(SurfaceContainerHighest)
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text(text = tag, fontSize = 7.sp, color = Color(0xFFD0C5AF), maxLines = 1)
            }
        }
    }
}

@Composable
private fun MeiHuaDetailCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    badge: String,
    badgeColor: Color,
    content: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainer.copy(alpha = 0.9f))
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceContainerHigh),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = badgeColor, modifier = Modifier.size(18.dp))
            }
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = badgeColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD0C5AF),
                    lineHeight = 17.sp
                )
            }
        }
    }
}
