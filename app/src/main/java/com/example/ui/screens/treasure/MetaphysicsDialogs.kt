package com.example.ui.screens.treasure

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.domain.calculator.BoneWeightData
import com.example.domain.calculator.NameNumerologyData
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

@Composable
fun BoneWeightDialog(
    result: BoneWeightData.BoneResult,
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
                .testTag("bone_weight_dialog")
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⚖", fontSize = 18.sp, color = ImperialGold)
                        Text(
                            text = " 袁天罡称骨算命",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.Close, null, tint = Color(0xFFD0C5AF))
                    }
                }

                // Bone Weight Display
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "命中骨重",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFD0C5AF)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = result.weightStr,
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold,
                            color = ImperialGold,
                            fontFamily = FontFamily.Serif
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF003824).copy(alpha = 0.7f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = result.level,
                                style = MaterialTheme.typography.labelSmall,
                                color = AlchemicalJade
                            )
                        }
                    }
                }

                // Poem
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerLowest)
                        .border(1.dp, ImperialGold.copy(alpha = 0.25f), RoundedCornerShape(10.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = result.poem,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = ImperialGold,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily.Serif
                    )
                }

                // Explanation
                Text(
                    text = result.explanation,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD0C5AF),
                    lineHeight = 17.sp
                )

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ImperialGold,
                        contentColor = OnImperialGold
                    ),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Text("谨记批注 · 归元", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun NameNumerologyDialog(
    initialName: String = "天衍",
    onDismiss: () -> Unit
) {
    var nameInput by remember { mutableStateOf(initialName) }
    var currentResult by remember { mutableStateOf(NameNumerologyData.evaluate(initialName)) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(SurfaceContainer.copy(alpha = 0.98f))
                .border(1.dp, EtherealCyan.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                .padding(18.dp)
                .testTag("name_numerology_dialog")
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("◎", fontSize = 18.sp, color = EtherealCyan)
                        Text(
                            text = " 姓名数理吉凶评测",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.Close, null, tint = Color(0xFFD0C5AF))
                    }
                }

                // Name input and evaluate button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = {
                            nameInput = it
                            currentResult = NameNumerologyData.evaluate(it)
                        },
                        placeholder = { Text("输入测试姓名", fontSize = 11.sp, color = Color(0xFF99907C)) },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EtherealCyan,
                            unfocusedBorderColor = Color(0xFF4D4635),
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { currentResult = NameNumerologyData.evaluate(nameInput) },
                        modifier = Modifier.height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EtherealCyan,
                            contentColor = Color(0xFF003640)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("测算", fontWeight = FontWeight.Bold)
                    }
                }

                // Score Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("数理吉凶总评", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD0C5AF))
                            Text(currentResult.sancai, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = AlchemicalJade)
                        }

                        Text(
                            text = "${currentResult.totalScore} 分",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold,
                            color = ImperialGold
                        )
                    }
                }

                // Five Patterns (天格、人格、地格、外格、总格)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    PatternPill("天格", currentResult.tiange.toString(), Modifier.weight(1f))
                    PatternPill("人格", currentResult.renge.toString(), Modifier.weight(1f))
                    PatternPill("地格", currentResult.dige.toString(), Modifier.weight(1f))
                    PatternPill("外格", currentResult.waige.toString(), Modifier.weight(1f))
                    PatternPill("总格", currentResult.zongge.toString(), Modifier.weight(1f))
                }

                Text(
                    text = currentResult.judgment,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 17.sp
                )

                Text(
                    text = "● ${currentResult.elementBalance}",
                    style = MaterialTheme.typography.labelSmall,
                    color = EtherealCyan
                )
            }
        }
    }
}

@Composable
private fun PatternPill(
    label: String,
    value: String,
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
            Text(label, fontSize = 8.sp, color = Color(0xFFD0C5AF))
            Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ImperialGold)
        }
    }
}
