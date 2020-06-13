package com.lucassimoesmartins.flixguide.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucassimoesmartins.flixguide.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_entertainment.view.*

class EntertainmentListAdapter(
    private val context: Context,
    private var imgEntertainmentList: List<String> = listOf()
) : RecyclerView.Adapter<EntertainmentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_entertainment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imgEntertainmentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(imgEntertainmentList[position])
    }

    fun updateList(newImgEntertainmentList: List<String>) {
        imgEntertainmentList = newImgEntertainmentList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(imgEntertainment: String) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w185${imgEntertainment}")
                .into(itemView.imgEntertainment)
        }
    }
}