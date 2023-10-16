package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artbooktesting.R
import com.example.artbooktesting.databinding.FragmentArtDetailsBinding

class ArtDetailsFragment : Fragment(R.layout.fragment_art_details) {

    private var artDetailsBinding:FragmentArtDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        artDetailsBinding = binding

        binding.imageView2.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        val callback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        artDetailsBinding = null
        super.onDestroyView()
    }
    }
