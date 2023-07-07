package ru.kpfu.itis.spymasters

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import ru.kpfu.itis.spymasters.databinding.FragmentTimerBinding

class TimerFragment : Fragment(R.layout.fragment_timer) {
    private var binding: FragmentTimerBinding? = null
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 0
    private lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimerBinding.bind(view)

        val timerMinutes = arguments?.getInt("TIMER_MINUTES") ?: 0
        remainingTimeInMillis = timerMinutes * 60 * 1000.toLong()

        if (savedInstanceState != null) {
            isTimerRunning = savedInstanceState.getBoolean(KEY_TIMER_RUNNING)
            remainingTimeInMillis = savedInstanceState.getLong(KEY_REMAINING_TIME)
        } else {
            isTimerRunning = true
        }

        if (isTimerRunning) startTimer(remainingTimeInMillis)
        else updateTimerUI(remainingTimeInMillis)

        binding?.ivPause?.setOnClickListener {
            if (isTimerRunning) pauseTimer()
            else resumeTimer()
        }
    }

    private fun pauseTimer() {
        timer.cancel()
        isTimerRunning = false
        updateTimerUI(remainingTimeInMillis)
    }

    private fun resumeTimer() {
        startTimer(remainingTimeInMillis)
    }

    private fun startTimer(timeInMillis: Long) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(p0: Long) {
                remainingTimeInMillis = p0
                updateTimerUI(remainingTimeInMillis)
            }

            override fun onFinish() {
                isTimerRunning = false
                updateTimerUI(0)
                TODO("Not yet implemented")
            }
        }.start()

        isTimerRunning = true
    }

    private fun updateTimerUI(timeInMillis: Long) {
        val minutes = (timeInMillis / 1000) / 60
        val seconds = (timeInMillis / 1000) % 60
        binding?.tvTime?.text = String.format("%02d:%02d", minutes, seconds)

        if (isTimerRunning) binding?.ivPause?.setImageResource(R.drawable.baseline_pause_circle_filled_24)
        else binding?.ivPause?.setImageResource(R.drawable.baseline_play_circle_24)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_TIMER_RUNNING, isTimerRunning)
        outState.putLong(KEY_REMAINING_TIME, remainingTimeInMillis)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val KEY_TIMER_RUNNING = "timerRunning"
        private const val KEY_REMAINING_TIME = "remainingTime"
    }
}