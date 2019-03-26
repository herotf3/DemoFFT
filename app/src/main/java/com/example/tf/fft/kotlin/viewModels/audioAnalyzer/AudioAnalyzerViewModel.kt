
package com.example.tf.fft.kotlin.viewModels.audioAnalyzer

import android.content.Context
import com.example.tf.fft.algorithm.Radix2FFT
import com.scichart.core.model.DoubleValues
import com.example.tf.fft.kotlin.model.audioAnalyzer.IAudioAnalyzerDataProvider
import com.example.tf.fft.kotlin.viewModels.FragmentViewModelBase
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

class AudioAnalyzerViewModel(context: Context, maxFrequency: Int, private val dataProvider: IAudioAnalyzerDataProvider) : FragmentViewModelBase(context) {
    private val bufferSize = dataProvider.getBufferSize()
    private val sampleRate = dataProvider.getSampleRate()
    private val audioStreamBufferSize :Int = sampleRate*4

    private val fft = Radix2FFT(bufferSize)

    private val hzPerDataPoint = sampleRate.toDouble() / bufferSize
    private val fftSize = (maxFrequency / hzPerDataPoint).toInt()

    val audioStreamVM = AudioStreamViewModel(context, audioStreamBufferSize)
    val fftVM = FFTViewModel(context, fftSize, hzPerDataPoint)
    val spectrogramVM = SpectrogramViewModel(context, fftSize, audioStreamBufferSize / bufferSize)

    private val fftData = DoubleValues()

    override fun subscribe(lifecycleProvider: LifecycleProvider<*>) {
        super.subscribe(lifecycleProvider)

        dataProvider.getData().doOnNext {
            audioStreamVM.onNextAudioData(it)

            fft.run(it.yData, fftData)
            fftData.setSize(fftSize)

            fftVM.onNextFFT(fftData)

            spectrogramVM.onNextFFT(fftData)
        }.bindToLifecycle(lifecycleProvider).subscribe()
    }
}