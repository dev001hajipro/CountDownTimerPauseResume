package com.dev001hajipro.countdowntimerpauseresume.ui.main

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var timerText = MutableLiveData("")
    var millisUntilFinishedForResume = 0L
    private lateinit var timer: CountDownTimer
    val df = SimpleDateFormat("mm:ss.SS", Locale.US)

    fun onStart() {
        timer = timer(10 * 1000L, 10L)
        timer.start()
    }

    fun onCancel() {
        timer.cancel()
    }

    fun onResume() {
        timer = timer(millisUntilFinishedForResume, 10L)
        timer.start()
    }

    fun onPause() {
        timer.cancel()
    }

    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                timerText.value = df.format(0L)
            }

            override fun onTick(millisUntilFinished: Long) {
                millisUntilFinishedForResume = millisUntilFinished
                timerText.value = df.format(millisUntilFinishedForResume)
            }
        }
    }
}
