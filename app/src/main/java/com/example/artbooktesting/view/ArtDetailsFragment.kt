package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import com.example.artbooktesting.databinding.FragmentArtDetailsBinding
import com.example.artbooktesting.util.Status
import com.example.artbooktesting.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtDetailsFragment@Inject constructor(
    val glide: RequestManager
)  : Fragment(R.layout.fragment_art_details) {

    private var artDetailsBinding:FragmentArtDetailsBinding? = null
    lateinit var viewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentArtDetailsBinding.bind(view)
        artDetailsBinding = binding

        subscribeObservers()

        binding.imageView2.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        val callback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.buttonSubmit.setOnClickListener {
            viewModel.makeArt(binding.artName.text.toString(),
                binding.editartistName.text.toString(),
                binding.year.text.toString())
        }
    }

    private fun subscribeObservers(){
        viewModel.selectedImageURL.observe(viewLifecycleOwner, Observer { url ->
            artDetailsBinding?.let {
                glide.load(url).into(it.imageView2)
            }
        })
        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message?:"ERROR",Toast.LENGTH_SHORT).show()

                }
                Status.LOADING ->{

                }
            }
        })

    }

    override fun onDestroyView() {
        artDetailsBinding = null
        super.onDestroyView()
    }
    }
