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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.calculator.BaZiData
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.ImperialGoldFixed
import com.example.ui.theme.OnImperialGold
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceVoid
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TianYanViewModel

@Composable
fun BaZiCastingScreen(
    viewModel: TianYanViewModel,
    modifier: Modifier = Modifier
) {
    val profiles by viewModel.profiles.collectAsStateWithLifecycle()
    val activeProfile by viewModel.activeProfile.collectAsStateWithLifecycle()
    val setupYear by viewModel.setupYear.collectAsStateWithLifecycle()
    val setupMonth by viewModel.setupMonth.collectAsStateWithLifecycle()
    val setupDay by viewModel.setupDay.collectAsStateWithLifecycle()
    val setupHourIndex by viewModel.setupHourIndex.collectAsStateWithLifecycle()
    val setupGender by viewModel.setupGender.collectAsStateWithLifecycle()
    val setupIsLunar by viewModel.setupIsLunar.collectAsStateWithLifecycle()
    val setupTrueSolarTime by viewModel.setupTrueSolarTime.collectAsStateWithLifecycle()

    val hourName = BaZiData.EARTHLY_BRANCHES[setupHourIndex] + "时"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceVoid)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("bazi_casting_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateTo(Screen.Destiny) },
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

            Text(
                text = "Hexagram Divinati...",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "分享",
                        tint = MaterialTheme.colorScheme.onSurface
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
                        contentDescription = "个人",
                        tint = OnImperialGold,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Profile Selector Bar
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "☸ 命盘所属 · 角色切换",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFD0C5AF)
                )
                Text(
                    text = "● 已载入 ${profiles.size} 命盘",
                    style = MaterialTheme.typography.labelSmall,
                    color = EtherealCyan
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                profiles.forEach { p ->
                    val isSelected = (p.id == activeProfile.id)
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) SurfaceContainerHigh else SurfaceContainerLow)
                            .border(
                                1.dp,
                                if (isSelected) ImperialGold.copy(alpha = 0.7f) else Color(0xFF4D4635).copy(alpha = 0.4f),
                                RoundedCornerShape(14.dp)
                            )
                            .clickable { viewModel.selectProfile(p) }
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) ImperialGold.copy(alpha = 0.25f) else SurfaceContainerHighest),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (p.gender == "乾造") "乾" else "坤",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) ImperialGold else Color(0xFFD0C5AF)
                                )
                                if (isSelected) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .size(12.dp)
                                            .clip(CircleShape)
                                            .background(ImperialGold),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = OnImperialGold,
                                            modifier = Modifier.size(9.dp)
                                        )
                                    }
                                }
                            }
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Text(
                                    text = p.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${p.gender} · ${p.yearGanzhi}${p.fateNayin.take(1)}命",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFD0C5AF),
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Ganzhi Matrix Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "💬 时空干支起局",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "GANZHI MATRIX",
                        style = MaterialTheme.typography.labelSmall,
                        color = ImperialGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Solar / Lunar Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (!setupIsLunar) ImperialGold else Color.Transparent)
                            .clickable { viewModel.setSetupIsLunar(false) }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "☀ 公历（阳历）",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (!setupIsLunar) OnImperialGold else Color(0xFFD0C5AF)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (setupIsLunar) ImperialGold else Color.Transparent)
                            .clickable { viewModel.setSetupIsLunar(true) }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "☾ 农历（阴历）",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (setupIsLunar) OnImperialGold else Color(0xFFD0C5AF)
                        )
                    }
                }

                // Four Pillars Display Cards (年柱, 月柱, 日元, 时柱)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PillarCard("年柱 · 岁次", activeProfile.yearGanzhi, activeProfile.yearNayin, "$setupYear", ImperialGold, Modifier.weight(1f))
                    PillarCard("月柱 · 月建", activeProfile.monthGanzhi, activeProfile.monthNayin, "${setupMonth}月", EtherealCyan, Modifier.weight(1f))
                    PillarCard("日元 · 主命", activeProfile.dayGanzhi, activeProfile.dayNayin, "${setupDay}日", ImperialGoldFixed, Modifier.weight(1f), isDayMaster = true)
                    PillarCard("时柱 · 归宿", activeProfile.hourGanzhi, activeProfile.hourNayin, hourName, EtherealCyan, Modifier.weight(1f))
                }

                // Steppers Grid (Year, Month, Day, Hour)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StepperItem("公历年份", "$setupYear 年", onUp = { viewModel.updateSetupYear(1) }, onDown = { viewModel.updateSetupYear(-1) }, modifier = Modifier.weight(1f))
                    StepperItem("公历月份", "${setupMonth.toString().padStart(2, '0')} 月 (仲秋)", onUp = { viewModel.updateSetupMonth(1) }, onDown = { viewModel.updateSetupMonth(-1) }, modifier = Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StepperItem("日宿设定", "$setupDay 日 (初七)", onUp = { viewModel.updateSetupDay(1) }, onDown = { viewModel.updateSetupDay(-1) }, modifier = Modifier.weight(1f))
                    StepperItem("出生时辰", "$hourName · 早食", onUp = { viewModel.updateSetupHour(1) }, onDown = { viewModel.updateSetupHour(-1) }, modifier = Modifier.weight(1f), isGold = true)
                }

                // Time range info
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
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = null,
                                tint = EtherealCyan,
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = " 时辰区间：07:00 - 08:59",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "辰土 · 司命库位",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                }
            }
        }

        // 乾坤阴阳极性
        Column {
            Text(
                text = "☉ 乾坤阴阳极性",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFD0C5AF)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // 乾造
                val isQian = (setupGender == "乾造")
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isQian) SurfaceContainerHigh else SurfaceContainerLow)
                        .border(
                            1.dp,
                            if (isQian) ImperialGold.copy(alpha = 0.8f) else Color(0xFF4D4635).copy(alpha = 0.4f),
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { viewModel.setSetupGender("乾造") }
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(if (isQian) ImperialGold.copy(alpha = 0.2f) else SurfaceContainerHighest),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "☉",
                                fontSize = 24.sp,
                                color = if (isQian) ImperialGold else Color(0xFFD0C5AF)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "乾造",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isQian) ImperialGold else MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "男命 · 阳刚生运",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                }

                // 坤造
                val isKun = (setupGender == "坤造")
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isKun) SurfaceContainerHigh else SurfaceContainerLow)
                        .border(
                            1.dp,
                            if (isKun) EtherealCyan.copy(alpha = 0.8f) else Color(0xFF4D4635).copy(alpha = 0.4f),
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { viewModel.setSetupGender("坤造") }
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(if (isKun) EtherealCyan.copy(alpha = 0.2f) else SurfaceContainerHighest),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "⦸",
                                fontSize = 24.sp,
                                color = if (isKun) EtherealCyan else Color(0xFFD0C5AF)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "坤造",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isKun) EtherealCyan else MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "女命 · 阴柔承载",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                    }
                }
            }
        }

        // 真太阳时经纬校准
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceContainer.copy(alpha = 0.9f))
                .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.4f), RoundedCornerShape(14.dp))
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
                            imageVector = Icons.Default.Explore,
                            contentDescription = null,
                            tint = EtherealCyan,
                            modifier = Modifier.size(20.dp)
                        )
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text(
                                text = "真太阳时经纬校准",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "按出生地经度消除平太阳时误差",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFD0C5AF)
                            )
                        }
                    }

                    Switch(
                        checked = setupTrueSolarTime,
                        onCheckedChange = { viewModel.setSetupTrueSolarTime(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF0F131D),
                            checkedTrackColor = EtherealCyan
                        )
                    )
                }

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
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = ImperialGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = " 浙江省 · 杭州市 (西湖区)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "+00:32' 校偏",
                                style = MaterialTheme.typography.labelSmall,
                                color = EtherealCyan
                            )
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = EtherealCyan,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        // Action CTA Button
        Button(
            onClick = {
                viewModel.applyGanzhiPillars()
                viewModel.navigateTo(Screen.GanzhiDeduction)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(26.dp))
                .testTag("launch_deduction_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = ImperialGold,
                contentColor = OnImperialGold
            )
        ) {
            Text(
                text = "∞ 开启天衍排盘 · 洞察玄机 ↗",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Guarantee Footer
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "《渊海子平》三命通会算法 · 已加密命理罗盘验证",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF99907C),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
private fun PillarCard(
    title: String,
    ganzhi: String,
    nayin: String,
    dateTag: String,
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
            .padding(vertical = 10.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 9.sp, color = Color(0xFFD0C5AF), style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = ganzhi,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = nayin, fontSize = 9.sp, color = color.copy(alpha = 0.85f), style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = dateTag, fontSize = 9.sp, color = Color(0xFFD0C5AF))
        }
    }
}

@Composable
private fun StepperItem(
    title: String,
    value: String,
    onUp: () -> Unit,
    onDown: () -> Unit,
    modifier: Modifier = Modifier,
    isGold: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.35f), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = title, style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isGold) ImperialGold else MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }

            Column {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "增加",
                    tint = Color(0xFFD0C5AF),
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onUp() }
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "减少",
                    tint = Color(0xFFD0C5AF),
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onDown() }
                )
            }
        }
    }
}
