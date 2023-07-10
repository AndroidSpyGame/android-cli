package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import ru.kpfu.itis.spymasters.databinding.FragmentVotingBinding

@Suppress("DEPRECATION") // TODO: Требовал этой аннотации, так ли нужен метод getParcelableArrayList? Может его заменить на другой в 24 строке?
class VotingFragment : Fragment(R.layout.fragment_voting) {

    private var binding: FragmentVotingBinding? = null
    private var players: ArrayList<Player>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVotingBinding.bind(view)

        BackgroundAnimator.animate(binding?.votingLayout?.background as AnimationDrawable, 10,4000)
        players = arguments?.getParcelableArrayList<Player>("players")
        binding?.radGroup?.removeView(binding?.root?.findViewById(R.id.example))
        addRadBtn()

        binding?.radGroup?.layout(10, 10, 10, 10)

        binding?.run {
            deletePlayer.setOnClickListener {
                val selectedRadBtn = radGroup.checkedRadioButtonId
                radGroup.removeView(binding?.root?.findViewById(selectedRadBtn))
                removePlayer(selectedRadBtn)
            }
        }
    }

    private fun addRadBtn() {
        for (item: Player in players!!){
            val radioButton = RadioButton(context)
            val id = View.generateViewId()
            radioButton.setText(item.name)
            radioButton.setButtonDrawable(R.drawable.custom_icon)
            radioButton.setBackgroundResource(R.drawable.custome_test)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0, 20, 0, 20)
            radioButton.layoutParams = layoutParams
            radioButton.setPadding(30)
            radioButton.setTextSize(30F)
            binding?.radGroup?.addView(radioButton)
        }
    }

    private fun removePlayer(id: Int){
        for (i in (0..players!!.size - 1)){
            if (players!![i].id == id){
                players!!.removeAt(i)
                break
            }
        }
    }
}