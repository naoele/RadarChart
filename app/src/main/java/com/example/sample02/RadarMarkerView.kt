package com.example.sample02

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.DecimalFormat

/**
 * レーダーチャートのマーカーView
 */
@SuppressLint("ViewConstructor")
class RadarMarkerView constructor(
    context: Context,
    layoutResource: Int,
) : MarkerView(context, layoutResource) {

    private val tvContent: TextView = findViewById(R.id.tvContent)
    private val format = DecimalFormat("##0")

    init {
//        tvContent.setTypeface(Typeface.createFromAsset(context.assets, "OpenSans-Light.ttf"))
    }

    override fun refreshContent(e: Entry, highlight: Highlight) {
        tvContent.text = String.format("%s %%", format.format(e.y.toDouble()))
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height - 10).toFloat())
    }

}