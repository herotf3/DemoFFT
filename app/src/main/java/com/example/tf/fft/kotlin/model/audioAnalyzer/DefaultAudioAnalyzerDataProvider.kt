
package com.example.tf.fft.kotlin.model.audioAnalyzer

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import com.example.tf.fft.kotlin.model.DataProviderBase
import java.util.concurrent.TimeUnit

class DefaultAudioAnalyzerDataProvider
(private val sampleRate: Int = 20000,

 private val minBufferSize: Int = 2048, // should be with power of 2 for correct work of FFT
 interval: Long = (sampleRate / minBufferSize).toLong()
): DataProviderBase<AudioData>(interval, TimeUnit.MILLISECONDS), IAudioAnalyzerDataProvider {

    private val audioRecord = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize * 2)

    private val audioData = AudioData(minBufferSize)

    private var time = 0L

    init {
        if (audioRecord.state != AudioRecord.STATE_INITIALIZED)
            throw UnsupportedOperationException("This device doesn't support AudioRecord")
    }

    override fun onStart() {
        super.onStart()

        audioRecord.startRecording()
    }

    override fun onStop() {
        super.onStop()

        audioRecord.stop()
    }

    override fun onNext(): AudioData {
        audioRecord.read(audioData.yData.itemsArray, 0, minBufferSize)

        val itemsArray = audioData.xData.itemsArray
        for (index in 0 until minBufferSize)
            itemsArray[index] = time++

        return audioData
    }

    override fun getBufferSize(): Int = minBufferSize

    override fun getSampleRate(): Int = sampleRate
}