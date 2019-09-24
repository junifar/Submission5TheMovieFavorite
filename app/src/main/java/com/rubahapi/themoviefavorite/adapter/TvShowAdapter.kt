package com.rubahapi.moviecatalogue.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rubahapi.themoviefavorite.BuildConfig.IMAGE_LINK_URL
import com.rubahapi.themoviefavorite.R
import com.rubahapi.themoviefavorite.model.TvShow

class TvShowAdapter(private val context: Context, private val items: List<TvShow>, private val listener: (TvShow) -> Unit):
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(context).inflate(
        R.layout.list_item_tv_show, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(context, items[position], listener)


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.listview_item_title)
        private val description = view.findViewById<TextView>(R.id.listview_item_short_description)
        private val image_path = view.findViewById<ImageView>(R.id.image_logo)

        fun bindItem(context: Context, items: TvShow, listener: (TvShow) -> Unit){
            name.text = items.name
            description.text = items.overview
            Glide.with(context).load("$IMAGE_LINK_URL${items.poster_path}")
                .into(image_path)
            itemView.setOnClickListener{listener(items)}
        }
    }

}