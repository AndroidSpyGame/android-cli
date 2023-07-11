package ru.kpfu.itis.spymasters.game_process_fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.tools.BackgroundAnimator
import ru.kpfu.itis.spymasters.entities.Player
import ru.kpfu.itis.spymasters.R
import ru.kpfu.itis.spymasters.databinding.FragmentTimerBinding

class TimerFragment : Fragment(R.layout.fragment_timer) {
    private var binding: FragmentTimerBinding? = null
    private var isTimerRunning = false
    private var remainingTimeInMillis: Long = 0
    private lateinit var timer: CountDownTimer
    private var playersFromBundle: ArrayList<Player>? = null
    private var timerFromBundle: Int? = null
    private var countSpyFromBundle: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimerBinding.bind(view)

        BackgroundAnimator.animate(binding?.timerFragmentLayout?.background as AnimationDrawable)

        @Suppress("DEPRECATION")
        playersFromBundle = arguments?.getParcelableArrayList("players")
        timerFromBundle = arguments?.getInt("timer")
        countSpyFromBundle = arguments?.getInt("countSpy")

        val timerMinutes = timerFromBundle ?: 0
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

        binding?.btnFinishGame?.setOnClickListener {
            finishTimer()
        }

        binding?.ivMenu?.setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_exitToMenuFragment)
        }

        binding?.ivHelp?.setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_rulesFragment)
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
                finishTimer()
            }
        }.start()

        isTimerRunning = true
    }

    private fun finishTimer() {
        isTimerRunning = false
        updateTimerUI(0)

        val bundle = Bundle()
        bundle.apply {
            if (timerFromBundle != null) putInt("timer", timerFromBundle!!)
            if (countSpyFromBundle != null) putInt("countSpy", countSpyFromBundle!!)
            if (playersFromBundle != null) putParcelableArrayList("players", playersFromBundle)
        }


        findNavController().navigate(R.id.action_timerFragment_to_votingFragment, bundle)
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