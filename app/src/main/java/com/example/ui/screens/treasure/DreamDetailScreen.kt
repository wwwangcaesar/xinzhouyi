package com.example.ui.screens.treasure

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.R
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
fun DreamDetailScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val dream by viewModel.selectedDream.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var followUpInput by remember { mutableStateOf("") }
    var savedSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceVoid)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("dream_detail_screen"),
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
                    text = "Dream Detail & Ai I...",
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
                        viewModel.saveDivinationRecord("DREAM", dream.title, dream.omenBadge, dream.ancientInterpretation)
                        savedSuccess = true
                        Toast.makeText(context, "已存入易数卷宗", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = if (savedSuccess) Icons.Default.Check else Icons.Default.BookmarkBorder,
                        contentDescription = "保存",
                        tint = if (savedSuccess) AlchemicalJade else ImperialGold
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

        // Tags Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(SurfaceContainerHigh)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(text = dream.category, fontSize = 9.sp, color = ImperialGold)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF003824).copy(alpha = 0.7f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(text = dream.luckScore, fontSize = 9.sp, color = AlchemicalJade)
                }
            }
            Text(text = dream.hexagramRef, fontSize = 9.sp, color = EtherealCyan)
        }

        // Title and Badge
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = ImperialGold,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = " ${dream.title}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily.Serif
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(ImperialGold.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "✨ ${dream.omenBadge}",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGold,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Dream Quote Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = null,
                    tint = EtherealCyan,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = " ${dream.quote}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 18.sp
                )
            }
        }

        // Hero Image Banner (Visual Metaphor)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, ImperialGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
        ) {
            // Render custom generated image asset
            Image(
                painter = painterResource(id = R.drawable.dream_golden_koi),
                contentDescription = "逆水化龙 · 意象投射",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient dark scrim over bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color(0xFF0F131D).copy(alpha = 0.85f))
                        )
                    )
            )

            // Caption overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
            ) {
                Text(
                    text = "天机演象 · 意象投射",
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialGoldFixed
                )
                Text(
                    text = "逆水化龙 · 气冲霄汉",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.Serif
                )
            }
        }

        // 玄微析梦 · 双向对照
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📖 玄微析梦 · 双向对照",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "天衍推演模型 v4.2",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFD0C5AF)
                    )
                }

                // Card 1: 周公原典易理
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .padding(12.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "☸ 周公原典易理",
                                style = MaterialTheme.typography.labelSmall,
                                color = ImperialGold,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "《断梦秘书》卷四",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFD0C5AF)
                            )
                        }

                        Text(
                            text = dream.ancientInterpretation,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 18.sp
                        )
                    }
                }

                // Card 2: 荣格原型
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .padding(12.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = EtherealCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = " 深层心理映射 · 荣格原型",
                                style = MaterialTheme.typography.labelSmall,
                                color = EtherealCyan,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = dream.psychologicalInterpretation,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // 五行能量与气场加持
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
                    Text(
                        text = "☉ 五行能量与气场加持",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF003824).copy(alpha = 0.7f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = "金水相涵", style = MaterialTheme.typography.labelSmall, color = AlchemicalJade)
                    }
                }

                Text(
                    text = "梦象元气生克配比 · 灵蕴流转",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF)
                )

                // Element bars
                ElementProgressBar("水 (坎水 · 智慧与财富本源)", dream.waterRatio, EtherealCyan)
                ElementProgressBar("金 (兑金 · 决断与蜕变动能)", dream.metalRatio, ImperialGold)
                ElementProgressBar("火 (离火 · 声名与灵明破障)", dream.fireRatio, Color(0xFFFF8364))

                Spacer(modifier = Modifier.height(4.dp))

                // Auspicious Details
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLow)
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = ImperialGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(text = "天时运期 · 决断吉时", style = MaterialTheme.typography.labelSmall, color = ImperialGold)
                            Text(text = dream.auspiciousTime, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainerLow)
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CompassCalibration, null, tint = AlchemicalJade, modifier = Modifier.size(14.dp))
                                Text(" 吉利方位", style = MaterialTheme.typography.labelSmall, color = AlchemicalJade)
                            }
                            Text(dream.auspiciousDirection, style = MaterialTheme.typography.titleSmall, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainerLow)
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Star, null, tint = ImperialGold, modifier = Modifier.size(14.dp))
                                Text(" 助运着装", style = MaterialTheme.typography.labelSmall, color = ImperialGold)
                            }
                            Text(dream.luckyAttire, style = MaterialTheme.typography.titleSmall, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        }

        // 互动重温 · 追问演变
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
                    Text(
                        text = "💬 互动重温 · 追问演变",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = "● 灵枢常驻", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
                }

                Text(
                    text = "梦境若有余波或细部变动，可直接追问，天衍周公将即刻演变卦辞：",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD0C5AF)
                )

                // Follow up chips
                dream.followUpQuestions.forEach { q ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainerLow)
                            .clickable {
                                followUpInput = q
                                Toast.makeText(context, "天衍演算法正深入推演此境...", Toast.LENGTH_SHORT).show()
                            }
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = q, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = ImperialGold,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }

                // Input Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLowest)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = followUpInput,
                            onValueChange = { followUpInput = it },
                            placeholder = { Text("向天衍周公追问梦兆细节...", fontSize = 11.sp, color = Color(0xFF99907C)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Icon(Icons.Default.Mic, null, tint = Color(0xFFD0C5AF), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(
                            onClick = {
                                if (followUpInput.isNotBlank()) {
                                    Toast.makeText(context, "天衍周公已感应，玄微推演中...", Toast.LENGTH_SHORT).show()
                                    followUpInput = ""
                                }
                            },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(EtherealCyan)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "发送",
                                tint = Color(0xFF003640),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        // Bottom Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    viewModel.saveDivinationRecord("DREAM", dream.title, dream.omenBadge, dream.ancientInterpretation)
                    savedSuccess = true
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
                Text(" 存入易数卷宗", style = MaterialTheme.typography.bodyMedium)
            }

            Button(
                onClick = {
                    Toast.makeText(context, "已结缘生成吉签卡片", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ImperialGold,
                    contentColor = OnImperialGold
                )
            ) {
                Icon(Icons.Default.Share, null, modifier = Modifier.size(16.dp))
                Text(" 结缘分享吉签", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
        }

        Text(
            text = "玄机潜藏，敬信而不偏执 · 天衍周公梦镜秘录",
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF99907C),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun ElementProgressBar(
    label: String,
    ratio: Int,
    color: Color
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "● $label", style = MaterialTheme.typography.labelSmall, color = color)
            Text(text = "$ratio%", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(SurfaceContainerHighest)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(ratio / 100f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )
        }
    }
}
