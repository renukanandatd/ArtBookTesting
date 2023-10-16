package com.example.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.example.artbooktesting.api.RetroFitApi
import com.example.artbooktesting.model.ImageResponse
import com.example.artbooktesting.roomdb.Art
import com.example.artbooktesting.roomdb.ArtDAO
import com.example.artbooktesting.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artdao:ArtDAO,
    private val retroFitApi: RetroFitApi
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artdao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artdao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artdao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retroFitApi.imageSearch(imageString)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("ERROR", null)
            }else{
                Resource.error("ERROR!", null)
            }
        }catch (e:Exception){
            Resource.error("NO DATA!", null)
        }
    }
}