package com.example.artbooktesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artbooktesting.model.ImageResponse
import com.example.artbooktesting.repo.ArtRepositoryInterface
import com.example.artbooktesting.roomdb.Art
import com.example.artbooktesting.util.Resource
import javax.inject.Inject

class ArtViewModel @Inject constructor(
    private val  repository : ArtRepositoryInterface
) : ViewModel() {

    //ART FRAGMENT
    val artList = repository.getArt()


    //IMAGE API FRAGMENT
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageURL : LiveData<String>
        get() = selectedImage


    //ART DETAILS FRAGMENT
    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = insertArtMsg

    fun resetInsertArtMsg(){
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }
}