package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.artbooktesting.R
import com.example.artbooktesting.adapter.ArtRecyclerAdapter
import com.example.artbooktesting.databinding.FragmentArtsBinding
import com.example.artbooktesting.viewmodel.ArtViewModel
import com.example.artbooktesting.viewmodel.ArtViewModel_Factory
import javax.inject.Inject

class ArtFragment @Inject constructor(
    val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.fragment_arts) {

    private var artFragmentBinding : FragmentArtsBinding? = null
    lateinit var viewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentArtsBinding.bind(view)
        artFragmentBinding = binding

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }

    private fun subscribeToObservers(){

    }

    override fun onDestroyView() {
        artFragmentBinding = null
        super.onDestroyView()
    }
}