package com.example.artbooktesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbooktesting.model.ImageResponse
import com.example.artbooktesting.repo.ArtRepositoryInterface
import com.example.artbooktesting.roomdb.Art
import com.example.artbooktesting.util.Resource
import kotlinx.coroutines.launch
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
    fun setSelectedImage(url:String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art:Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art:Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name:String, artistName : String, year :String){
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name, artist name, year",null))
            return
        }
        val yearInt = try {
            year.toInt()
        }catch (e:Exception){
            insertArtMsg.postValue(Resource.error("Year should be a number",null))
            return
        }

        val art = Art(name,artistName,selectedImage.value?:"",yearInt)
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(searchingString: String){

        if (searchingString.isEmpty()){
            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchingString)
            images.value = response
        }
    }
}