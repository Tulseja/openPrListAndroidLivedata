package com.remoteapi.nikhilkumar.remoteapi.ui

import android.annotation.SuppressLint
import android.support.v7.widget.AppCompatRatingBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.utils.PaginationAdapter
import com.remoteapi.nikhilkumar.remoteapi.utils.loadImage


class MyContestHomeAdapter : PaginationAdapter<PRObject>() {
    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.res_list_item, parent, false)
        return PRViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is PRViewHolder) {
            holder.bind(dataList[position])
        }
    }

    override fun addLoadingViewFooter() {
        addLoadingViewFooter(PRObject())
    }

    fun updateData(isSearchResult : Boolean , resList : List<PRObject>){
        if(isSearchResult) {
            dataList.clear()
            dataList.addAll(resList)
            notifyDataSetChanged()
        } else {
            val currentSize = itemCount
            dataList.addAll(resList)
            notifyItemRangeInserted(currentSize, dataList.size)
        }
    }



    class PRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context by lazy { itemView.context }
        val logoIv by lazy { itemView.findViewById<ImageView>(R.id.logoIv) }
        val titleTv by lazy { itemView.findViewById<TextView>(R.id.resNameTv) }

        val prTitleTv by lazy { itemView.findViewById<TextView>(R.id.prTitleTv) }




        fun bind(prElement : PRObject) {
            if(!TextUtils.isEmpty(prElement.userObj?.imgUrl))
                logoIv.loadImage(prElement.userObj?.imgUrl)
            titleTv.text = prElement.userObj?.loginName
            prTitleTv.text = prElement.prTitle

        }

    }



}