package ru.kpfu.itis.spymasters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kpfu.itis.spymasters.databinding.FragmentStartGameBinding

class StartGameFragment : Fragment(R.layout.fragment_start_game) {

    private var binding: FragmentStartGameBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartGameBinding.bind(view)

        val timer = arguments?.getInt("timer")
        val countSpy = arguments?.getInt("countSpy")
//        val players = arguments?.getSerializable("players")

        binding?.run {
            textView5.text = "Таймер: " + timer.toString()
            textView6.text = "Количество шпионов: " + countSpy.toString()
        }
    }
}