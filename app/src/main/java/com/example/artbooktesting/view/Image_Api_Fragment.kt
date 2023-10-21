package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artbooktesting.R
import com.example.artbooktesting.adapter.ArtRecyclerAdapter
import com.example.artbooktesting.adapter.ImageApiAdapter
import com.example.artbooktesting.databinding.FragmentApiBinding
import com.example.artbooktesting.util.Status
import com.example.artbooktesting.viewmodel.ArtViewModel
import javax.inject.Inject

class Image_Api_Fragment @Inject constructor(val imageRecyclerAdapter: ImageApiAdapter) : Fragment(R.layout.fragment_api) {

    lateinit var viewModel: ArtViewModel
    private var imageApiBinding : FragmentApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentApiBinding.bind(view)
        imageApiBinding = binding

        binding.recyclerViewImages.adapter = imageRecyclerAdapter
        binding.recyclerViewImages.layoutManager = GridLayoutManager(requireContext(),3)
        imageRecyclerAdapter.setOnClickListener{
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

    fun subscribeToObservers(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val urls = it.data.hits
                }
                Status.ERROR -> {
                    val urls = it.data.hits
                }
                Status.SUCCESS -> {
                    val urls = it.data.hits
                }

                else -> {}
            }
        })
    }
}