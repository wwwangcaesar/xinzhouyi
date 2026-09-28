package com.example.data.repository

import com.example.data.local.DivinationDao
import com.example.data.model.DivinationRecord
import com.example.data.model.NatalProfile
import kotlinx.coroutines.flow.Flow

class TianYanRepository(private val dao: DivinationDao) {

    val allRecords: Flow<List<DivinationRecord>> = dao.getAllRecords()

    suspend fun saveRecord(record: DivinationRecord): Long {
        return dao.insertRecord(record)
    }

    suspend fun deleteRecord(id: Long) {
        dao.deleteRecordById(id)
    }

    fun getInitialProfiles(): List<NatalProfile> = listOf(
        NatalProfile(
            id = "self",
            name = "本人",
            gender = "乾造",
            genderSub = "男命 · 阳刚生运",
            birthYear = 1996,
            birthMonth = 9,
            birthDay = 19,
            birthHourIndex = 4, // 辰时
            yearGanzhi = "丙子",
            monthGanzhi = "丁酉",
            dayGanzhi = "戊寅",
            hourGanzhi = "丙辰",
            yearNayin = "润下水",
            monthNayin = "山下火",
            dayNayin = "城头土",
            hourNayin = "沙中土",
            fateNayin = "炉中火命",
            yearZodiac = "鼠",
            monthZodiac = "鸡",
            dayZodiac = "虎",
            hourZodiac = "龙",
            yearStage = "胎",
            monthStage = "死",
            dayStage = "长生",
            hourStage = "冠带",
            location = "浙江省 · 杭州市 (西湖区)",
            solarOffsetMinutes = 32
        ),
        NatalProfile(
            id = "mother",
            name = "母亲",
            gender = "坤造",
            genderSub = "女命 · 阴柔承载",
            birthYear = 1968,
            birthMonth = 7,
            birthDay = 15,
            birthHourIndex = 3, // 卯时
            yearGanzhi = "戊申",
            monthGanzhi = "己未",
            dayGanzhi = "癸丑",
            hourGanzhi = "乙卯",
            yearNayin = "大驿土",
            monthNayin = "天上火",
            dayNayin = "桑柘木",
            hourNayin = "大溪水",
            fateNayin = "戊申土命",
            yearZodiac = "猴",
            monthZodiac = "羊",
            dayZodiac = "牛",
            hourZodiac = "兔",
            yearStage = "长生",
            monthStage = "养",
            dayStage = "衰",
            hourStage = "长生",
            location = "浙江省 · 杭州市 (西湖区)",
            solarOffsetMinutes = 32
        )
    )
}
