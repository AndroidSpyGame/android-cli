package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kpfu.itis.spymasters.databinding.FragmentVotingBinding

class VotingFragment : Fragment(R.layout.fragment_voting) {

    private var binding: FragmentVotingBinding? = null
    private var players: ArrayList<Player>? = null
    private var adapter: PlayerVotingAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVotingBinding.bind(view)

        //players = arguments?.getParcelableArrayList<Player>("players")
        players = createPlayerList()

        var recyclerView = binding?.recyclerViewVoting

        //var deleteBtn = binding?.deletePlayer

        recyclerView?.layoutManager = LinearLayoutManager(context)

        adapter = PlayerVotingAdapter(players!!)
        recyclerView?.adapter = adapter

        binding?.run {
            deletePlayer.setOnClickListener {
                val selectedPlayer = adapter?.getSelectedPlayer()

                if (selectedPlayer != null) {
                    adapter?.removePlayer(selectedPlayer)
                } else {

                }

            }
        }


//        var recyclerView = binding?.recyclerViewVoting
//
//        if (players != null){
//            adapter = PlayerVotingAdapter(players as java.util.ArrayList<Player>)
//        }
//
////        recyclerView?.layoutManager = LinearLayoutManager(this)
////        recyclerView?.adapter = adapter
//
//
//
////        recyclerView.layoutManager = LinearLayoutManager(this)
////        recyclerView.adapter =

        BackgroundAnimator.animate(binding?.votingLayout?.background as AnimationDrawable, 10,4000)


        binding.run {

        }
    }

    private fun createPlayerList(): ArrayList<Player> {
        val playerList = arrayListOf<Player>()
        playerList.add(Player("Игрок 1", false))
        playerList.add(Player("Игрок 2", false))
        playerList.add(Player("Игрок 3", false))
        playerList.add(Player("Игрок 4", false))
        return playerList
    }


}