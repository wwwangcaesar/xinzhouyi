package com.example.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Token
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.screens.destiny.BaZiCastingScreen
import com.example.ui.screens.destiny.BaZiDeductionScreen
import com.example.ui.screens.destiny.DestinyChartScreen
import com.example.ui.screens.iching.IChingScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.treasure.BoneWeightDialog
import com.example.ui.screens.treasure.DreamDetailScreen
import com.example.ui.screens.treasure.KingWenDivinationScreen
import com.example.ui.screens.treasure.MeiHuaScreen
import com.example.ui.screens.treasure.NameNumerologyDialog
import com.example.ui.screens.treasure.TreasureChestScreen
import com.example.ui.theme.AlchemicalJade
import com.example.ui.theme.EtherealCyan
import com.example.ui.theme.ImperialGold
import com.example.ui.theme.OnImperialGold
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.SurfaceVoid
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.TianYanViewModel

@Composable
fun MainScreen(viewModel: TianYanViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val showBoneDialog by viewModel.showBoneDialog.collectAsStateWithLifecycle()
    val boneResult by viewModel.boneResult.collectAsStateWithLifecycle()
    val showNameDialog by viewModel.showNameDialog.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // BackHandler for sub-screens
    BackHandler(enabled = currentScreen !is Screen.Destiny) {
        when (currentScreen) {
            is Screen.GanzhiSetup -> viewModel.navigateTo(Screen.Destiny)
            is Screen.GanzhiDeduction -> viewModel.navigateTo(Screen.Destiny)
            is Screen.KingWen -> viewModel.navigateTo(Screen.Treasure)
            is Screen.MeiHua -> viewModel.navigateTo(Screen.Treasure)
            is Screen.Dream -> viewModel.navigateTo(Screen.Treasure)
            is Screen.Treasure, is Screen.IChing, is Screen.Profile -> viewModel.navigateTo(Screen.Destiny)
            else -> viewModel.navigateTo(Screen.Destiny)
        }
    }

    val isSubScreen = currentScreen is Screen.GanzhiSetup ||
            currentScreen is Screen.GanzhiDeduction ||
            currentScreen is Screen.KingWen ||
            currentScreen is Screen.MeiHua ||
            currentScreen is Screen.Dream

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            if (!isSubScreen) {
                MainTopAppBar(
                    currentScreen = currentScreen,
                    onNotificationClick = {
                        Toast.makeText(context, "今日天象：惊蛰交节，气机升腾", Toast.LENGTH_SHORT).show()
                    },
                    onProfileClick = {
                        viewModel.navigateTo(Screen.Profile)
                    }
                )
            }
        },
        bottomBar = {
            if (!isSubScreen) {
                MainBottomNavigation(
                    currentScreen = currentScreen,
                    onNavigate = { viewModel.navigateTo(it) }
                )
            }
        },
        containerColor = SurfaceVoid
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentScreen, label = "ScreenTransition") { screen ->
                when (screen) {
                    is Screen.Destiny -> DestinyChartScreen(viewModel = viewModel)
                    is Screen.GanzhiSetup -> BaZiCastingScreen(viewModel = viewModel)
                    is Screen.GanzhiDeduction -> BaZiDeductionScreen(viewModel = viewModel)
                    is Screen.Treasure -> TreasureChestScreen(viewModel = viewModel)
                    is Screen.KingWen -> KingWenDivinationScreen(viewModel = viewModel)
                    is Screen.MeiHua -> MeiHuaScreen(viewModel = viewModel)
                    is Screen.Dream -> DreamDetailScreen(viewModel = viewModel)
                    is Screen.IChing -> IChingScreen(viewModel = viewModel)
                    is Screen.Profile -> ProfileScreen(viewModel = viewModel)
                }
            }
        }
    }

    // Four Celestial Pillars Dialogs
    if (showBoneDialog) {
        BoneWeightDialog(
            result = boneResult,
            onDismiss = { viewModel.toggleBoneDialog(false) }
        )
    }

    if (showNameDialog) {
        NameNumerologyDialog(
            initialName = "天衍",
            onDismiss = { viewModel.toggleNameDialog(false) }
        )
    }
}

@Composable
private fun MainTopAppBar(
    currentScreen: Screen,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val title = when (currentScreen) {
        is Screen.Destiny -> "Destiny Chart"
        is Screen.Treasure -> "Treasure Chest"
        is Screen.IChing -> "Iching Studies"
        is Screen.Profile -> "我的"
        else -> "Destiny Chart"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceVoid.copy(alpha = 0.95f))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("main_top_app_bar")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerHigh)
                        .border(1.dp, ImperialGold.copy(alpha = 0.3f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = "天衍罗盘",
                        tint = ImperialGold,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Serif
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(AlchemicalJade)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "甲辰年 丁卯月 惊蛰",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF),
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerLow)
                        .testTag("notifications_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "通知",
                        tint = Color(0xFFD0C5AF),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(ImperialGold)
                        .clickable { onProfileClick() }
                        .testTag("profile_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "个人信息",
                        tint = OnImperialGold,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MainBottomNavigation(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerLowest.copy(alpha = 0.95f))
            .border(1.dp, Color(0xFF4D4635).copy(alpha = 0.25f), RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .testTag("main_bottom_nav")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = Icons.Default.Explore,
                label = "命盘",
                isSelected = currentScreen is Screen.Destiny,
                testTag = "nav_destiny",
                onClick = { onNavigate(Screen.Destiny) }
            )

            NavItem(
                icon = Icons.Default.Token,
                label = "百宝箱",
                isSelected = currentScreen is Screen.Treasure,
                testTag = "nav_treasure",
                onClick = { onNavigate(Screen.Treasure) }
            )

            NavItem(
                icon = Icons.Default.MenuBook,
                label = "易经",
                isSelected = currentScreen is Screen.IChing,
                testTag = "nav_iching",
                onClick = { onNavigate(Screen.IChing) }
            )

            NavItem(
                icon = Icons.Default.ManageAccounts,
                label = "我的",
                isSelected = currentScreen is Screen.Profile,
                testTag = "nav_profile",
                onClick = { onNavigate(Screen.Profile) }
            )
        }
    }
}

@Composable
private fun NavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(horizontal = 14.dp, vertical = 4.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(if (isSelected) ImperialGold.copy(alpha = 0.18f) else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) ImperialGold else Color(0xFFD0C5AF),
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) ImperialGold else Color(0xFFD0C5AF),
            fontFamily = FontFamily.Serif
        )
    }
}
