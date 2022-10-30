package com.example.sample02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.MarkerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chartTotal = findViewById<MarkSheetRadarChart>(R.id.chartTotal)

        val chartData = RadarChartModel()

        val markerView: MarkerView =
            RadarMarkerView(applicationContext, R.layout.layout_radar_markerview)
        markerView.chartView = chartTotal
        chartTotal.apply {
            description.isEnabled = chartData.isEnabledDescription
            webLineWidth = chartData.webLineWidth
            webLineWidthInner = chartData.webLineWidthInner
            webColor = chartData.webColor
            webColorInner = chartData.webColorInner
            webAlpha = chartData.webAlpha
            data = chartData.data
            animateXY(
                chartData.animateDurationMillisX,
                chartData.animateDurationMillisY,
                chartData.animateEasing
            )
            xAxis.textSize = chartData.activityTextSize
            xAxis.xOffset = chartData.activityTextXOffset
            xAxis.yOffset = chartData.activityTextYOffset
            xAxis.valueFormatter = chartData.valueFormatter
            marker = markerView
        }
        chartTotal.invalidate()
    }
}