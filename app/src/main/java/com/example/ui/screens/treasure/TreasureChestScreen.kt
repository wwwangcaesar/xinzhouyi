package com.example.ui.screens.treasure

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.calculator.DreamData
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
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TianYanViewModel

@Composable
fun TreasureChestScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    var searchInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp)
            .testTag("treasure_chest_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Banner
        Column(modifier = Modifier.padding(top = 4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "● CYBER METAPHYSICS BOX",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGold,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = AlchemicalJade,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = " 灵枢已就绪",
                        style = MaterialTheme.typography.labelSmall,
                        color = AlchemicalJade
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "天衍百宝箱",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "周公解梦 · 灵签占卜 · 易数微工具",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD0C5AF)
            )
        }

        // 周公智能AI解梦 Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(14.dp)
                .testTag("ai_dream_oracle_card")
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceContainerHigh),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = ImperialGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text(
                                text = "周公智能AI解梦",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "天象感应 · 潜意识通玄演算",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFD0C5AF),
                                fontSize = 9.sp
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(EtherealCyan.copy(alpha = 0.15f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "⚡ GPT-玄微",
                            style = MaterialTheme.typography.labelSmall,
                            color = EtherealCyan
                        )
                    }
                }

                // Search / Input bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLowest)
                        .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(10.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Nightlight,
                                contentDescription = null,
                                tint = ImperialGold,
                                modifier = Modifier.size(16.dp)
                            )
                            OutlinedTextField(
                                value = searchInput,
                                onValueChange = { searchInput = it },
                                placeholder = {
                                    Text(
                                        text = "输入昨夜梦境关键词，如：梦见金鲤、飞翔...",
                                        fontSize = 11.sp,
                                        color = Color(0xFF99907C)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("dream_input_field"),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                keyboardActions = KeyboardActions(onSearch = {
                                    if (searchInput.isNotBlank()) {
                                        viewModel.searchDream(searchInput)
                                    }
                                }),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = null,
                                    tint = Color(0xFFD0C5AF),
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = " 语音联想已激活",
                                    fontSize = 10.sp,
                                    color = Color(0xFFD0C5AF)
                                )
                            }

                            Button(
                                onClick = {
                                    val q = if (searchInput.isBlank()) "金鲤跃龙门" else searchInput
                                    viewModel.searchDream(q)
                                },
                                modifier = Modifier
                                    .height(34.dp)
                                    .testTag("interpret_dream_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = ImperialGold,
                                    contentColor = OnImperialGold
                                ),
                                shape = RoundedCornerShape(17.dp)
                            ) {
                                Text(
                                    text = "解梦释意 ↗",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Hot Keywords
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "热门梦兆:", style = MaterialTheme.typography.labelSmall, color = Color(0xFF99907C))
                    DreamData.HOT_KEYWORDS.forEach { kw ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainerHigh)
                                .clickable {
                                    searchInput = kw
                                    viewModel.searchDream(kw)
                                }
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(text = kw, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }

                // Featured Dream Card (梦见金鲤跃龙门)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .border(1.dp, ImperialGold.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                        .clickable { viewModel.openDream(DreamData.FEATURED_DREAM) }
                        .padding(12.dp)
                        .testTag("featured_dream_item")
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(ImperialGold.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "吉", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ImperialGold)
                                }
                                Column(modifier = Modifier.padding(start = 8.dp)) {
                                    Text(
                                        text = "梦见金鲤跃龙门",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "● 上上吉 · 主仕途显达与转机出现",
                                        fontSize = 9.sp,
                                        color = ImperialGoldFixed
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceContainerHighest)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(text = "昨日精选", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                            }
                        }

                        Text(
                            text = "昔人有云：鲤鱼跃龙门，身价百倍。梦此者无论科考求职、商战博弈，皆逢九紫离火流年之生旺助益。三日...",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFD0C5AF),
                            lineHeight = 16.sp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "⚚ 五行配比：金水相生 88%",
                                style = MaterialTheme.typography.labelSmall,
                                color = EtherealCyan
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "吉凶化解详析",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = ImperialGold
                                )
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
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

        // 玄学工具矩阵 (FOUR CELESTIAL PILLARS)
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
                            .background(ImperialGold)
                    )
                    Text(
                        text = " 玄学工具矩阵",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = "FOUR CELESTIAL PILLARS",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 2x2 Grid of tools
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Tool 1: 文王六十四签
                PillarToolCard(
                    icon = "☱",
                    badge = "神应",
                    title = "文王六十四签",
                    desc = "每日诚心一掷，断当前疑难祸福",
                    actionText = "摇签解卦",
                    onClick = { viewModel.navigateTo(Screen.KingWen) },
                    modifier = Modifier.weight(1f)
                )

                // Tool 2: 梅花易数速占
                PillarToolCard(
                    icon = "❄",
                    badge = "立决",
                    title = "梅花易数速占",
                    desc = "见物起卦，以天地数机断事",
                    actionText = "万物起数",
                    onClick = { viewModel.navigateTo(Screen.MeiHua) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Tool 3: 袁天罡称骨
                PillarToolCard(
                    icon = "⚖",
                    badge = "定数",
                    title = "袁天罡称骨",
                    desc = "骨重知福禄，一世荣枯由命定",
                    actionText = "命重核验",
                    onClick = { viewModel.toggleBoneDialog(true) },
                    modifier = Modifier.weight(1f)
                )

                // Tool 4: 姓名数理吉凶
                PillarToolCard(
                    icon = "◎",
                    badge = "音律",
                    title = "姓名数理吉凶",
                    desc = "三才五格剖析与能量配比天平",
                    actionText = "姓名评测",
                    onClick = { viewModel.toggleNameDialog(true) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // 吉日良辰择吉
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = AlchemicalJade,
                            modifier = Modifier.size(18.dp)
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = "吉日良辰择吉",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "天德月德星临 · 诸事宜合",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFD0C5AF),
                                fontSize = 9.sp
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { }
                    ) {
                        Text(
                            text = "万年历",
                            style = MaterialTheme.typography.labelSmall,
                            color = ImperialGold
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = ImperialGold,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                // Three date pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AuspiciousDatePill("嫁娶大吉", "03.18", "二月初十", "岁德合 · 司命...", AlchemicalJade, Modifier.weight(1f))
                    AuspiciousDatePill("移徙安居", "03.22", "二月十四", "天喜星 · 进宅...", EtherealCyan, Modifier.weight(1f))
                    AuspiciousDatePill("开市纳财", "03.26", "二月十八", "金匮道 · 万商...", ImperialGold, Modifier.weight(1f))
                }

                // Custom matching banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "☸", color = ImperialGold, fontSize = 14.sp)
                            Text(
                                text = " 精准量身择吉（八字配对）",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(SurfaceContainerHighest)
                                .clickable { }
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "定制良辰",
                                fontSize = 9.sp,
                                color = ImperialGold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PillarToolCard(
    icon: String,
    badge: String,
    title: String,
    desc: String,
    actionText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceContainerHigh)
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(12.dp)
            .testTag("tool_card_${title}")
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerHighest),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = icon, fontSize = 16.sp, color = ImperialGold)
                }

                Text(
                    text = badge,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF),
                    fontSize = 9.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                fontSize = 10.sp,
                color = Color(0xFFD0C5AF),
                lineHeight = 14.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = actionText,
                    fontSize = 10.sp,
                    color = Color(0xFFD0C5AF)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = ImperialGold,
                    modifier = Modifier.size(13.dp)
                )
            }
        }
    }
}

@Composable
private fun AuspiciousDatePill(
    tag: String,
    date: String,
    lunarDate: String,
    desc: String,
    tagColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(tagColor.copy(alpha = 0.2f))
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            ) {
                Text(text = tag, fontSize = 8.sp, color = tagColor, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date, style = MaterialTheme.typography.labelMedium, color = Color(0xFFD0C5AF))
            Text(
                text = lunarDate,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(text = desc, fontSize = 8.sp, color = Color(0xFF99907C), maxLines = 1)
        }
    }
}
