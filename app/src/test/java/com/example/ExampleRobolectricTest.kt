package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.domain.calculator.BaZiData
import com.example.domain.calculator.BoneWeightData
import com.example.domain.calculator.IChingData
import com.example.domain.calculator.KingWenData
import com.example.domain.calculator.MeiHuaCalculator
import com.example.domain.calculator.NameNumerologyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("天衍玄机", appName)
    }

    @Test
    fun `test BaZi ganzhi calculations`() {
        val ganzhi = BaZiData.calculateGanzhi(1996, 9, 19, 4)
        assertEquals(4, ganzhi.size)
        assertEquals("丙子", ganzhi[0])
    }

    @Test
    fun `test King Wen sign retrieval`() {
        val sign64 = KingWenData.getSign(64)
        assertNotNull(sign64)
        assertEquals("火水未济", sign64.hexagramName)
        assertEquals("大吉", sign64.grade)
    }

    @Test
    fun `test MeiHua plum blossom divination`() {
        val result = MeiHuaCalculator.calculate(5, 2, 17, 5)
        assertEquals(8, result.upperNum)
        assertEquals(5, result.lowerNum)
        assertEquals(5, result.movingYao)
        assertEquals("地风升", result.originalHexagram.name)
    }

    @Test
    fun `test Bone weight calculation`() {
        val bone = BoneWeightData.calculate(1996, 9, 19, 4)
        assertEquals("四两四钱", bone.weightStr)
    }

    @Test
    fun `test Name numerology`() {
        val nameResult = NameNumerologyData.evaluate("天衍")
        assertTrue(nameResult.totalScore > 0)
    }

    @Test
    fun `test IChing 64 hexagrams loaded`() {
        assertTrue(IChingData.HEXAGRAMS.isNotEmpty())
    }
}
