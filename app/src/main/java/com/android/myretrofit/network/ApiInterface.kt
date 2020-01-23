package com.android.myretrofit.network

import com.android.myretrofit.ui.albumscreen.AlbumModel
import com.android.myretrofit.utils.PHOTO_URL
import com.android.myretrofit.ui.photoscreen.PhotoModel
import com.android.myretrofit.utils.ALBUM_URL
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(PHOTO_URL)
    fun getPhotos() :  Call<List<PhotoModel>>

    @GET(ALBUM_URL)
    fun getAlbums() :  Call<List<AlbumModel>>

}