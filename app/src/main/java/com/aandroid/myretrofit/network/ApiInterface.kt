package com.aandroid.myretrofit.network

import com.aandroid.myretrofit.ui.albumscreen.AlbumModel
import com.aandroid.myretrofit.utils.PHOTO_URL
import com.aandroid.myretrofit.ui.photoscreen.PhotoModel
import com.aandroid.myretrofit.utils.ALBUM_URL
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(PHOTO_URL)
    fun getPhotos() :  Call<List<PhotoModel>>

    @GET(ALBUM_URL)
    fun getAlbums() :  Call<List<AlbumModel>>

}