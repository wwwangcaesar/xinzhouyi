package com.example.domain.calculator

import com.example.data.model.DreamItem

object DreamData {
    val HOT_KEYWORDS = listOf("梦见金鲤", "梦见飞翔", "梦见黄金", "梦见水涨", "梦见故人", "梦见登高", "梦见大火")

    val FEATURED_DREAM = DreamItem(
        id = "golden_koi",
        title = "梦见金鲤跃龙门",
        category = "灵兽水族 · 功名财禄",
        luckScore = "气运加持 +92%",
        hexagramRef = "卦枢 · 乾象",
        omenBadge = "上上大吉 · 乾九五潜龙飞天象",
        quote = "“昨夜梦及深潭清澈，忽有金鳞赤尾大鲤破水凌空，逆瀑而上，化作赤金祥龙隐入苍穹雷云之中。”",
        ancientInterpretation = "“鲤跃龙门，身价百倍。凡梦此者，主科名鼎盛，仕宦超迁，商贾获暴利，大吉兆也。”\n\n此梦卦象为《乾》之上九“飞龙在天，利见大人”。水至清则灵光发越，鲤化为龙，预示长期积蓄的隐性势能已至蜕变临界点，阻滞皆开，关隘自破。",
        psychologicalInterpretation = "自性化蜕变（Self-Actualization）：\n清澈深水象征丰沛而澄明的无意识母体；逆瀑而上的金鲤，则是潜意识中被压抑却极具爆发力的生命力原型（Libido）。潜意识正向显意识释放突破当下限制的明确信号。\n\n-> 当下心智特征：目标高度清晰，自我价值认同急剧攀升。",
        waterRatio = 45,
        metalRatio = 35,
        fireRatio = 20,
        woodRatio = 0,
        earthRatio = 0,
        auspiciousTime = "明日辰时至巳时 (07:00-11:00) 宜赴约商谈、递交提案、立项决断。",
        auspiciousDirection = "正北 · 东南方 (水木相生之气)",
        luckyAttire = "青黛 · 暗金色 (配金属或灵石佩饰)",
        followUpQuestions = listOf(
            "梦里的水若是浑浊该当何解？",
            "如果鲤鱼半途落回深潭？",
            "针对我当前考研/创业的应期？"
        )
    )

    val DREAMS = listOf(
        FEATURED_DREAM,
        DreamItem(
            id = "flying",
            title = "梦见御风翱翔云霄",
            category = "天象羽族 · 灵识开悟",
            luckScore = "气运加持 +88%",
            hexagramRef = "卦枢 · 升象",
            omenBadge = "大吉 · 顺风扬帆象",
            quote = "“凭虚御风，俯瞰群峦叠翠，白云如锦缎舒展于足下，轻快无比。”",
            ancientInterpretation = "《断梦秘书》卷三：“身生羽翼，飞腾霄汉，大吉。主心智旷达，解脱羁绊，有超然拔俗之庆。”",
            psychologicalInterpretation = "个体渴望超越当下环境局限，释放创造力与抱负，代表心智解脱与精神升华。",
            waterRatio = 20,
            metalRatio = 30,
            fireRatio = 10,
            woodRatio = 40,
            earthRatio = 0,
            auspiciousTime = "巳时至午时 (09:00-13:00) 宜展开发散性创作与策略规划。",
            auspiciousDirection = "正东 · 震方",
            luckyAttire = "苍绿色 · 云白色",
            followUpQuestions = listOf(
                "若是飞着飞着突然下坠？",
                "飞到太高呼吸困难作何解释？",
                "此梦对事业跳槽有何启示？"
            )
        ),
        DreamItem(
            id = "gold_treasure",
            title = "梦见掘地得金玉满堂",
            category = "造化珍宝 · 财禄广进",
            luckScore = "气运加持 +85%",
            hexagramRef = "卦枢 · 大有",
            omenBadge = "吉 · 土生金旺象",
            quote = "“于旧宅庭院掘土三尺，见金光熠熠，得金锭玉璧数匣，温润光华。”",
            ancientInterpretation = "周公断曰：“得金玉者，家道将丰。金为纯阳重器，见之主利源通达，求谋有成。”",
            psychologicalInterpretation = "潜意识在提醒梦者关注并挖掘自身潜在技能与未开采的经验价值，自我确证感强化。",
            waterRatio = 10,
            metalRatio = 45,
            fireRatio = 5,
            woodRatio = 10,
            earthRatio = 30,
            auspiciousTime = "未时至申时 (13:00-17:00) 宜推进资产配置与合同结转。",
            auspiciousDirection = "西南 · 坤位",
            luckyAttire = "姜黄色 · 琥珀色",
            followUpQuestions = listOf(
                "若是挖出金子又被人抢走？",
                "金子颜色偏暗发黑是何意？",
                "近期有投资计划，此兆可投否？"
            )
        )
    )

    fun searchDream(query: String): DreamItem {
        val trimmed = query.trim()
        val found = DREAMS.find { it.title.contains(trimmed) || trimmed.contains(it.id) }
        if (found != null) return found

        // Generate dynamic dream oracle based on user input
        return DreamItem(
            id = "custom_" + System.currentTimeMillis(),
            title = if (trimmed.isNotBlank()) "梦见$trimmed" else "梦见祥瑞化象",
            category = "意象演变 · 玄微感应",
            luckScore = "气运加持 +86%",
            hexagramRef = "卦枢 · 泰象",
            omenBadge = "上吉 · 阴阳顺畅象",
            quote = "“梦境感念 $trimmed，气机流转于虚实之间，隐兆玄机。”",
            ancientInterpretation = "周公通幽演玄：“凡梦 $trimmed 者，心有所发，神有所游。阴阳互变，吉凶互倚。以正心修德为本，所谋之事终有吉兆回甘。”",
            psychologicalInterpretation = "荣格分析心理学：梦中“$trimmed”作为核心象征，映射出潜意识对近期生活节律与情志压力的自我平衡调节机制。",
            waterRatio = 30,
            metalRatio = 25,
            fireRatio = 25,
            woodRatio = 10,
            earthRatio = 10,
            auspiciousTime = "明日辰时至巳时 (07:00-11:00) 宜行正事。",
            auspiciousDirection = "东南方 · 巽方",
            luckyAttire = "素白 · 黛蓝",
            followUpQuestions = listOf(
                "梦中细节不清对吉凶有影响吗？",
                "如何通过起居化解负面余波？",
                "需要做卦象复验吗？"
            )
        )
    }
}
