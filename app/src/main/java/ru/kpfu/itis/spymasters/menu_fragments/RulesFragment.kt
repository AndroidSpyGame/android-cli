package ru.kpfu.itis.spymasters.menu_fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.tools.BackgroundAnimator
import ru.kpfu.itis.spymasters.R
import ru.kpfu.itis.spymasters.repositories.RulesRepository
import ru.kpfu.itis.spymasters.databinding.FragmentRulesBinding


class RulesFragment : Fragment(R.layout.fragment_rules) {

    private lateinit var binding: FragmentRulesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRulesBinding.bind(view)

        BackgroundAnimator.animate(
            binding.rulesFragmentLayout.background as AnimationDrawable
        )

        //Page flipping through functionality
        val pages = RulesRepository.list
        var i = 0
        binding.run {
            rulesContent.text = pages[i]
            pageNumberText.text = String.format("%d/%d", i + 1, pages.size)
            previousButton.visibility = Button.GONE
            nextButton.setOnClickListener {
                rulesContent.text = pages[++i]
                pageNumberText.text = String.format("%d/%d", i + 1, pages.size)
                previousButton.visibility = Button.VISIBLE
                backButton.visibility = Button.GONE
                if (i == pages.size - 1) {
                    it.visibility = Button.GONE
                    homeButton.visibility = Button.VISIBLE
                }
            }
            previousButton.setOnClickListener {
                rulesContent.text = pages[--i]
                pageNumberText.text = String.format("%d/%d", i + 1, pages.size)
                nextButton.visibility = Button.VISIBLE
                homeButton.visibility = Button.GONE
                if (i == 0) {
                    it.visibility = Button.GONE
                    backButton.visibility = Button.VISIBLE
                }
            }
            homeButton.setOnClickListener {
                findNavController().navigate(R.id.action_rulesFragment_to_mainFragment)
            }
            backButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }



}