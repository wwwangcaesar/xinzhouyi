package com.example.domain.calculator

import com.example.data.model.DivinationSign

object KingWenData {
    val SIGNS: List<DivinationSign> = listOf(
        DivinationSign(
            number = 64,
            title = "文王神签 · 第六十四签",
            hexagramName = "火水未济",
            grade = "大吉",
            hexagramStructure = "上上之格 · 火在水上",
            verseLines = listOf(
                "吉星高照显灵光",
                "枯木逢春再吐芳",
                "百事顺心皆遂意",
                "功名富贵福绵长"
            ),
            deduction = "易象演卦：火在水上，阴阳交泰，万物将荣。事虽初创，气象勃然。",
            careerGuidance = "迟疑暂歇无妨，逢春自有贵人引路破局，行事中正方可亨通。",
            wealthGuidance = "秋冬固守本源，初春见机大发，东南有利，宜稳健蓄财。",
            transactionGuidance = "互信为基，秉持直道，不为浮利所惑，终得双赢丰果。"
        ),
        DivinationSign(
            number = 1,
            title = "文王神签 · 第一签",
            hexagramName = "乾为天",
            grade = "上上大吉",
            hexagramStructure = "极品元亨 · 六龙御天",
            verseLines = listOf(
                "巍巍独步向云间",
                "玉殿千官第一班",
                "富贵荣华天付与",
                "福如东海寿如山"
            ),
            deduction = "易象演卦：纯阳刚健，自强不息。名利双收，所向披靡。",
            careerGuidance = "飞龙在天，利见大人。主位居显贵，大有作为。",
            wealthGuidance = "正财大旺，横财亦随。经营得宜，财帛盈库。",
            transactionGuidance = "合谋大成，契约光明，诚信待人自得吉庆。"
        ),
        DivinationSign(
            number = 11,
            title = "文王神签 · 第十一签",
            hexagramName = "地天泰",
            grade = "大吉",
            hexagramStructure = "三阳开泰 · 天地交融",
            verseLines = listOf(
                "万事由天莫强求",
                "顺应时机莫生愁",
                "春风吹动枯杨绿",
                "满船空载月明归"
            ),
            deduction = "易象演卦：小往大来，吉亨通泰。阴阳和谐，和气生财。",
            careerGuidance = "贵人多助，上下齐心。适宜开拓全新业务或跨界合作。",
            wealthGuidance = "财源滚滚，水到渠成。求财有得，积善之家庆有余。",
            transactionGuidance = "买卖顺畅，互利共赢。良朋益友鼎力支持。"
        ),
        DivinationSign(
            number = 15,
            title = "文王神签 · 第十五签",
            hexagramName = "地山谦",
            grade = "吉",
            hexagramStructure = "厚德载谦 · 高山仰止",
            verseLines = listOf(
                "谦谦君子德名芳",
                "行事端方步履长",
                "天道亏盈福厚善",
                "门前喜气自洋洋"
            ),
            deduction = "易象演卦：地中有山，内高外卑。以退为进，终获大吉。",
            careerGuidance = "不矜不伐，深孚众望。虽有微阻，终得化解。",
            wealthGuidance = "财不露白，稳中求进。细水长流，久富绵长。",
            transactionGuidance = "以诚待客，退一步海阔天空，得利久远。"
        ),
        DivinationSign(
            number = 46,
            title = "文王神签 · 第四十六签",
            hexagramName = "地风升",
            grade = "大吉",
            hexagramStructure = "地中生木 · 步步高升",
            verseLines = listOf(
                "木入深林渐长高",
                "春风吹拂显英豪",
                "前程远大无极限",
                "胜似灵芝出九霄"
            ),
            deduction = "易象演卦：积小高大，顺德应运。求谋上达，诸事亨通。",
            careerGuidance = "升迁有望，遇长辈提拔。立下远谋，必获殊荣。",
            wealthGuidance = "进财顺遂，谋略得当。东南大利，积聚有道。",
            transactionGuidance = "合作顺利，签订契约顺畅，必有丰饶回报。"
        ),
        DivinationSign(
            number = 63,
            title = "文王神签 · 第六十三签",
            hexagramName = "水火既济",
            grade = "吉 · 防初吉",
            hexagramStructure = "水火相济 · 功成备豫",
            verseLines = listOf(
                "船行江心水自平",
                "顺风扬帆向前程",
                "但防险滩潜暗石",
                "谨慎持身永利亨"
            ),
            deduction = "易象演卦：思患预防，功成慎始。事已齐备，守成为要。",
            careerGuidance = "大局已定，防患未然。谨防得意忘形导致疏忽。",
            wealthGuidance = "获利甚丰，及时落袋为安，不宜过于激进。",
            transactionGuidance = "签约宜细密谨慎，白纸黑字分明，免生后患。"
        )
    )

    fun getSign(number: Int): DivinationSign {
        return SIGNS.find { it.number == number } ?: SIGNS.first()
    }
}
