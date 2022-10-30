package com.example.sample02

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart
import com.github.mikephil.charting.utils.FSize
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlin.math.roundToInt

/**
 *
 */
class MarkSheetXAxisRadarChart(
    viewPortHandler: ViewPortHandler?,
    xAxis: XAxis?,
    chart: RadarChart?
) :
    XAxisRendererRadarChart(
        viewPortHandler,
        xAxis,
        chart
    ) {
    init {
//        val chart = mAxis
    }

    override fun drawLabel(
        c: Canvas?,
        formattedLabel: String?,
        x: Float,
        y: Float,
        anchor: MPPointF?,
        angleDegrees: Float
    ) {

        val line = formattedLabel?.split(",")?.toTypedArray()
        if (line.isNullOrEmpty() || line.size < 2) {
            super.drawLabel(c, formattedLabel, x, y, anchor, angleDegrees)
        } else {
            Utils.drawXAxisValue(c, line[0], x, y, mAxisLabelPaint, anchor, angleDegrees)
            val greenLabelPaint = Paint(mAxisLabelPaint).apply {
                color = Color.parseColor("#00AFAA")
            }
            val sizeX = x - mAxisLabelPaint.textSize
            val sizeY = y + mAxisLabelPaint.textSize + 24
            Utils.drawXAxisValue(
                c,
                line[1],
                sizeX,
                sizeY,
                greenLabelPaint,
                anchor,
                angleDegrees
            )
            Utils.drawXAxisValue(
                c,
                line[2],
//                x + mAxisLabelPaint.textSize + greenLabelPaint.textSize,
                x  + greenLabelPaint.textSize + 32,
                sizeY,
                mAxisLabelPaint,
                anchor,
                angleDegrees
            )
        }
    }

    override fun drawLabels(c: Canvas?, pos: Float, anchor: MPPointF?) {
        super.drawLabels(c, pos, anchor)
    }

    override fun computeSize() {

        // 最も横に長いラベル文字を設定
        var longest = mXAxis.longestLabel
        val line = longest.split(",").toTypedArray()
        if (line.size > 1) {
            longest = line[1] + line[2]
        }

        mAxisLabelPaint.typeface = mXAxis.typeface
        mAxisLabelPaint.textSize = mXAxis.textSize

        val labelSize = Utils.calcTextSize(mAxisLabelPaint, longest);
        val labelWidth = labelSize.width
        val labelHeight = Utils.calcTextHeight(mAxisLabelPaint, "Q").toFloat()
        val labelRotatedSize = Utils.getSizeOfRotatedRectangleByDegrees(
            labelWidth,
            labelHeight,
            mXAxis.labelRotationAngle
        )

        mXAxis.mLabelWidth = labelWidth.roundToInt()
        mXAxis.mLabelHeight = labelHeight.roundToInt()
        mXAxis.mLabelRotatedWidth = labelRotatedSize.width.roundToInt()
        mXAxis.mLabelRotatedHeight = labelRotatedSize.height.roundToInt()

        FSize.recycleInstance(labelRotatedSize);
        FSize.recycleInstance(labelSize);
    }
}