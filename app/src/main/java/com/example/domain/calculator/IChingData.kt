package com.example.domain.calculator

import com.example.data.model.Hexagram

object IChingData {
    val TRIGRAMS = listOf("全部 (64)", "乾 (天)", "坤 (地)", "震 (雷)", "巽 (风)", "坎 (水)", "离 (火)", "艮 (山)", "兑 (泽)")

    // 8 basic trigram lines: bottom to top
    val TRIGRAM_LINES = mapOf(
        "乾" to listOf(true, true, true),
        "坤" to listOf(false, false, false),
        "震" to listOf(true, false, false),
        "巽" to listOf(false, true, true),
        "坎" to listOf(false, true, false),
        "离" to listOf(true, false, true),
        "艮" to listOf(false, false, true),
        "兑" to listOf(true, true, false)
    )

    val TRIGRAM_ELEMENT = mapOf(
        "乾" to "金",
        "坤" to "土",
        "震" to "木",
        "巽" to "木",
        "坎" to "水",
        "离" to "火",
        "艮" to "土",
        "兑" to "金"
    )

    val HEXAGRAMS: List<Hexagram> = listOf(
        Hexagram(
            number = 1,
            name = "乾为天",
            englishName = "The Creative",
            upperTrigram = "乾",
            lowerTrigram = "乾",
            lines = listOf(true, true, true, true, true, true),
            omen = "大吉",
            tag = "天行健 · 元亨利贞",
            summary = "天行健，君子以自强不息。刚健中正，万物资始。",
            tuanCi = "大哉乾元，万物资始，乃统天。云行雨施，品物流形。大明终始，六位时成，时乘六龙以御天。",
            xiangCi = "天行健，君子以自强不息。潜龙勿用，阳在下也。见龙在田，德施普也。",
            yaoLines = listOf(
                "初九：潜龙勿用。",
                "九二：见龙在田，利见大人。",
                "九三：君子终日乾乾，夕惕若厉，无咎。",
                "九四：或跃在渊，无咎。",
                "九五：飞龙在天，利见大人。",
                "上九：亢龙有悔。"
            )
        ),
        Hexagram(
            number = 2,
            name = "坤为地",
            englishName = "The Receptive",
            upperTrigram = "坤",
            lowerTrigram = "坤",
            lines = listOf(false, false, false, false, false, false),
            omen = "吉 · 承载",
            tag = "厚德载物 · 含弘光大",
            summary = "地势坤，君子以厚德载物。包容万象，柔顺中正。",
            tuanCi = "至哉坤元，万物资生，乃顺承天。坤厚载物，德合无疆。含弘光大，品物咸亨。",
            xiangCi = "地势坤，君子以厚德载物。履霜坚冰，阴始凝也。驯致其道，至坚冰也。",
            yaoLines = listOf(
                "初六：履霜，坚冰至。",
                "六二：直，方，大，不习无不利。",
                "六三：含章可贞。或从王事，无成有终。",
                "六四：括囊；无咎，无誉。",
                "六五：黄裳，元吉。",
                "上六：龙战于野，其血玄黄。"
            )
        ),
        Hexagram(
            number = 11,
            name = "地天泰",
            englishName = "Peace & Harmony",
            upperTrigram = "坤",
            lowerTrigram = "乾",
            lines = listOf(true, true, true, false, false, false),
            omen = "大吉 · 通达",
            tag = "天地交泰 · 通泰安康",
            summary = "天地交而万物通也，上下交而其志同也。小往大来，吉亨。",
            tuanCi = "泰，小往大来，吉亨。则是天地交，而万物通也；上下交，而其志同也。",
            xiangCi = "天地交，泰；后以财成天地之道，辅相天地之宜，以左右民。",
            yaoLines = listOf(
                "初九：拔茅茹，以其汇，征吉。",
                "九二：包荒，用冯河，不遐遗，朋亡，得尚于中行。",
                "九三：无平不陂，无往不复，艰贞无咎。勿恤其孚，于食有福。",
                "六四：翩翩，不富以其邻，不戒以孚。",
                "六五：帝乙归妹，以祉元吉。",
                "上六：城复于隍，勿用师。自邑告命，贞吝。"
            )
        ),
        Hexagram(
            number = 12,
            name = "天地否",
            englishName = "Standstill / Obstacle",
            upperTrigram = "乾",
            lowerTrigram = "坤",
            lines = listOf(false, false, false, true, true, true),
            omen = "潜伏 · 守正",
            tag = "闭塞求变 · 韬光养晦",
            summary = "天地不交而万物不通也，上下不交而天下无邦也。君子以俭德辟难，不可荣以禄。",
            tuanCi = "否之匪人，不利君子贞，大往小来。则是天地不交，而万物不通也。",
            xiangCi = "天地不交，否；君子以俭德辟难，不可荣以禄。",
            yaoLines = listOf(
                "初六：拔茅茹，以其汇，贞吉亨。",
                "六二：包承。小人吉，大人否亨。",
                "六三：包羞。",
                "九四：有命无咎，畴离祉。",
                "九五：休否，大人吉。其亡其亡，系于苞桑。",
                "上九：倾否，先否后喜。"
            )
        ),
        Hexagram(
            number = 15,
            name = "地山谦",
            englishName = "Modesty",
            upperTrigram = "坤",
            lowerTrigram = "艮",
            lines = listOf(false, false, true, false, false, false),
            omen = "终成 · 大猷",
            tag = "谦尊而光 · 卑而不可逾",
            summary = "谦尊而光，卑而不可逾，君子之终也。天道亏盈而益谦，地道变盈而流谦。",
            tuanCi = "谦，亨，天道下济而光明，地道卑而上行。天道亏盈而益谦，地道变盈而流谦，鬼神害盈而福谦，人道恶盈而好谦。",
            xiangCi = "地中有山，谦；君子以裒多益寡，称物平施。",
            yaoLines = listOf(
                "初六：谦谦君子，用涉大川，吉。",
                "六二：鸣谦，贞吉。",
                "九三：劳谦，君子有终，吉。",
                "六四：无不利，撝谦。",
                "六五：不富以其邻，利用侵伐，无不利。",
                "上六：鸣谦，利用行师，征邑国。"
            )
        ),
        Hexagram(
            number = 46,
            name = "地风升",
            englishName = "Pushing Upward",
            upperTrigram = "坤",
            lowerTrigram = "巽",
            lines = listOf(false, true, true, false, false, false),
            omen = "大吉",
            tag = "木入土中 · 积小高大",
            summary = "柔以时升，巽而顺，刚中而应，是以大亨。用见大人，勿恤；有庆也。",
            tuanCi = "升：元亨，用见大人，勿恤，南征吉。象曰：地中生木，升；君子以顺德，积小以高大。",
            xiangCi = "地中生木，升；君子以顺德，积小以高大。",
            yaoLines = listOf(
                "初六：允升，大吉。",
                "九二：孚乃利用禴，无咎。",
                "九三：升虚邑。",
                "六四：王用亨于岐山，吉无咎。",
                "六五：贞吉，升阶。",
                "上六：冥升，利于不息之贞。"
            )
        ),
        Hexagram(
            number = 54,
            name = "雷泽归妹",
            englishName = "The Marrying Maiden",
            upperTrigram = "震",
            lowerTrigram = "兑",
            lines = listOf(true, true, false, true, false, false),
            omen = "守正",
            tag = "情志暗动 · 守正则吉",
            summary = "归妹，天地之大义也。天地不交，而万物不兴。征凶，无攸利，柔乘刚也。",
            tuanCi = "归妹，征凶，无攸利。天地不交，而万物不兴，归妹人之终始也。",
            xiangCi = "泽上有雷，归妹；君子以永终知敝。",
            yaoLines = listOf(
                "初九：归妹以娣，跛能履，征吉。",
                "九二：眇能视，利幽人之贞。",
                "六三：归妹以须，反归以娣。",
                "九四：归妹愆期，迟归有时。",
                "六五：帝乙归妹，其君之袂，不如其娣之袂良，月几望，吉。",
                "上六：女承筐无实，士刲羊无血，无攸利。"
            )
        ),
        Hexagram(
            number = 63,
            name = "水火既济",
            englishName = "Completion",
            upperTrigram = "坎",
            lowerTrigram = "离",
            lines = listOf(true, false, true, false, true, false),
            omen = "亨 · 防初吉",
            tag = "功成已备 · 慎终如始",
            summary = "水在火上，既济。君子以思患而预防之。初吉终乱，慎始敬终。",
            tuanCi = "既济，亨小，利贞，初吉终乱。其道穷也。",
            xiangCi = "水在火上，既济；君子以思患而预防之。",
            yaoLines = listOf(
                "初九：曳其轮，濡其尾，无咎。",
                "六二：妇丧其茀，勿逐，七日得。",
                "九三：高宗伐鬼方，三年克之，小人勿用。",
                "六四：濡有衣袽，终日戒。",
                "九五：东邻杀牛，不如西邻之禴祭，实受其福。",
                "上六：濡其首，厉。"
            )
        ),
        Hexagram(
            number = 64,
            name = "火水未济",
            englishName = "Before Completion",
            upperTrigram = "离",
            lowerTrigram = "坎",
            lines = listOf(false, true, false, true, false, true),
            omen = "亨 · 创生",
            tag = "生生不息 · 周而复始",
            summary = "火在水上，未济。君子以慎辨物居方。事虽未成，孕育新生之无限机运。",
            tuanCi = "未济，亨，小狐汔济，濡其尾，无攸利。不续终也。虽不当位，刚柔应也。",
            xiangCi = "火在水上，未济；君子以慎辨物居方。",
            yaoLines = listOf(
                "初六：濡其尾，吝。",
                "九二：曳其轮，贞吉。",
                "六三：未济，征凶，利涉大川。",
                "九四：贞吉，悔亡，震用伐鬼方，三年有赏于大国。",
                "六五：贞吉，无悔，君子之光，有孚，吉。",
                "上九：有孚于饮酒，无咎，濡其首，有孚失是。"
            )
        ),
        Hexagram(
            number = 35,
            name = "火地晋",
            englishName = "Progress",
            upperTrigram = "离",
            lowerTrigram = "坤",
            lines = listOf(false, false, false, true, false, true),
            omen = "大吉 · 顺德",
            tag = "明出地上 · 顺理成章",
            summary = "火土相生，顺德而丽大明。今日利于开拓新局，宜在未时前定夺商约重事，顺应天心，自见天朗。",
            tuanCi = "晋，进也。明出地上，顺而丽乎大明，柔进而上行，是以康侯用锡马蕃庶，昼日三接也。",
            xiangCi = "明出地上，晋；君子以自昭明德。",
            yaoLines = listOf(
                "初六：晋如，摧如，贞吉。罔孚，裕无咎。",
                "六二：晋如，愁如，贞吉。受兹介福，于其王母。",
                "六三：众允，悔亡。",
                "九四：晋如鼫鼠，贞厉。",
                "六五：悔亡，失得勿恤，往吉，无不利。",
                "上九：晋其角，维用伐邑，厉吉无咎，贞吝。"
            )
        )
    )

