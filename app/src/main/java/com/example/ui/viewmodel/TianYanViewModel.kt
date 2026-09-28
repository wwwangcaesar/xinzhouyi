package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.TianYanDatabase
import com.example.data.model.DivinationRecord
import com.example.data.model.DivinationSign
import com.example.data.model.DreamItem
import com.example.data.model.Hexagram
import com.example.data.model.MeiHuaResult
import com.example.data.model.NatalProfile
import com.example.data.repository.TianYanRepository
import com.example.domain.calculator.BaZiData
import com.example.domain.calculator.BoneWeightData
import com.example.domain.calculator.DreamData
import com.example.domain.calculator.IChingData
import com.example.domain.calculator.KingWenData
import com.example.domain.calculator.MeiHuaCalculator
import com.example.domain.calculator.NameNumerologyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    object Destiny : Screen()
    object GanzhiSetup : Screen()
    object GanzhiDeduction : Screen()
    object Treasure : Screen()
    object KingWen : Screen()
    object MeiHua : Screen()
    data class Dream(val item: DreamItem) : Screen()
    object IChing : Screen()
    object Profile : Screen()
}

class TianYanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TianYanRepository = TianYanRepository(
        TianYanDatabase.getInstance(application).divinationDao()
    )

    val savedRecords: StateFlow<List<DivinationRecord>> = repository.allRecords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Destiny)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _profiles = MutableStateFlow(repository.getInitialProfiles())
    val profiles: StateFlow<List<NatalProfile>> = _profiles.asStateFlow()

    private val _activeProfile = MutableStateFlow(repository.getInitialProfiles().first())
    val activeProfile: StateFlow<NatalProfile> = _activeProfile.asStateFlow()

    // Ganzhi setup draft state
    private val _setupYear = MutableStateFlow(1996)
    val setupYear = _setupYear.asStateFlow()
    private val _setupMonth = MutableStateFlow(9)
    val setupMonth = _setupMonth.asStateFlow()
    private val _setupDay = MutableStateFlow(19)
    val setupDay = _setupDay.asStateFlow()
    private val _setupHourIndex = MutableStateFlow(4) // 辰时
    val setupHourIndex = _setupHourIndex.asStateFlow()
    private val _setupGender = MutableStateFlow("乾造")
    val setupGender = _setupGender.asStateFlow()
    private val _setupIsLunar = MutableStateFlow(false)
    val setupIsLunar = _setupIsLunar.asStateFlow()
    private val _setupTrueSolarTime = MutableStateFlow(true)
    val setupTrueSolarTime = _setupTrueSolarTime.asStateFlow()

    // King Wen Divination state
    private val _kingWenStep = MutableStateFlow(1) // 1: 默念, 2: 摇筒, 3: 掷筊, 4: 解读
    val kingWenStep = _kingWenStep.asStateFlow()
    private val _kingWenCategory = MutableStateFlow("事业前程")
    val kingWenCategory = _kingWenCategory.asStateFlow()
    private val _currentSign = MutableStateFlow(KingWenData.getSign(64))
    val currentSign = _currentSign.asStateFlow()

    // MeiHua State
    private val _meiHuaMode = MutableStateFlow(0) // 0: 时数局, 1: 报数起卦, 2: 物象触动
    val meiHuaMode = _meiHuaMode.asStateFlow()
    private val _meiHuaResult = MutableStateFlow(MeiHuaCalculator.calculate())
    val meiHuaResult = _meiHuaResult.asStateFlow()

    // Dream State
    private val _dreamQuery = MutableStateFlow("")
    val dreamQuery = _dreamQuery.asStateFlow()
    private val _selectedDream = MutableStateFlow(DreamData.FEATURED_DREAM)
    val selectedDream = _selectedDream.asStateFlow()

    // I-Ching State
    private val _ichingFilter = MutableStateFlow("全部 (64)")
    val ichingFilter = _ichingFilter.asStateFlow()
    private val _ichingQuery = MutableStateFlow("")
    val ichingQuery = _ichingQuery.asStateFlow()
    private val _selectedHexagram = MutableStateFlow<Hexagram?>(null)
    val selectedHexagram = _selectedHexagram.asStateFlow()

    // Active tool dialogs in Treasure
    private val _showBoneDialog = MutableStateFlow(false)
    val showBoneDialog = _showBoneDialog.asStateFlow()
    private val _boneResult = MutableStateFlow(BoneWeightData.calculate())
    val boneResult = _boneResult.asStateFlow()

    private val _showNameDialog = MutableStateFlow(false)
    val showNameDialog = _showNameDialog.asStateFlow()
    private val _nameResult = MutableStateFlow(NameNumerologyData.evaluate("天衍"))
    val nameResult = _nameResult.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun selectProfile(profile: NatalProfile) {
        _activeProfile.value = profile
        _setupYear.value = profile.birthYear
        _setupMonth.value = profile.birthMonth
        _setupDay.value = profile.birthDay
        _setupHourIndex.value = profile.birthHourIndex
        _setupGender.value = profile.gender
    }

    fun updateSetupYear(delta: Int) {
        _setupYear.value = (_setupYear.value + delta).coerceIn(1920, 2040)
    }

    fun updateSetupMonth(delta: Int) {
        val next = (_setupMonth.value + delta)
        _setupMonth.value = when {
            next > 12 -> 1
            next < 1 -> 12
            else -> next
        }
    }

    fun updateSetupDay(delta: Int) {
        val next = (_setupDay.value + delta)
        _setupDay.value = when {
            next > 31 -> 1
            next < 1 -> 31
            else -> next
        }
    }

    fun updateSetupHour(delta: Int) {
        val next = (_setupHourIndex.value + delta)
        _setupHourIndex.value = when {
            next > 11 -> 0
            next < 0 -> 11
            else -> next
        }
    }

    fun setSetupGender(gender: String) {
        _setupGender.value = gender
    }

    fun setSetupIsLunar(isLunar: Boolean) {
        _setupIsLunar.value = isLunar
    }

    fun setSetupTrueSolarTime(enabled: Boolean) {
        _setupTrueSolarTime.value = enabled
    }

    fun applyGanzhiPillars(): NatalProfile {
        val ganzhi = BaZiData.calculateGanzhi(
            year = _setupYear.value,
            month = _setupMonth.value,
            day = _setupDay.value,
            hourIndex = _setupHourIndex.value
        )
        val yGz = ganzhi[0]
        val mGz = ganzhi[1]
        val dGz = ganzhi[2]
        val hGz = ganzhi[3]

        val updated = _activeProfile.value.copy(
            gender = _setupGender.value,
            birthYear = _setupYear.value,
            birthMonth = _setupMonth.value,
            birthDay = _setupDay.value,
            birthHourIndex = _setupHourIndex.value,
            yearGanzhi = yGz,
            monthGanzhi = mGz,
            dayGanzhi = dGz,
            hourGanzhi = hGz,
            yearNayin = BaZiData.NAYIN_TABLE[yGz] ?: "润下水",
            monthNayin = BaZiData.NAYIN_TABLE[mGz] ?: "山下火",
            dayNayin = BaZiData.NAYIN_TABLE[dGz] ?: "城头土",
            hourNayin = BaZiData.NAYIN_TABLE[hGz] ?: "沙中土",
            fateNayin = BaZiData.NAYIN_TABLE[yGz]?.let { "${it}命" } ?: "城头土命"
        )
        _activeProfile.value = updated
        return updated
    }

    // King Wen
    fun setKingWenCategory(category: String) {
        _kingWenCategory.value = category
    }

    fun nextKingWenStep() {
        if (_kingWenStep.value < 4) {
            _kingWenStep.value += 1
        }
    }

    fun resetKingWen() {
        _kingWenStep.value = 1
        val randomSignNum = listOf(1, 11, 15, 46, 63, 64).random()
        _currentSign.value = KingWenData.getSign(randomSignNum)
    }

    fun setKingWenSign(sign: DivinationSign) {
        _currentSign.value = sign
    }

    // MeiHua
    fun setMeiHuaMode(mode: Int) {
        _meiHuaMode.value = mode
    }

    fun recalculateMeiHua(y: Int = 5, m: Int = 2, d: Int = 17, h: Int = 5) {
        _meiHuaResult.value = MeiHuaCalculator.calculate(y, m, d, h)
    }

    // Dreams
    fun setDreamQuery(q: String) {
        _dreamQuery.value = q
    }

    fun searchDream(query: String) {
        val result = DreamData.searchDream(query)
        _selectedDream.value = result
        navigateTo(Screen.Dream(result))
    }

    fun openDream(dream: DreamItem) {
        _selectedDream.value = dream
        navigateTo(Screen.Dream(dream))
    }

    // I-Ching
    fun setIChingFilter(filter: String) {
        _ichingFilter.value = filter
    }

    fun setIChingQuery(query: String) {
        _ichingQuery.value = query
    }

    fun selectHexagram(h: Hexagram?) {
        _selectedHexagram.value = h
    }

    // Dialogs
    fun toggleBoneDialog(show: Boolean) {
        _showBoneDialog.value = show
        if (show) {
            _boneResult.value = BoneWeightData.calculate(
                _setupYear.value, _setupMonth.value, _setupDay.value, _setupHourIndex.value
            )
        }
    }

    fun toggleNameDialog(show: Boolean, name: String = "天衍") {
        _showNameDialog.value = show
        if (show) {
            _nameResult.value = NameNumerologyData.evaluate(name)
        }
    }

    // Save record to DB
    fun saveDivinationRecord(type: String, title: String, hexOrOmen: String, summary: String) {
        viewModelScope.launch {
            repository.saveRecord(
                DivinationRecord(
                    type = type,
                    title = title,
                    hexagramOrOmen = hexOrOmen,
                    summary = summary
                )
            )
        }
    }

    fun deleteRecord(id: Long) {
        viewModelScope.launch {
            repository.deleteRecord(id)
        }
    }
}
