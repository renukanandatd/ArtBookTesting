package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.artbooktesting.R
import com.example.artbooktesting.adapter.ArtRecyclerAdapter
import com.example.artbooktesting.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class Image_Api_Fragment @Inject constructor(imageRecyclerAdapter: ImageRecyclerAdapter) : Fragment(R.layout.fragment_api) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}