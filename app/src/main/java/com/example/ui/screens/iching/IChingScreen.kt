package com.example.ui.screens.iching

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.Hexagram
import com.example.domain.calculator.IChingData
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
import com.example.ui.viewmodel.TianYanViewModel

@Composable
fun IChingScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val filter by viewModel.ichingFilter.collectAsStateWithLifecycle()
    val query by viewModel.ichingQuery.collectAsStateWithLifecycle()
    val selectedHexagram by viewModel.selectedHexagram.collectAsStateWithLifecycle()

    val hexagrams = IChingData.HEXAGRAMS.filter { h ->
        val matchesQuery = query.isBlank() || h.name.contains(query) || h.englishName.contains(query, ignoreCase = true) || h.upperTrigram.contains(query) || h.lowerTrigram.contains(query)
        val matchesFilter = when (filter) {
            "全部 (64)" -> true
            else -> {
                val trigramKey = filter.take(1)
                h.upperTrigram == trigramKey || h.lowerTrigram == trigramKey
            }
        }
        matchesQuery && matchesFilter
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp)
            .testTag("iching_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Banner Title
        Column(modifier = Modifier.padding(top = 4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "● CYBER-ICHING MATRIX",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "HEXAGRAM.64",
                    style = MaterialTheme.typography.labelSmall,
                    color = EtherealCyan
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "易理研习",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "道生万物 · 六十四卦义理全览",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD0C5AF)
            )
        }

        // 今日灵卦 Card (01 乾为天)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, ImperialGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(14.dp)
                .testTag("daily_oracle_hexagram")
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "✨ 今日灵卦 · 序卦第零一", style = MaterialTheme.typography.labelSmall, color = ImperialGold, fontWeight = FontWeight.Bold)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF003824).copy(alpha = 0.7f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "元亨 · 上上卦", style = MaterialTheme.typography.labelSmall, color = AlchemicalJade)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Big Hexagram Symbol
                    HexagramSymbolView(
                        lines = listOf(true, true, true, true, true, true),
                        width = 48.dp,
                        lineHeight = 5.dp,
                        spacing = 4.dp,
                        activeColor = ImperialGold
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "乾为天",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = ImperialGold,
                                fontFamily = FontFamily.Serif
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "THE CREATIVE",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFD0C5AF)
                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "上乾下乾 · 天行健，君子以自强不息",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                }

                // 卦辞解悟
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLowest)
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Lightbulb, null, tint = EtherealCyan, modifier = Modifier.size(14.dp))
                            Text(" 卦辞解悟", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
                        }
                        Text(
                            text = "「元亨利贞。天行健，君子以自强不息。」",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "[九五动爻] 飞龙在天，利见大人。盛德居中，乘时而发。",
                            style = MaterialTheme.typography.bodySmall,
                            color = ImperialGoldFixed,
                            fontSize = 11.sp
                        )
                    }
                }

                Button(
                    onClick = { viewModel.selectHexagram(IChingData.HEXAGRAMS.first()) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ImperialGold,
                        contentColor = OnImperialGold
                    ),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Text("研读卦变与爻辞详解 ↗", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Search & Filter Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, null, tint = Color(0xFFD0C5AF), modifier = Modifier.size(18.dp))
                OutlinedTextField(
                    value = query,
                    onValueChange = { viewModel.setIChingQuery(it) },
                    placeholder = {
                        Text("输入卦名、卦序或上下卦（如：泰、水火）", fontSize = 11.sp, color = Color(0xFF99907C))
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                Icon(Icons.Default.FilterList, null, tint = ImperialGold, modifier = Modifier.size(18.dp))
            }
        }

        // 八卦原象 (TRIGRAM SELECTOR)
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "八卦原象 (TRIGRAM SELECTOR)", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
                Text(text = "全览 (${IChingData.HEXAGRAMS.size})", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IChingData.TRIGRAMS.forEach { t ->
                    val isSelected = (t == filter)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) ImperialGold else SurfaceContainerHigh)
                            .clickable { viewModel.setIChingFilter(t) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = t,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) OnImperialGold else Color(0xFFD0C5AF)
                        )
                    }
                }
            }
        }

        // 经卦矩阵 (List of Hexagram cards)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "经卦矩阵",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(text = "精研六爻 · 阐释运律", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
            }

            hexagrams.forEach { hex ->
                HexagramListItem(
                    hexagram = hex,
                    onClick = { viewModel.selectHexagram(hex) }
                )
            }
        }

        // 研习笔记 · 观变玩占 Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(ImperialGold.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.MenuBook, null, tint = ImperialGold, modifier = Modifier.size(18.dp))
                }
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = "研习笔记 · 观变玩占",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "易与天地准，故能弥纶天地之道。点击任一卦象即可推衍动爻与变卦。",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFD0C5AF),
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }

    // Hexagram Detail Sheet Dialog
    selectedHexagram?.let { hex ->
        HexagramDetailDialog(
            hexagram = hex,
            onDismiss = { viewModel.selectHexagram(null) }
        )
    }
}

