package com.example.sample02

import android.content.Context
import android.util.AttributeSet
import com.github.mikephil.charting.charts.RadarChart

/**
 * カスタムレーダーチャート
 */
class MarkSheetRadarChart : RadarChart {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
//        mXAxisRenderer = MarkSheetXAxisRadarChart(mViewPortHandler, mXAxis, this)
    }

    override fun init() {
        super.init()
        mXAxisRenderer = MarkSheetXAxisRadarChart(mViewPortHandler, mXAxis, this)
    }
}