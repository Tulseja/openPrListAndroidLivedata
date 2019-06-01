package com.remoteapi.nikhilkumar.remoteapi.ui

import android.annotation.SuppressLint
import android.support.v7.widget.AppCompatRatingBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.ImageBitmapContainer
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.utils.PaginationAdapter
import com.remoteapi.nikhilkumar.remoteapi.utils.loadImage


class GameHomeAdapter : PaginationAdapter<Any>() {
    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.res_list_item, parent, false)
        return ImageBitmapHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if(holder is ImageBitmapHolder){
            holder.bind(dataList[position])
        }
    }

    override fun addLoadingViewFooter() {
        addLoadingViewFooter(PRObject())
    }

    fun updateData(resList : List<Int>){
        val currentSize = itemCount
        dataList.addAll(resList)
        notifyItemRangeInserted(currentSize, dataList.size)
    }


    class ImageBitmapHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context by lazy { itemView.context }
        val logoIv by lazy { itemView.findViewById<Button>(R.id.logoIv) }



        fun bind(element : Any) {
            if(element is Int) {
                logoIv.text = element.toString()
            }

        }

    }



}