package com.example.ui.screens.profile

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldFixedDim
import com.example.ui.theme.OnImperialGold
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceVoid
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TianYanViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val activeProfile by viewModel.activeProfile.collectAsStateWithLifecycle()
    val savedRecords by viewModel.savedRecords.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var autoCalibrate by remember { mutableStateOf(true) }
    var hapticFeedback by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp)
            .testTag("profile_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // User Profile Banner Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, ImperialGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(ImperialGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = OnImperialGold,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = activeProfile.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(ImperialGold.copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "${activeProfile.gender} · ${activeProfile.fateNayin}",
                                    fontSize = 9.sp,
                                    color = ImperialGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${activeProfile.yearGanzhi}年 ${activeProfile.monthGanzhi}月 ${activeProfile.dayGanzhi}日 ${activeProfile.hourGanzhi}时",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, null, tint = EtherealCyan, modifier = Modifier.size(12.dp))
                            Text(
                                text = " ${activeProfile.location}",
                                fontSize = 10.sp,
                                color = EtherealCyan
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .clickable { viewModel.navigateTo(Screen.GanzhiSetup) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "管理本命干支与经纬校偏",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                        Text(
                            text = "切换命盘 ↗",
                            style = MaterialTheme.typography.labelSmall,
                            color = ImperialGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // 易数卷宗 (Saved Divination Records from Room DB)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(14.dp)
                .testTag("divination_records_archive")
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Bookmark, null, tint = ImperialGold, modifier = Modifier.size(18.dp))
                        Text(
                            text = " 易数卷宗归档",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = "已载入 ${savedRecords.size} 卷",
                        style = MaterialTheme.typography.labelSmall,
                        color = EtherealCyan
                    )
                }

                if (savedRecords.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "卷宗尚空，可在解梦、摇签或梅花占卜时点击“存入卷宗”",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF99907C)
                        )
                    }
                } else {
                    savedRecords.forEach { record ->
                        val dateStr = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(Date(record.timestamp))
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
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(SurfaceContainerHighest)
                                                .padding(horizontal = 4.dp, vertical = 1.dp)
                                        ) {
                                            Text(
                                                text = when (record.type) {
                                                    "DREAM" -> "解梦"
                                                    "KING_WEN" -> "灵签"
                                                    "MEIHUA" -> "梅花"
                                                    else -> "八字"
                                                },
                                                fontSize = 8.sp,
                                                color = ImperialGoldFixedDim
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = record.title,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = record.summary,
                                        fontSize = 10.sp,
                                        color = Color(0xFFD0C5AF),
                                        maxLines = 1
                                    )
                                    Text(
                                        text = dateStr,
                                        fontSize = 8.sp,
                                        color = Color(0xFF99907C)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        viewModel.deleteRecord(record.id)
                                        Toast.makeText(context, "已自卷宗移除", Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.Delete, null, tint = Color(0xFF99907C), modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }

        // Metaphysical System Settings
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "⚙ 天机玄枢设置",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Setting 1: 磁偏角常驻校准
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("磁偏角常驻自动校准", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                        Text("消除地球磁极与真北时空偏差", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                    }
                    Switch(
                        checked = autoCalibrate,
                        onCheckedChange = { autoCalibrate = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF0F131D),
                            checkedTrackColor = ImperialGold
                        )
                    )
                }

                // Setting 2: 触感与玄音声效
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("触感振动反馈", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                        Text("摇签、转动罗盘时提供灵觉微反馈", fontSize = 9.sp, color = Color(0xFFD0C5AF))
                    }
                    Switch(
                        checked = hapticFeedback,
                        onCheckedChange = { hapticFeedback = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF0F131D),
                            checkedTrackColor = EtherealCyan
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(SurfaceContainerHighest)
                )

                // Info: Algorithm specifications
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("算法依据", style = MaterialTheme.typography.bodySmall, color = Color(0xFFD0C5AF))
                    Text("《渊海子平》·《三命通会》·《梅花易数》", style = MaterialTheme.typography.labelSmall, color = ImperialGold)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("系统版本", style = MaterialTheme.typography.bodySmall, color = Color(0xFFD0C5AF))
                    Text("Cyber-Taoism Matrix v4.2.0", style = MaterialTheme.typography.labelSmall, color = EtherealCyan)
                }
            }
        }
    }
}
