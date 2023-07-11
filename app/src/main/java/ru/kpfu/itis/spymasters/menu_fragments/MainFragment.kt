package ru.kpfu.itis.spymasters.menu_fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.tools.BackgroundAnimator
import ru.kpfu.itis.spymasters.R
import ru.kpfu.itis.spymasters.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        BackgroundAnimator.animate(binding.mainFragmentLayout.background as AnimationDrawable)

        val navController = findNavController()
        binding.run {
            rulesButton.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_rulesFragment)
            }
            creatorsButton.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_creatorsFragment)
            }
            playButton.setOnClickListener {
                navController.navigate(R.id.action_mainFragment_to_createGameFragment)
            }
        }

    }

}
