package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.kpfu.itis.spymasters.databinding.FragmentCreateGameBinding

class CreateGameFragment : Fragment(R.layout.fragment_create_game) {

    private var binding: FragmentCreateGameBinding? = null
    private var adapter: PlayerAdapter? = null
    private var players = ArrayList<Player>()
    private var radioGroupTimerList = ArrayList<RadioButton>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateGameBinding.bind(view)

        BackgroundAnimator.animate(binding?.createGameLayout?.background as AnimationDrawable, 10,4000)

        var countSpy = 1
        var timer = 5
        initAdapter()
        binding?.run {

            //  сохранение новых имен
            players = adapter?.getPlayers()!!

            // генерация и переход
            createGame.setOnClickListener {
                val bundle = Bundle()
                randomSpy(countSpy)
                bundle.apply {
                    putInt("timer", timer)
                    putInt("countSpy", players.size)
                    putParcelableArrayList("players", ArrayList(players.toList()))
                }

                //action_createGameFragment_to_startGameFragment

                findNavController().navigate(R.id.action_createGameFragment_to_votingFragment, bundle)
                //findNavController().navigate(R.id.action_createGameFragment_to_startGameFragment, bundle)
            }

            addPlayer.setOnClickListener {
                addPlayer()
            }


            // выбор кол-ва шпионов
            radioButtonSpy1.setOnClickListener {
                countSpy = 1
            }
            radioButtonSpy2.setOnClickListener {
                countSpy = 2
            }
            radioButtonSpy3.setOnClickListener {
                countSpy = 3
            }


            // выбор таймера
            radioButton5.isChecked = true
            radioGroupTimerList.add(radioButton3)
            radioGroupTimerList.add(radioButton4)
            radioGroupTimerList.add(radioButton5)
            radioGroupTimerList.add(radioButton7)
            radioGroupTimerList.add(radioButton10)
            radioGroupTimerList.add(radioButton12)
            radioGroupTimerList.add(radioButton15)
            radioGroupTimerList.add(radioButton20)
            radioGroupTimerList.add(radioButton25)
            radioGroupTimerList.add(radioButton30)

            radioButton3.setOnClickListener {
                timer = 3
                onClickTimer(radioButton3)
            }
            radioButton4.setOnClickListener {
                timer = 4
                onClickTimer(radioButton4)
            }
            radioButton5.setOnClickListener {
                timer = 5
                onClickTimer(radioButton5)
            }
            radioButton7.setOnClickListener {
                timer = 7
                onClickTimer(radioButton7)
            }
            radioButton10.setOnClickListener {
                timer = 10
                onClickTimer(radioButton10)
            }
            radioButton12.setOnClickListener {
                timer = 12
                onClickTimer(radioButton12)
            }
            radioButton15.setOnClickListener {
                timer = 15
                onClickTimer(radioButton15)
            }
            radioButton20.setOnClickListener {
                timer = 20
                onClickTimer(radioButton20)
            }
            radioButton25.setOnClickListener {
                timer = 25
                onClickTimer(radioButton25)
            }
            radioButton30.setOnClickListener {
                timer = 30
                onClickTimer(radioButton30)
            }
        }
    }

    fun updateText(position: Int, newName: String) {
        // Сохраните newText в базе данных или в другом месте
        players[position].name = newName
    }

    private fun randomSpy(countSpy: Int){
        if (countSpy == 1){
            val random = (1..players.size).random()
            players[random - 1].isSpy = true
        }
        else if (countSpy == 2){
            val random1 = (1..players.size).random()
            players[random1 - 1].isSpy = true

            var random2 = 0
            while ((random2 != 0) or (random1 != random2)){
                random2 = (1..players.size).random()
            }

            players[random1].isSpy = true
            players[random2].isSpy = true
        }
        else if (countSpy == 3){
            val random1 = (1..players.size).random()
            players[random1 - 1].isSpy = true

            var random2 = 0
            while ((random2 == 0) or (random1 == random2)){
                random2 = (1..players.size).random()
            }

            var random3 = 0
            while ((random3 == 0) or (random3 == random2) or (random3 == random1)){
                random3 = (1..players.size).random()
            }

            players[random1 - 1].isSpy = true
            players[random2 - 1].isSpy = true
            players[random3 - 1].isSpy = true
        }
    }

    private fun onClickTimer(radioButton: RadioButton){
        check(true)
        for (item: RadioButton in radioGroupTimerList) {
            if (item.equals(radioButton)) {
                continue
            }
            item.isChecked = false
        }
    }

    private fun initAdapter(){
        addPlayers(players)
        adapter = PlayerAdapter(players = players)
        binding?.rvPlayers?.adapter = adapter
        binding?.rvPlayers?.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun addPlayers(players: ArrayList<Player>){
        players.add(Player("Игрок 1", false))
        players.add(Player("Игрок 2", false))
        players.add(Player("Игрок 3", false))
        adapter?.updateDataset()
    }

    private fun addPlayer(){
        val number = players.size + 1
        val name = "Игрок " + number.toString()
        players.add(Player(name, false))
        adapter?.updateDataset()
    }
}