@Composable
private fun HexagramListItem(
    hexagram: Hexagram,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainer.copy(alpha = 0.9f))
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.35f), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Miniature 6 lines
                HexagramSymbolView(
                    lines = hexagram.lines,
                    width = 28.dp,
                    lineHeight = 3.dp,
                    spacing = 2.dp,
                    activeColor = if (hexagram.lines.all { it }) ImperialGold else if (hexagram.lines.none { it }) AlchemicalJade else EtherealCyan
                )

                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${hexagram.number.toString().padStart(2, '0')} ",
                            style = MaterialTheme.typography.labelSmall,
                            color = ImperialGold
                        )
                        Text(
                            text = hexagram.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = FontFamily.Serif
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = hexagram.englishName,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = hexagram.tag,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFD0C5AF),
                        fontSize = 11.sp
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(SurfaceContainerHigh)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = hexagram.omen,
                        fontSize = 9.sp,
                        color = if (hexagram.omen.contains("大吉")) ImperialGold else AlchemicalJade,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "上${hexagram.upperTrigram} / 下${hexagram.lowerTrigram}",
                    fontSize = 9.sp,
                    color = Color(0xFF99907C)
                )
            }
        }
    }
}

@Composable
private fun HexagramDetailDialog(
    hexagram: Hexagram,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(SurfaceContainer.copy(alpha = 0.98f))
                .border(1.dp, ImperialGold.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        HexagramSymbolView(
                            lines = hexagram.lines,
                            width = 30.dp,
                            lineHeight = 3.dp,
                            spacing = 2.dp,
                            activeColor = ImperialGold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = hexagram.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = ImperialGold,
                                fontFamily = FontFamily.Serif
                            )
                            Text(
                                text = "上${hexagram.upperTrigram}下${hexagram.lowerTrigram} · ${hexagram.englishName}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFD0C5AF)
                            )
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.Close, null, tint = Color(0xFFD0C5AF))
                    }
                }

                // 卦辞
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("【彖辞】", style = MaterialTheme.typography.labelSmall, color = ImperialGold)
                        Text(hexagram.tuanCi, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface, lineHeight = 17.sp)
                    }
                }

                // 大象传
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("【象传】", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
                        Text(hexagram.xiangCi, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface, lineHeight = 17.sp)
                    }
                }

                // 六爻爻辞
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("【六爻爻辞】", style = MaterialTheme.typography.labelSmall, color = AlchemicalJade)
                    hexagram.yaoLines.forEach { line ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceContainerLowest)
                                .padding(8.dp)
                        ) {
                            Text(line, style = MaterialTheme.typography.bodySmall, color = Color(0xFFD0C5AF), fontSize = 11.sp)
                        }
                    }
                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ImperialGold,
                        contentColor = OnImperialGold
                    ),
                    shape = RoundedCornerShape(21.dp)
                ) {
                    Text("研毕悟玄", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
