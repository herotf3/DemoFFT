
package com.example.tf.fft.kotlin.viewModels.audioAnalyzer

import android.content.Context
import com.example.tf.fft.kotlin.utils.dip
import com.scichart.charting.visuals.axes.AutoRange
import com.scichart.charting.visuals.axes.NumericAxis
import com.scichart.charting.visuals.renderableSeries.FastLineRenderableSeries
import com.scichart.data.model.DoubleRange
import com.scichart.drawing.common.SolidPenStyle
import com.example.tf.fft.kotlin.model.audioAnalyzer.AudioData
import com.example.tf.fft.kotlin.utils.XyDataSeries
import com.example.tf.fft.kotlin.viewModels.ChartViewModel
import com.scichart.drawing.utility.ColorUtil.*

class AudioStreamViewModel(context: Context, audioStreamBufferSize: Int) : ChartViewModel(context) {
    private val audioDS = XyDataSeries<Long, Short>().apply { fifoCapacity = audioStreamBufferSize }

    init {
        xAxes.add(NumericAxis(context).apply {
            autoRange = AutoRange.Always
            drawLabels = false
            drawMinorTicks = false
            drawMajorTicks = false
            drawMajorBands = false
            drawMinorGridLines = false
            drawMajorGridLines = false
        })

        yAxes.add(NumericAxis(context).apply {
            visibleRange = DoubleRange(Short.MIN_VALUE.toDouble(), Short.MAX_VALUE.toDouble())
            drawLabels = false
            drawMinorTicks = false
            drawMajorTicks = false
            drawMajorBands = false
            drawMinorGridLines = false
            drawMajorGridLines = false
        })

        val lineThickness = context.dip(1f)

        renderableSeries.add(FastLineRenderableSeries().apply {
            dataSeries = audioDS
            strokeStyle = SolidPenStyle(Grey, true, lineThickness, null)
        })
    }

    fun onNextAudioData(audioData: AudioData) {
        audioDS.append(audioData.xData, audioData.yData)
    }
}