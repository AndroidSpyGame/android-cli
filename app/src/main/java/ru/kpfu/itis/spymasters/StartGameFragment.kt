package ru.kpfu.itis.spymasters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.processNextEventInCurrentThread
import ru.kpfu.itis.spymasters.databinding.FragmentStartGameBinding

class StartGameFragment : Fragment(R.layout.fragment_start_game) {

    private var binding: FragmentStartGameBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartGameBinding.bind(view)

        val timer = arguments?.getInt("timer")
        val countSpy = arguments?.getInt("countSpy")
        val players: ArrayList<Player>? = arguments?.getParcelableArrayList<Player>("players")

        var bundle = Bundle()
        if (players != null){
            bundle.putParcelableArrayList("players", ArrayList(players.toList()))
        }

        binding?.run {
            if (players != null)
            for (item: Player in players){
                if (item.isSpy){
                    textView5.text = item.name
                    return
                }
            }
            textView6.text = players?.firstOrNull()?.name.toString()

            button.setOnClickListener {
                findNavController().navigate(R.id.action_startGameFragment_to_votingFragment, bundle)
            }
        }
    }
}