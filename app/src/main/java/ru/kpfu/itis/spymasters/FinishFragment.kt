package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.databinding.FragmentFinishBinding

class FinishFragment : Fragment(R.layout.fragment_finish) {
    private var binding: FragmentFinishBinding? = null
    private var isCiviliansWon: Boolean? = null
    private var spies: ArrayList<Player>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFinishBinding.bind(view)

        BackgroundAnimator.animate(binding?.finishFragmentLayout?.background as AnimationDrawable)

        isCiviliansWon = arguments?.getBoolean(IS_CIVILIANS_WON)
        @Suppress("DEPRECATION")
        spies = arguments?.getParcelableArrayList(SPIES_LIST)

        if (savedInstanceState != null) {
            isCiviliansWon = savedInstanceState.getBoolean(IS_CIVILIANS_WON)
            @Suppress("DEPRECATION")
            spies = savedInstanceState.getParcelableArrayList(SPIES_LIST)
        }

        binding?.tvGameResult?.text = getGameResultString(isCiviliansWon)

        binding?.tvGameSpies?.text = getFormatSpiesList(spies)

        binding?.btnToMainMenu?.setOnClickListener {
            findNavController().navigate(R.id.action_finishFragment_to_mainFragment)
        }
    }

    private fun getGameResultString(isCiviliansWon: Boolean?): String {
        if (isCiviliansWon == null) return requireActivity().resources.getString(R.string.game_end_error)
        return if (isCiviliansWon) requireActivity().resources.getString(R.string.game_end_win)
        else requireActivity().resources.getString(R.string.game_end_loss)
    }

    private fun getFormatSpiesList(spies: List<Player>?): String {
        var resultString = ""
        if (spies != null) {
            for (spy: Player in spies) {
                resultString += "\u2022 ${spy.name}\n"
            }
        }
        return resultString
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (isCiviliansWon != null) outState.putBoolean(IS_CIVILIANS_WON, isCiviliansWon!!)
        if (spies != null) outState.putParcelableArrayList(SPIES_LIST, spies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val IS_CIVILIANS_WON = "IS_CIVILIANS_WIN"
        const val SPIES_LIST = "SPIES_LIST"
    }
}