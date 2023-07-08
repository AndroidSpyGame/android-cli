package ru.kpfu.itis.spymasters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.spymasters.databinding.CreatorItemBinding

class CreatorsAdapter : RecyclerView.Adapter<CreatorsAdapter.CreatorHolder>() {

    val creatorsList = CreatorsRepository.creatorsList

    class CreatorHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CreatorItemBinding.bind(view)

        fun bind(creator: Creator) {
            binding.run {
                creatorNameText.text = String.format("Имя: %s", creator.name)
                creatorLastNameText.text = String.format("Фамилия: %s", creator.lastName)
                creatorTgUrlText.text = String.format("Telegram: %s", creator.telegram)
                creatorAvatarImage.setImageResource(creator.drawableAvatarID)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.creator_item, parent, false)
        return CreatorHolder(view)
    }

    override fun onBindViewHolder(holder: CreatorHolder, position: Int) {
        holder.bind(creatorsList[position])
    }

    override fun getItemCount(): Int = creatorsList.size

}