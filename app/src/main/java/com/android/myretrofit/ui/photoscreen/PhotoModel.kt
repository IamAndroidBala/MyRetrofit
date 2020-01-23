package com.android.myretrofit.ui.photoscreen

import com.google.gson.annotations.SerializedName

/**
 * Created by praka on 12/24/2017.
 */
class PhotoModel(
    @field:SerializedName("albumId") var albumId: Int,
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("url") var url: String,
    @field:SerializedName("thumbnailUrl") var thumbnailUrl: String
)