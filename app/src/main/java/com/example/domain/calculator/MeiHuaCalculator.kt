package com.example.domain.calculator

import com.example.data.model.Hexagram
import com.example.data.model.MeiHuaResult

object MeiHuaCalculator {
    // 8 Trigram symbols mapped to numbers 1..8
    // 1: 乾, 2: 兑, 3: 离, 4: 震, 5: 巽, 6: 坎, 7: 艮, 8: 坤
    private val NUM_TO_TRIGRAM = mapOf(
        1 to "乾", 2 to "兑", 3 to "离", 4 to "震",
        5 to "巽", 6 to "坎", 7 to "艮", 8 to "坤"
    )

    fun calculate(
        yearNum: Int = 5,
        monthNum: Int = 2,
        dayNum: Int = 17,
        hourNum: Int = 5
    ): MeiHuaResult {
        val upperSum = yearNum + monthNum + dayNum
        val upperRem = if (upperSum % 8 == 0) 8 else upperSum % 8

        val lowerSum = upperSum + hourNum
        val lowerRem = if (lowerSum % 8 == 0) 8 else lowerSum % 8

        val movingYao = if (lowerSum % 6 == 0) 6 else lowerSum % 6

        val upperName = NUM_TO_TRIGRAM[upperRem] ?: "坤"
        val lowerName = NUM_TO_TRIGRAM[lowerRem] ?: "巽"

        val originalHex = IChingData.findHexagram(upperName, lowerName)

        // Calculate Mutual Hexagram (互卦): lines 2,3,4 become lower; lines 3,4,5 become upper
        val origLines = originalHex.lines
        val mutualLower = if (origLines.size >= 5) origLines.subList(1, 4) else listOf(true, false, false)
        val mutualUpper = if (origLines.size >= 5) origLines.subList(2, 5) else listOf(true, true, false)

        val mutualUpperName = findTrigramByLines(mutualUpper)
        val mutualLowerName = findTrigramByLines(mutualLower)
        val mutualHex = IChingData.findHexagram(mutualUpperName, mutualLowerName)

        // Calculate Transformed Hexagram (变卦): invert the moving Yao
        val transLines = origLines.toMutableList()
        val yaoIdx = (movingYao - 1).coerceIn(0, 5)
        transLines[yaoIdx] = !transLines[yaoIdx]

        val transLower = transLines.subList(0, 3)
        val transUpper = transLines.subList(3, 6)
        val transUpperName = findTrigramByLines(transUpper)
        val transLowerName = findTrigramByLines(transLower)
        val transHex = IChingData.findHexagram(transUpperName, transLowerName)

        // Body (体) vs Function (用): the trigram containing the moving Yao is Yong (客), the other is Ti (主)
        val isMovingInUpper = movingYao in 4..6
        val tiName = if (isMovingInUpper) lowerName else upperName
        val yongName = if (isMovingInUpper) upperName else lowerName

        val tiElement = IChingData.TRIGRAM_ELEMENT[tiName] ?: "土"
        val yongElement = IChingData.TRIGRAM_ELEMENT[yongName] ?: "木"

        val movingYaoText = when (movingYao) {
            1 -> "初爻"
            2 -> "二爻"
            3 -> "三爻"
            4 -> "四爻"
            5 -> "五爻"
            else -> "上爻"
        }

        return MeiHuaResult(
            upperNum = upperRem,
            lowerNum = lowerRem,
            movingYao = movingYao,
            upperFormula = "辰年($yearNum) + 农月($monthNum) + 农日($dayNum) = $upperSum  余 $upperRem · $upperName",
            lowerFormula = "总和($upperSum) + 辰时($hourNum) = $lowerSum  余 $lowerRem · $lowerName",
            movingFormula = "时空合数 $lowerSum ÷ 6 取余  动在$movingYaoText",
            originalHexagram = originalHex,
            mutualHexagram = mutualHex,
            transformedHexagram = transHex,
            tiGuaName = "$tiName$tiElement",
            tiElement = tiElement,
            tiDesc = "问事主体 · 宽厚载物 · 静待天机",
            yongGuaName = "$yongName$yongElement",
            yongElement = yongElement,
            yongDesc = "外部机变 · 用克体之势 · 后化比和",
            relationVerdict = "前阻后顺 · 比和大吉",
            judgment = "“升而不仅必升，升进顺行，积小以高大。目下虽有微风拂扰，守中谦逊，必乘东风化龙。”\n以巽顺之木遇坤厚之土，虽有破土之微劳，终得山高水阔之厚赐。",
            careerAdvice = "升卦现前，贵人居于长辈或西南方位。适宜稳扎稳打，五爻动处见契机，立夏至小满节点将迎来实质晋升或关键邀约。",
            wealthAdvice = "互见雷泽归妹，中期文书、合伙细则易生歧义，切勿轻信口头承诺。经由变卦地山谦化解，重实利轻虚名，中长线收益稳固。",
            harmonyAdvice = "风行地上，体贴微至。遇有争执宜主动以柔克刚，切莫好胜相斥。谦和守默，自得良缘默契。"
        )
    }

    private fun findTrigramByLines(lines: List<Boolean>): String {
        for ((name, tLines) in IChingData.TRIGRAM_LINES) {
            if (tLines == lines) return name
        }
        return "乾"
    }
}
