package ru.kpfu.itis.spymasters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.spymasters.items.PlayerItem
import ru.kpfu.itis.spymasters.databinding.ItemPlayerBinding
import ru.kpfu.itis.spymasters.entities.Player

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
        notifyDataSetChanged()
    }

//    fun updateText(position: Int, newName: String) {
//        players[position] = Player(newName, false)
//        notifyItemChanged(position)
//    }

    fun getPlayers(): ArrayList<Player> {
        return players
    }

    fun setPlayers(player: Player){
        players.add(player)
    }

}