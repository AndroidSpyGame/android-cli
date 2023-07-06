package ru.kpfu.itis.spymasters

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
//    private var timer: Int = 5

    private var radioGroupTimerList = ArrayList<RadioButton>()
//    private lateinit var communicator: Communicator


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateGameBinding.bind(view)
        val bundle = Bundle()


        var countSpy = 1
        var timer = 5
//        var playersBool = randomPlayerSpy(countSpy)
        initAdapter()
        binding?.run {
            createGame.setOnClickListener {
                bundle.putInt("timer", timer)
                bundle.putInt("countSpy", countSpy)
//                bundle.putBooleanArray("playersBool")
//                bundle.putSerializable("players", players)
                findNavController().navigate(R.id.action_createGameFragment_to_startGameFragment, bundle)
            }

            addPlayer.setOnClickListener {
                addPlayer()
            }

            radioButtonSpy1.setOnClickListener {
                countSpy = 1
            }
            radioButtonSpy2.setOnClickListener {
                countSpy = 2
            }
            radioButtonSpy3.setOnClickListener {
                countSpy = 3
            }


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

//    private fun randomPlayerSpy(countSpy: Int): ArrayList<Boolean>{
//        var list = ArrayList<Int>()
//        for (i in 1..countSpy){
//            var random = (1..players.size).random()
//            while(list.contains(random)){
//                random = (1..players.size).random()
//            }
//            players[random - 1].isSpy = true
//            list.add(random)
//        }
//
//
//        for (i in 0..countSpy - 1){
//            bools[i] = players[i].isSpy
//        }
//        return bools
//    }


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
        players.add(Player("Игрок 3", true))
        adapter?.updateDataset()
    }


    private fun addPlayer(){
        var number = players.size + 1
        var name = "Игрок " + number.toString()
        players.add(Player(name, false))
        adapter?.updateDataset()
    }
}