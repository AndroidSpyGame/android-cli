package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.databinding.FragmentCreatorsBinding


class CreatorsFragment : Fragment(R.layout.fragment_creators) {

    private lateinit var binding: FragmentCreatorsBinding
    private lateinit var adapter: CreatorsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatorsBinding.bind(view)
        adapter = CreatorsAdapter()

        BackgroundAnimator.animate(
            binding.creatorsFragmentLayout.background as AnimationDrawable)

        binding.run {
            creatorsRv.adapter = adapter
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_creatorsFragment_to_mainPageFragment)
            }
        }

    }
}