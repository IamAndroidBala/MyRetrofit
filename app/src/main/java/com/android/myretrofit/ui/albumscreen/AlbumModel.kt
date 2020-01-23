package com.android.myretrofit.ui.albumscreen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumModel(var userId : Int, var id : Int, var title : String) : Parcelable{}