    fun findHexagram(upper: String, lower: String): Hexagram {
        val match = HEXAGRAMS.find { it.upperTrigram == upper && it.lowerTrigram == lower }
        if (match != null) return match

        // Synthesize hexagram if not in short list
        val upperLines = TRIGRAM_LINES[upper] ?: listOf(true, true, true)
        val lowerLines = TRIGRAM_LINES[lower] ?: listOf(true, true, true)
        return Hexagram(
            number = ((upper.hashCode() + lower.hashCode()).coerceAtLeast(1) % 64) + 1,
            name = "$upper$lower 卦",
            englishName = "The Matrix Harmony",
            upperTrigram = upper,
            lowerTrigram = lower,
            lines = lowerLines + upperLines,
            omen = "吉",
            tag = "阴阳变通 · 玄机内敛",
            summary = "$upper 上 $lower 下，天地感应，动静合宜。",
            tuanCi = "天玄地黄，乾坤相易，刚柔互济，亨通吉顺。",
            xiangCi = "象曰：观天地之象，顺阴阳之理，君子修省自持。",
            yaoLines = listOf(
                "初爻：吉顺自来。",
                "二爻：守中得利。",
                "三爻：慎动免咎。",
                "四爻：乘势借风。",
                "五爻：大人助力。",
                "上爻：终归平淡。"
            )
        )
    }
}
