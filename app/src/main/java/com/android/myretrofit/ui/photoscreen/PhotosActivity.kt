package com.android.myretrofit.ui.photoscreen

import android.os.Bundle
import android.view.MenuItem
import com.android.myretrofit.BaseActivity
import com.android.myretrofit.R
import com.android.myretrofit.network.ApiInterface
import com.android.myretrofit.network.RetrofitClientInstance
import com.android.myretrofit.ui.albumscreen.AlbumModel
import com.android.myretrofit.utils.CommonMethods
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_photo.*
import retrofit2.Call
import retrofit2.Response

class PhotosActivity : BaseActivity() {

    var mList = ArrayList<PhotoModel>()
    lateinit var albumModel: AlbumModel
    lateinit var photoAdapter: PhotoAdapter
    lateinit var kProgressHUD: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        supportActionBar?.title = resources.getString(R.string.photo)

        if(intent?.extras?.getParcelable<AlbumModel>("Album") != null){
            albumModel = intent?.extras?.getParcelable<AlbumModel>("Album") as AlbumModel
        }

        photoAdapter = PhotoAdapter(mList, this@PhotosActivity)
        recyclerPhoto.adapter = photoAdapter

        if(!CommonMethods.isNetworkAvailable(this@PhotosActivity)) {
            CommonMethods.showToast(this, getString(R.string.no_network))
            return
        }

        kProgressHUD = CommonMethods.createHUD(this@PhotosActivity)

        val mApiInterface = RetrofitClientInstance.getRetrofitInstance()!!.create(ApiInterface::class.java)

        mApiInterface.getPhotos().enqueue(object : retrofit2.Callback<List<PhotoModel>> {

            override fun onResponse(call: Call<List<PhotoModel>>, response: Response<List<PhotoModel>>) {
                response.body()?.let {
                    cancelProgressBar()
                    val finalResult : List<PhotoModel> = it.filter { photos -> photos.albumId == albumModel.id }
                    setResultToRecyclerView(finalResult)
                }
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                cancelProgressBar()
                CommonMethods.showToast(this@PhotosActivity, t.localizedMessage )
            }

        })

    }

    private fun setResultToRecyclerView(photo : List<PhotoModel>) {
        photoAdapter.addPhotos(photo as ArrayList<PhotoModel>)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cancelProgressBar() {
        if (::kProgressHUD.isInitialized) {
            CommonMethods.cancelHUD(kProgressHUD)
        }
    }

}
