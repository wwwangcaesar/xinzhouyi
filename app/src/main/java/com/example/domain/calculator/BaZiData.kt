package com.example.domain.calculator

object BaZiData {
    val HEAVENLY_STEMS = listOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")
    val EARTHLY_BRANCHES = listOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")
    val ZODIACS = listOf("鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪")

    val NAYIN_TABLE = mapOf(
        "甲子" to "海中金", "乙丑" to "海中金", "丙寅" to "炉中火", "丁卯" to "炉中火",
        "戊辰" to "大林木", "己巳" to "大林木", "庚午" to "路旁土", "辛未" to "路旁土",
        "壬申" to "剑锋金", "癸酉" to "剑锋金", "甲戌" to "山头火", "乙亥" to "山头火",
        "丙子" to "涧下水", "丁丑" to "涧下水", "戊寅" to "城头土", "己卯" to "城头土",
        "庚辰" to "白蜡金", "辛巳" to "白蜡金", "壬午" to "杨柳木", "癸未" to "杨柳木",
        "甲申" to "泉中水", "乙酉" to "泉中水", "丙戌" to "屋上土", "丁亥" to "屋上土",
        "戊子" to "霹雳火", "己丑" to "霹雳火", "庚寅" to "松柏木", "辛卯" to "松柏木",
        "壬辰" to "长流水", "癸巳" to "长流水", "甲午" to "沙中金", "乙未" to "沙中金",
        "丙申" to "山下火", "丁酉" to "山下火", "戊戌" to "平地木", "己亥" to "平地木",
        "庚子" to "壁上土", "辛丑" to "壁上土", "壬寅" to "金箔金", "癸卯" to "金箔金",
        "甲辰" to "覆灯火", "乙巳" to "覆灯火", "丙午" to "天河水", "丁未" to "天河水",
        "戊申" to "大驿土", "己酉" to "大驿土", "庚戌" to "钗钏金", "辛亥" to "钗钏金",
        "壬子" to "桑柘木", "癸丑" to "桑柘木", "甲寅" to "大溪水", "乙卯" to "大溪水",
        "丙辰" to "沙中土", "丁巳" to "沙中土", "戊午" to "天上火", "己未" to "天上火",
        "庚申" to "石榴木", "辛酉" to "石榴木", "壬戌" to "大海水", "癸亥" to "大海水"
    )

    data class HourFortune(
        val branch: String,
        val timeRange: String,
        val deity: String,
        val level: String, // "大吉", "吉", "平", "凶"
        val subtitle: String,
        val desc: String,
        val isCurrent: Boolean = false
    )

    fun getDailyHours(): List<HourFortune> = listOf(
        HourFortune("子时", "23:00-01:00", "青龙吉神", "吉", "子水承运", "夜气宁静，灵思涌动，宜静思默诵。"),
        HourFortune("丑时", "01:00-03:00", "明堂伏位", "平", "湿土涵养", "幽光潜藏，蓄势待发，安眠固本。"),
        HourFortune("寅时", "03:00-05:00", "天刑防隙", "平", "甲木萌动", "阴阳交割之初，平心静气。"),
        HourFortune("卯时", "05:00-07:00", "玉堂值神", "平", "晨光熹微", "旭日初升，文思敏睿，静心研读。"),
        HourFortune("辰时", "07:00-09:00", "偏财吉时 · 司命", "大吉", "天乙贵人", "喜神汇聚正南，宜推进重磅决策、签约通络。", isCurrent = true),
        HourFortune("巳时", "09:00-11:00", "勾陈逢化", "吉", "金水相生", "得遇长辈提点，宜沟通请益、求谋进取。"),
        HourFortune("午时", "11:00-13:00", "青龙吉庆", "平", "正午阳盛", "正午阳亢，静养生津，慎躁定神。"),
        HourFortune("未时", "13:00-15:00", "明堂合局", "吉", "财位显露", "财气潜伏，宜梳理账目、商洽定策。"),
        HourFortune("申时", "15:00-17:00", "天德贵人", "吉", "金风送爽", "气场流通，百事顺遂，广结善缘。"),
        HourFortune("酉时", "17:00-19:00", "玄武潜幽", "平", "落霞归宿", "白昼事务落幕，整理归档。"),
        HourFortune("戌时", "19:00-21:00", "司命守恒", "吉", "华灯初上", "亲朋会晤，温馨交融，商谈佳期。"),
        HourFortune("亥时", "21:00-23:00", "天乙归源", "平", "亥水通达", "调息纳神，安抚身心，顺应天律。")
    )

    fun calculateGanzhi(year: Int, month: Int, day: Int, hourIndex: Int): List<String> {
        // Year stem/branch offset from 1984 (甲子)
        val yearOffset = (year - 4) % 60
        val yStem = HEAVENLY_STEMS[(yearOffset % 10 + 10) % 10]
        val yBranch = EARTHLY_BRANCHES[(yearOffset % 12 + 12) % 12]
        val yearGz = "$yStem$yBranch"

        // Month stem calculation from year stem
        val monthStemBase = when (yStem) {
            "甲", "己" -> 2 // 丙
            "乙", "庚" -> 4 // 戊
            "丙", "辛" -> 6 // 庚
            "丁", "壬" -> 8 // 壬
            else -> 0 // 甲
        }
        val mStem = HEAVENLY_STEMS[(monthStemBase + (month - 1)) % 10]
        val mBranch = EARTHLY_BRANCHES[(month + 1) % 12]
        val monthGz = "$mStem$mBranch"

        // Day stem calculation (approximate baseline anchored to 1996-09-19 = 戊寅)
        val baseDayVal = (year * 365 + month * 30 + day + 14) % 60
        val dStem = HEAVENLY_STEMS[(baseDayVal % 10 + 10) % 10]
        val dBranch = EARTHLY_BRANCHES[(baseDayVal % 12 + 12) % 12]
        val dayGz = "$dStem$dBranch"

        // Hour stem from day stem (Five Rat Formula 五鼠遁元)
        val hourStemBase = when (dStem) {
            "甲", "己" -> 0 // 甲
            "乙", "庚" -> 2 // 丙
            "丙", "辛" -> 4 // 戊
            "丁", "壬" -> 6 // 庚
            else -> 8 // 壬
        }
        val hStem = HEAVENLY_STEMS[(hourStemBase + hourIndex) % 10]
        val hBranch = EARTHLY_BRANCHES[hourIndex % 12]
        val hourGz = "$hStem$hBranch"

        return listOf(yearGz, monthGz, dayGz, hourGz)
    }
}
