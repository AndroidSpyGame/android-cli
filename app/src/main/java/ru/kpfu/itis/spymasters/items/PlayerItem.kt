package ru.kpfu.itis.spymasters.items

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.spymasters.databinding.ItemPlayerBinding
import ru.kpfu.itis.spymasters.entities.Player

class PlayerItem(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(player: Player){
        binding.run {
            namePlayer.setText(player.name)


            namePlayer.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Ничего не делаем
                    //player.name = s.toString()
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Обновляем текст в модели данных
                    player.name = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    // Ничего не делаем
                    //player.name = s.toString()
                }
            })

        }
    }

}