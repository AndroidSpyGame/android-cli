package ru.kpfu.itis.spymasters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.spymasters.databinding.ItemPlayerBinding

class PlayerAdapter (private var players: ArrayList<Player>) : RecyclerView.Adapter<PlayerItem>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerItem = PlayerItem(
        binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PlayerItem, position: Int) {
        holder.onBind(players[position])
    }

    override fun getItemCount(): Int {
        return players.size
    }

    fun updateDataset() {
//        addPlayer()
        notifyDataSetChanged()
    }

    fun addPlayer(){
        var number = players.size + 1
        var name = "Игрок " + number.toString()
        players.add(Player(name, false))
    }

}