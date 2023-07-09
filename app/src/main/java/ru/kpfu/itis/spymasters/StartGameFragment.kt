package ru.kpfu.itis.spymasters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.kpfu.itis.spymasters.databinding.FragmentStartGameBinding

@Suppress("DEPRECATION") // TODO: Требовал этой аннотации, так ли нужен метод getParcelableArrayList? Может его заменить на другой в 19 строке?
class StartGameFragment : Fragment(R.layout.fragment_start_game) {

    private var binding: FragmentStartGameBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartGameBinding.bind(view)
// TODO: Здесь есть закоментированный зачем-то код и неиспользуемый таймер и countSpy. Нужно что-то с этим сделать
// Unused:        val timer = arguments?.getInt("timer")
// Unused:        val countSpy = arguments?.getInt("countSpy")
        val players: ArrayList<Player>? = arguments?.getParcelableArrayList<Player>("players")

//        var bundle = Bundle()
//        if (players != null){
//            bundle.putParcelableArrayList("players", ArrayList(players.toList()))
//        }

        binding?.run {
            if (players != null) { // TODO: Здесь добавил скобки на 27 и 34 строке, так ли должно быть? Выдавал ошибку
                for (item: Player in players) {
                    if (item.isSpy) {
                        textView5.text = item.name
                        return
                    }
                }
            }

            textView6.text = players?.firstOrNull()?.name.toString()

            btnToVotingFragment.setOnClickListener {
//                findNavController().navigate(R.id.action_createGameFragment_to_votingFragment)
            }
        }
    }
}