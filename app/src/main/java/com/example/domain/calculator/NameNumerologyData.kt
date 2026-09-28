package com.example.domain.calculator

object NameNumerologyData {
    data class NameResult(
        val name: String,
        val totalScore: Int,
        val tiange: Int,
        val renge: Int,
        val dige: Int,
        val waige: Int,
        val zongge: Int,
        val sancai: String,
        val judgment: String,
        val elementBalance: String
    )

    fun evaluate(name: String): NameResult {
        val clean = if (name.isBlank()) "天衍" else name
        val hash = clean.hashCode().let { if (it < 0) -it else it }
        val renge = (hash % 30) + 11
        val dige = ((hash / 3) % 30) + 12
        val tiange = ((hash / 7) % 20) + 5
        val zongge = tiange + renge + dige
        val waige = ((hash / 11) % 20) + 6
        val score = 85 + (hash % 14)

        return NameResult(
            name = clean,
            totalScore = score,
            tiange = tiange,
            renge = renge,
            dige = dige,
            waige = waige,
            zongge = zongge,
            sancai = "木火土 · 顺生大吉",
            judgment = "三才五格相生有情，基础稳固，境遇安泰。能得长上提拔而获成功，身心健朗，名利兼收。",
            elementBalance = "五行得生，火土双旺，辅以水润，大器晚成。"
        )
    }
}
