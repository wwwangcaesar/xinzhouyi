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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Star
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
import com.example.ui.components.BambooPotView
import com.example.ui.components.ShengBeiView
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
fun KingWenDivinationScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val step by viewModel.kingWenStep.collectAsStateWithLifecycle()
    val category by viewModel.kingWenCategory.collectAsStateWithLifecycle()
    val currentSign by viewModel.currentSign.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var isSaved by remember { mutableStateOf(false) }

    val categories = listOf("事业前程", "财运进退", "姻缘正果", "健康疾厄")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceVoid)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("king_wen_screen"),
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
                            "KING_WEN",
                            currentSign.title,
                            currentSign.grade,
                            currentSign.verseLines.joinToString(" / ")
                        )
                        isSaved = true
                        Toast.makeText(context, "已录入百宝箱易数卷宗", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.Check else Icons.Default.BookmarkBorder,
                        contentDescription = "录入",
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

        // 诚心祈念 · 屏息净气 Card
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
                    Text(
                        text = "☸ 诚心祈念 · 屏息净气",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(EtherealCyan.copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "● 感应中", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
                    }
                }

                // 4-step buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    SignStepPill("1", "默念所求", isCurrent = (step == 1), Modifier.weight(1f)) { viewModel.nextKingWenStep() }
                    SignStepPill("2", "诚心摇筒", isCurrent = (step == 2), Modifier.weight(1f)) { viewModel.nextKingWenStep() }
                    SignStepPill("3", "掷筊定乾坤", isCurrent = (step == 3), Modifier.weight(1f)) { viewModel.nextKingWenStep() }
                    SignStepPill("4", "解读签诗", isCurrent = (step == 4), Modifier.weight(1f)) { viewModel.nextKingWenStep() }
                }

                Text(
                    text = "选择占问事由 (QUESTION CATEGORY)",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { cat ->
                        val isCatActive = (cat == category)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isCatActive) ImperialGold else SurfaceContainerHigh)
                                .clickable { viewModel.setKingWenCategory(cat) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (isCatActive) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = OnImperialGold,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                                Text(
                                    text = cat,
                                    fontSize = 11.sp,
                                    fontWeight = if (isCatActive) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isCatActive) OnImperialGold else Color(0xFFD0C5AF)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bamboo Pot
        BambooPotView(
            signNumber = currentSign.number,
            signHexagram = currentSign.hexagramName,
            onShakeComplete = {
                viewModel.nextKingWenStep()
                Toast.makeText(context, "已摇出灵签：${currentSign.title}", Toast.LENGTH_SHORT).show()
            }
        )

        // ShengBei toss component
        ShengBeiView(
            isShengBei = true,
            onToss = {
                viewModel.nextKingWenStep()
                Toast.makeText(context, "掷得圣杯 · 阴阳定局！", Toast.LENGTH_SHORT).show()
            }
        )

        // Poetic Sign Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(16.dp)
                .testTag("king_wen_sign_result")
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "KING WEN ORACLE NO.${currentSign.number}",
                            style = MaterialTheme.typography.labelSmall,
                            color = EtherealCyan,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = currentSign.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = FontFamily.Serif
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF93000A).copy(alpha = 0.8f))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = currentSign.grade,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFB4AB)
                            )
                            Text(
                                text = currentSign.hexagramName,
                                fontSize = 9.sp,
                                color = Color(0xFFFFDAD6)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "【上上之格】", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF690005).copy(alpha = 0.7f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "文王圣谕", fontSize = 9.sp, color = Color(0xFFFFB4AB))
                    }
                }

                // Four-Column Traditional Poem Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLowest.copy(alpha = 0.8f))
                        .border(1.dp, ImperialGold.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        currentSign.verseLines.forEach { line ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                line.forEach { char ->
                                    Text(
                                        text = char.toString(),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ImperialGold,
                                        fontFamily = FontFamily.Serif
                                    )
                                }
                            }
                        }
                    }
                }

                Text(
                    text = currentSign.deduction,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD0C5AF),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        // 仙机玄解 · 逐事指迷
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "● 仙机玄解 · 逐事指迷",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Domain 1: 谋望功名
            DomainGuidanceCard(
                icon = Icons.Default.Explore,
                title = "谋望功名",
                badge = "大遂所愿",
                badgeColor = AlchemicalJade,
                content = currentSign.careerGuidance
            )

            // Domain 2: 财禄生发
            DomainGuidanceCard(
                icon = Icons.Default.Savings,
                title = "财禄生发",
                badge = "偏正皆旺",
                badgeColor = EtherealCyan,
                content = currentSign.wealthGuidance
            )

            // Domain 3: 交易合作
            DomainGuidanceCard(
                icon = Icons.Default.Handshake,
                title = "交易合作",
                badge = "诚心必合",
                badgeColor = ImperialGold,
                content = currentSign.transactionGuidance
            )
        }

        // AI Deep Interpretation CTA
        Button(
            onClick = {
                Toast.makeText(context, "天衍AI已介入，正在推演文王六爻互卦深层机密...", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SurfaceContainerHigh,
                contentColor = EtherealCyan
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Icon(Icons.Default.AutoAwesome, null, modifier = Modifier.size(16.dp))
            Text(" 解签秘策 · AI深研卦爻命理", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }

        // Bottom Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    viewModel.saveDivinationRecord(
                        "KING_WEN",
                        currentSign.title,
                        currentSign.grade,
                        currentSign.verseLines.joinToString(" / ")
                    )
                    isSaved = true
                    Toast.makeText(context, "已录入百宝箱易数卷宗", Toast.LENGTH_SHORT).show()
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
                Text(" 录入百宝箱", style = MaterialTheme.typography.bodyMedium)
            }

            Button(
                onClick = {
                    viewModel.resetKingWen()
                    isSaved = false
                    Toast.makeText(context, "已净手，清心再起文王神签", Toast.LENGTH_SHORT).show()
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
                Text(" 净手重新起签", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun SignStepPill(
    number: String,
    title: String,
    isCurrent: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isCurrent) ImperialGold.copy(alpha = 0.2f) else SurfaceContainerLow)
            .border(
                1.dp,
                if (isCurrent) ImperialGold else Color(0xFF4D4635).copy(alpha = 0.3f),
                RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(if (isCurrent) ImperialGold else SurfaceContainerHighest),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isCurrent) OnImperialGold else Color(0xFFD0C5AF)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                fontSize = 9.sp,
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                color = if (isCurrent) ImperialGold else Color(0xFFD0C5AF)
            )
        }
    }
}

@Composable
private fun DomainGuidanceCard(
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
