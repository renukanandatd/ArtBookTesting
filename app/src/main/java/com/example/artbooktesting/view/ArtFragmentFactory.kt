package com.example.artbooktesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.adapter.ArtRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val imageRecyclerAdapter: ArtRecyclerAdapter,
    private val glide:RequestManager
) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            Image_Api_Fragment::class.java.name -> Image_Api_Fragment(imageRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }

}