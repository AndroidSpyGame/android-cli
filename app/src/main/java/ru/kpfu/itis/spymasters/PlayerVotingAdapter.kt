package ru.kpfu.itis.spymasters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class PlayerVotingAdapter(private val players: ArrayList<Player>) : RecyclerView.Adapter<PlayerVotingAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_voting, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val player = players[position]
//        holder.nameTextView.text = player.name
//        holder.radioButton.isChecked = player.isSelected
//
//        holder.radioButton.setOnClickListener {
//            // Обновляем состояние выбранного человека
//            player.isSelected = holder.radioButton.isChecked
//
//            // Обновляем список
//            notifyDataSetChanged()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return players.size
//    }
//
//    fun removeSelectedPerson() {
//        players.removeAll { it.isSelected }
//        notifyDataSetChanged()
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val nameTextView: TextView = itemView.findViewById(R.id.name_player_voting)
//        val radioButton: RadioButton = itemView.findViewById(R.id.radio_btn_player)
//    }
//}


class PlayerVotingAdapter(private val playerList: ArrayList<Player>) :
    RecyclerView.Adapter<PlayerVotingAdapter.PlayerViewHolder>() {
    private var selectedPlayer: Player? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.radioButton.text = player.name

        // Обработка выбора RadioButton
        holder.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                selectedPlayer = player
            }
        }
    }


    override fun getItemCount(): Int {
        return playerList.size
    }


    fun getSelectedPlayer(): Player? {
        return selectedPlayer
    }


    fun removePlayer(player: Player) {
        val position = playerList.indexOf(player)
        playerList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButton: RadioButton = itemView.findViewById(R.id.deletePlayer)
    }
}