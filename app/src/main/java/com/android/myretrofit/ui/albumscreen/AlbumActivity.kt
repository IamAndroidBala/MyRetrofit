package com.android.myretrofit.ui.albumscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.myretrofit.R
import com.android.myretrofit.network.ApiInterface
import com.android.myretrofit.network.RetrofitClientInstance
import com.android.myretrofit.utils.CommonMethods
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_album.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumActivity : AppCompatActivity() {

    var mList = ArrayList<AlbumModel>()
    lateinit var albumAdapter: AlbumAdapter
    lateinit var kProgressHUD: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        albumAdapter = AlbumAdapter(this@AlbumActivity, mList)
        recyclerAlbum.adapter = albumAdapter

        if(!CommonMethods.isNetworkAvailable(this@AlbumActivity)) {
            CommonMethods.showToast(this, getString(R.string.no_network))
            return
        }

        kProgressHUD = CommonMethods.createHUD(this@AlbumActivity)

        val apiInterface = RetrofitClientInstance.getRetrofitInstance()!!.create(ApiInterface::class.java)

        apiInterface.getAlbums().enqueue(object : Callback<List<AlbumModel>> {

            override fun onResponse(call: Call<List<AlbumModel>>, response: Response<List<AlbumModel>>) {
                cancelProgressBar()
                setAlbums(response.body() as ArrayList<AlbumModel>)
            }

            override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {
                cancelProgressBar()
                CommonMethods.showToast(this@AlbumActivity, t.localizedMessage )
            }

        })

    }

    private fun setAlbums(result : ArrayList<AlbumModel>) {
        albumAdapter.setAlbums(result)
    }

    private fun cancelProgressBar() {
        if (::kProgressHUD.isInitialized) {
            CommonMethods.cancelHUD(kProgressHUD)
        }
    }

}