package com.android.myretrofit.ui.albumscreen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.myretrofit.R
import com.android.myretrofit.ui.photoscreen.PhotosActivity
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(private val mContext : Context, private var mList: ArrayList<AlbumModel>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onItemBind(mList.get(position))
    }

    override fun getItemCount() = mList.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun onItemBind(album : AlbumModel) {

            album.title.let {  itemView.tvAlbumName.text = album.title }

            itemView.setOnClickListener {
                mContext.startActivity(Intent(mContext,PhotosActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    putExtra("Album", album)
                })
            }

        }

    }

    fun setAlbums(albums : ArrayList<AlbumModel>) {
        mList.addAll(albums)
        notifyDataSetChanged()
    }

}