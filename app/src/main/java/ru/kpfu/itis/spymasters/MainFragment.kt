package ru.kpfu.itis.spymasters

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding : FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        binding.run{
            goGameCreationScreen.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_createGameFragment)
            }
        }
    }
}