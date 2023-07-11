package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.databinding.FragmentVotingBinding

@Suppress("DEPRECATION")
class VotingFragment : Fragment(R.layout.fragment_voting) {
    private var binding: FragmentVotingBinding? = null
    private var players: ArrayList<Player>? = null
    private var selected: ArrayList<Player> = ArrayList()
    private var removedCounter: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVotingBinding.bind(view)

        BackgroundAnimator.animate(binding?.votingLayout?.background as AnimationDrawable, 10, 4000)

        players = arguments?.getParcelableArrayList<Player>("players")
        binding?.radGroup?.removeView(binding?.root?.findViewById(R.id.example))
        addRadBtn()

        var countSpy = arguments?.getInt("countSpy") ?: 1

        binding?.radGroup?.layout(10, 10, 10, 10)

        binding?.run {
            deletePlayer.setOnClickListener {
                if (removedCounter < countSpy) {
                    val selectedRadBtn = radGroup.checkedRadioButtonId
                    radGroup.removeView(binding?.root?.findViewById(selectedRadBtn))
                    removePlayer(selectedRadBtn)
                    removedCounter++
                    if (removedCounter >= countSpy) toFinishFragment()
                } else toFinishFragment()
            }
        }
    }

    private fun toFinishFragment() {
        val bundle = Bundle()
        bundle.apply {
            if (players != null) {
                val sortedPlayers = players?.filter { o -> o.isSpy }?.sortedBy { o -> o.id }
                selected = ArrayList(selected.sortedBy { o -> o.id })
                if (sortedPlayers != null) putBoolean(FinishFragment.IS_CIVILIANS_WON, isSameSpyLists(selected, sortedPlayers))
            }
            if (selected != null) putParcelableArrayList(FinishFragment.SPIES_LIST, selected)
        }
        findNavController().navigate(R.id.action_votingFragment_to_finishFragment, bundle)
    }

    private fun isSameSpyLists(selected: ArrayList<Player>, spies: List<Player>): Boolean {
        if (selected.size != spies.size) return false
        for (i in 0 until selected.size) {
            if (selected[i].id != spies[i].id) return false
        }
        return true
    }

    private fun addRadBtn() {
        if (players == null) return
        for (item: Player in players!!) {
            val radioButton = RadioButton(context)
            val id =
                View.generateViewId() // TODO: Зачем здесь получают id, если потом не используется?
            radioButton.text = item.name
            radioButton.setButtonDrawable(R.drawable.custom_icon)
            radioButton.setBackgroundResource(R.drawable.custome_test)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 20, 0, 20)
            radioButton.layoutParams = layoutParams
            radioButton.setPadding(30)
            radioButton.textSize = 30F
            binding?.radGroup?.addView(radioButton)
        }
    }

    private fun removePlayer(id: Int) {
        for (i in (0 until players!!.size)) {
            if (players!![i].id == id) {
                selected.add(players!![i])
                players!!.removeAt(i)
                break
            }
        }
    }
}