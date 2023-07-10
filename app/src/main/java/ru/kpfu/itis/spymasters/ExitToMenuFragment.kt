package ru.kpfu.itis.spymasters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.databinding.FragmentCardBinding
import ru.kpfu.itis.spymasters.databinding.FragmentExitToMenuBinding

class ExitToMenuFragment : Fragment(R.layout.fragment_exit_to_menu) {

    private var binding: FragmentExitToMenuBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExitToMenuBinding.bind(view)

        binding?.btnYes?.setOnClickListener {
            findNavController().navigate(R.id.action_exitToMenuFragment_to_mainFragment)
        }
    }
}