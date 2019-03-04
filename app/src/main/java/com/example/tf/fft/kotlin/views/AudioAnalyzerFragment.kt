package com.example.tf.fft.kotlin.views

import android.util.Log
import com.example.tf.fft.R
import com.example.tf.fft.databinding.AudioAnalyzerFragmentBinding
import com.example.tf.fft.kotlin.viewModels.audioAnalyzer.AudioAnalyzerViewModel
import com.example.tf.fft.kotlin.model.audioAnalyzer.DefaultAudioAnalyzerDataProvider
import com.example.tf.fft.kotlin.model.audioAnalyzer.IAudioAnalyzerDataProvider
import com.example.tf.fft.kotlin.model.audioAnalyzer.StubAudioAnalyzerDataProvider
import com.scichart.scishowcase.views.BindingFragmentBase

class AudioAnalyzerFragment : BindingFragmentBase<AudioAnalyzerFragmentBinding, AudioAnalyzerViewModel>() {

    override fun getLayoutId(): Int = R.layout.audio_analyzer_fragment

    override fun onCreateViewModel(): AudioAnalyzerViewModel = AudioAnalyzerViewModel(requireContext(), 10000, createDataProvider())

    private fun createDataProvider(): IAudioAnalyzerDataProvider {
        try {
            return DefaultAudioAnalyzerDataProvider()
        } catch (e: Exception) {
            Log.i("AudioAnalyzerFragment", "Initialization of DefaultAudioAnalyzerDataProvider failed. Using stub implementation instead", e)
            return StubAudioAnalyzerDataProvider()
        }
    }
}