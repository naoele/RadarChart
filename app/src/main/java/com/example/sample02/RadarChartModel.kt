package com.example.sample02

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlin.math.floor

/**
 * レーダーチャートモデル
 */
open class RadarChartModel {

    /**
     * チャートの各頂点のテキスト
     */
    var activityTexts =
        arrayOf("part1,67%,(4/6問)", "part2,67%,(4/6問)", "part3,67%,(4/6問)", "part4,67%,(4/6問)", "part5,67%,(4/6問)", "part6,67%,(4/6問)", "part7,67%,(4/6問)")
    var activityTextSize: Float = 22f
    var activityTextXOffset: Float = 0f
//    var activityTextYOffset: Float = 240f
    var activityTextYOffset: Float = 0f
    val valueFormatter: ValueFormatter by lazy {
        object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val t = activityTexts[value.toInt() % activityTexts.size]
                return t
            }
        }
    }

    /**
     * チャートの説明テキストon/off
     */
    var isEnabledDescription: Boolean = false
    var webLineWidth: Float = 1f
    var webColor: Int = Color.LTGRAY
    var webLineWidthInner: Float = 1f
    var webColorInner: Int = Color.LTGRAY

    /**
     * ラインの透明度
     */
    var webAlpha: Int = 100

    /**
     * チャートのアニメーション設定値
     */
    var animateDurationMillisX: Int = 1400
    var animateDurationMillisY: Int = 1400
    var animateEasing: Easing.EasingFunction = Easing.EaseInOutQuad

    /**
     * 点数
     */
    val scoreMax: Int = 100
    val scoreMin: Int = 0
    val scoreEntries = ArrayList<RadarEntry>()
    val scoreColor: Int = Color.rgb(92, 198, 194)
    val scoreFillColor: Int = Color.rgb(92, 198, 194)
    val scoreIsDrawFilled = true
    val scoreFillAlpha = 180
    val scoreLineWidth = 2f
    val scoreIsDrawHighlightCircleEnabled = true
    val scoreIsDrawHighlightIndicators = false
    val scoreLabel = "点数"
    val scoreDataSet: RadarDataSet by lazy { getScoreDataSet() }

    val dataSetList: ArrayList<IRadarDataSet> by lazy { getScoreDataSetList() }
    val data: RadarData by lazy { getRadarData() }

    @JvmName("getScoreDataSet1")
    fun getScoreDataSet(): RadarDataSet {
        val scoreData = RadarDataSet(scoreEntries, scoreLabel)
        scoreData.color = scoreColor
        scoreData.fillColor = scoreFillColor
        scoreData.setDrawFilled(scoreIsDrawFilled)
        scoreData.fillAlpha = scoreFillAlpha
        scoreData.lineWidth = scoreLineWidth
        scoreData.isDrawHighlightCircleEnabled = scoreIsDrawHighlightCircleEnabled
        scoreData.setDrawHighlightIndicators(scoreIsDrawHighlightIndicators)
        return scoreData
    }

    open fun getScoreDataSetList(): ArrayList<IRadarDataSet> {
        val list = ArrayList<IRadarDataSet>()
        list.add(scoreDataSet)
        return list
    }

    open fun getRadarData(): RadarData {
        val data = RadarData(dataSetList)
        data.setValueTextSize(16f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)
        return data
    }

    /**
     * コンストラクタ
     */
    constructor() {
        init(null, ArrayList<Float>())
    }

    constructor(activityTexts: Array<String>, scoreList: List<Float>) {
        init(activityTexts, scoreList)
    }

    /**
     * 初期化処理
     */
    private fun init(texts: Array<String>?, scoreList: List<Float>) {

        if (texts == null && scoreList.isEmpty()) {
            for (i in activityTexts.indices) {
                val score = floor((Math.random() * scoreMax).toFloat()) + scoreMin
                scoreEntries.add(RadarEntry(score))
            }
        } else {
            if (texts != null) {
                activityTexts = texts
            }
            scoreList.forEach { scoreEntries.add(RadarEntry(it)) }
        }
    }
}