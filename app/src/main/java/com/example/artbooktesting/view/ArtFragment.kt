package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artbooktesting.R
import com.example.artbooktesting.databinding.FragmentArtsBinding

class ArtFragment : Fragment(R.layout.fragment_arts) {

    lateinit var artFragmentBinding : FragmentArtsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        artFragmentBinding = binding

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }
}