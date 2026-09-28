package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NatalProfile(
    val id: String,
    val name: String,
    val gender: String, // "乾造" or "坤造"
    val genderSub: String = if (gender == "乾造") "男命 · 阳刚生运" else "女命 · 阴柔承载",
    val birthYear: Int = 1996,
    val birthMonth: Int = 9,
    val birthDay: Int = 19,
    val birthHourIndex: Int = 4, // 0..11 for 子丑寅卯辰巳午未申酉戌亥
    val isLunar: Boolean = false,
    val yearGanzhi: String = "丙子",
    val monthGanzhi: String = "丁酉",
    val dayGanzhi: String = "戊寅",
    val hourGanzhi: String = "丙辰",
    val yearNayin: String = "润下水",
    val monthNayin: String = "山下火",
    val dayNayin: String = "城头土",
    val hourNayin: String = "沙中土",
    val fateNayin: String = "城头土命",
    val yearZodiac: String = "鼠",
    val monthZodiac: String = "鸡",
    val dayZodiac: String = "虎",
    val hourZodiac: String = "龙",
    val yearStage: String = "胎",
    val monthStage: String = "死",
    val dayStage: String = "长生",
    val hourStage: String = "冠带",
    val location: String = "浙江省 · 杭州市 (西湖区)",
    val solarOffsetMinutes: Int = 32
)

data class Hexagram(
    val number: Int,
    val name: String,
    val englishName: String,
    val upperTrigram: String,
    val lowerTrigram: String,
    // Bottom to top: 6 lines. true = Yang (solid), false = Yin (broken)
    val lines: List<Boolean>,
    val omen: String,
    val tag: String,
    val summary: String,
    val tuanCi: String,
    val xiangCi: String,
    val yaoLines: List<String>
)

data class DivinationSign(
    val number: Int,
    val title: String,
    val hexagramName: String,
    val grade: String,
    val hexagramStructure: String, // e.g. "火水未济"
    val verseLines: List<String>,
    val deduction: String,
    val careerGuidance: String,
    val wealthGuidance: String,
    val transactionGuidance: String
)

data class DreamItem(
    val id: String,
    val title: String,
    val category: String,
    val luckScore: String,
    val hexagramRef: String,
    val omenBadge: String,
    val quote: String,
    val ancientInterpretation: String,
    val psychologicalInterpretation: String,
    val waterRatio: Int,
    val metalRatio: Int,
    val fireRatio: Int,
    val woodRatio: Int,
    val earthRatio: Int,
    val auspiciousTime: String,
    val auspiciousDirection: String,
    val luckyAttire: String,
    val followUpQuestions: List<String>
)

data class MeiHuaResult(
    val upperNum: Int,
    val lowerNum: Int,
    val movingYao: Int,
    val upperFormula: String,
    val lowerFormula: String,
    val movingFormula: String,
    val originalHexagram: Hexagram,
    val mutualHexagram: Hexagram,
    val transformedHexagram: Hexagram,
    val tiGuaName: String,
    val tiElement: String,
    val tiDesc: String,
    val yongGuaName: String,
    val yongElement: String,
    val yongDesc: String,
    val relationVerdict: String,
    val judgment: String,
    val careerAdvice: String,
    val wealthAdvice: String,
    val harmonyAdvice: String
)

@Entity(tableName = "divination_records")
data class DivinationRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String, // "KING_WEN", "MEIHUA", "DREAM", "BAZI"
    val title: String,
    val hexagramOrOmen: String,
    val summary: String,
    val timestamp: Long = System.currentTimeMillis(),
    val notes: String = ""
)
