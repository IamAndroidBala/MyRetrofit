package com.aandroid.myretrofit.ui.albumscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aandroid.myretrofit.R
import com.aandroid.myretrofit.network.ApiInterface
import com.aandroid.myretrofit.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_album.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumActivity : AppCompatActivity() {

    var mList = ArrayList<AlbumModel>()
    lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        albumAdapter = AlbumAdapter(this@AlbumActivity, mList)
        recyclerAlbum.adapter = albumAdapter

        val apiInterface = RetrofitClientInstance.getRetrofitInstance()!!.create(ApiInterface::class.java)

        apiInterface.getAlbums().enqueue(object : Callback<List<AlbumModel>> {

            override fun onResponse(call: Call<List<AlbumModel>>, response: Response<List<AlbumModel>>) {
                setAlbums(response.body() as ArrayList<AlbumModel>)
            }

            override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {

            }

        })

    }

    private fun setAlbums(result : ArrayList<AlbumModel>) {
        albumAdapter.setAlbums(result)
    }

}