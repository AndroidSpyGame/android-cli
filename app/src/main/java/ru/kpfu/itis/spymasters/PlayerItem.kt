package ru.kpfu.itis.spymasters

import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.spymasters.databinding.ItemPlayerBinding

class PlayerItem(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(player: Player){
        binding.run {
            textPlayer.text = player.name
        }
    }

}