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
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.Restaurant
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.Restaurants
import com.remoteapi.nikhilkumar.remoteapi.utils.PaginationAdapter
import com.remoteapi.nikhilkumar.remoteapi.utils.loadImage
import java.text.SimpleDateFormat
import java.util.*

class MyContestHomeAdapter : PaginationAdapter<PRObject>() {
    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.res_list_item, parent, false)
        return PRViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        /*if (holder is RestaurantViewHolder) {
            holder.bind(dataList[position])
        }*/

        if (holder is PRViewHolder) {
            holder.bind(dataList[position])
        }
    }

    override fun addLoadingViewFooter() {
        addLoadingViewFooter(PRObject())
    }

    /*fun updateData(isSearchResult : Boolean, resList : List<Restaurants>){*/
    fun updateData(resList : List<PRObject>){
        /*if(isSearchResult){
            dataList.clear()
            dataList.addAll(resList)
            notifyDataSetChanged()
        } else {
            val currentSize = itemCount
            dataList.addAll(resList)
            notifyItemRangeInserted(currentSize, dataList.size)
        }*/
        val currentSize = itemCount
        dataList.addAll(resList)
        notifyItemRangeInserted(currentSize, dataList.size)
    }

     /*class AllContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context by lazy { itemView.context }
        val teamOneIv by lazy { itemView.findViewById<ImageView>(R.id.team1_iv) }
        val teamOneTitle by lazy { itemView.findViewById<TextView>(R.id.team1_tv) }

        val teamOneScore by lazy { itemView.findViewById<TextView>(R.id.team1_score_tv) }
        val teamOneOver by lazy { itemView.findViewById<TextView>(R.id.team1_over_tv) }

        val teamTwoIv by lazy { itemView.findViewById<ImageView>(R.id.team2_iv) }
        val teamTwoTitle by lazy { itemView.findViewById<TextView>(R.id.team2_tv) }

        val teamTwoScore by lazy { itemView.findViewById<TextView>(R.id.team2_score_tv) }
        val teamTwoOver by lazy { itemView.findViewById<TextView>(R.id.team2_over_tv) }
        val dateTv by lazy { itemView.findViewById<TextView>(R.id.date_tv) }
        val matchStatus by lazy { itemView.findViewById<TextView>(R.id.match_status_tv) }
        val contestRv  by lazy {  itemView.findViewById<RecyclerView>(R.id.contestRv) }

        @SuppressLint("SimpleDateFormat")
        fun bind(contestElement : MyContestAPIElement) {
            teamOneIv.loadImage(contestElement.team1Data?.imgUrl)
            teamOneTitle.text = contestElement.team1Data?.alias
            teamOneScore.text = contestElement.team1Data?.score
            teamOneOver.text = contestElement.team1Data?.overs?.toString()

            teamTwoIv.loadImage(contestElement.team2Data?.imgUrl)
            teamTwoTitle.text = contestElement.team2Data?.alias
            teamTwoScore.text = contestElement.team2Data?.score
            teamTwoOver.text = contestElement.team2Data?.overs?.toString()
            val adapter = ContestListAdapter()

            contestRv.adapter = adapter
            if(contestElement.contestList?.isNotEmpty() == true)
                adapter.updateData(contestElement.contestList!!)

            if(contestElement.startTimeMilis != null) {
                val date = Date(contestElement.startTimeMilis ?: 0)
                val df = SimpleDateFormat("dd/MM/yyyy")
                dateTv.text = df.format(date)
            }
            matchStatus.text = contestElement.matchStatus
        }

    }*/

    /*class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context by lazy { itemView.context }
        val logoIv by lazy { itemView.findViewById<ImageView>(R.id.logoIv) }
        val resNameTv by lazy { itemView.findViewById<TextView>(R.id.resNameTv) }

        val avgCostTv by lazy { itemView.findViewById<TextView>(R.id.avgCostTv) }
        val ratingBar by lazy { itemView.findViewById<AppCompatRatingBar>(R.id.tv_rating)  }



        fun bind(resElement : Restaurants) {
            if(!TextUtils.isEmpty(resElement.restraunt?.logoUrl))
                logoIv.loadImage(resElement.restraunt?.logoUrl)
            resNameTv.text = resElement.restraunt?.resName
            avgCostTv.text = context.getString(R.string.avg_cost,resElement.restraunt?.avgCost)
            if(!TextUtils.isEmpty(resElement.restraunt?.rating?.avgRating))
                ratingBar.rating = resElement.restraunt?.rating?.avgRating?.toFloat()!!
        }

    }*/

    class PRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context by lazy { itemView.context }
        val logoIv by lazy { itemView.findViewById<ImageView>(R.id.logoIv) }
        val titleTv by lazy { itemView.findViewById<TextView>(R.id.resNameTv) }

        val prTitleTv by lazy { itemView.findViewById<TextView>(R.id.prTitleTv) }
        /*val ratingBar by lazy { itemView.findViewById<AppCompatRatingBar>(R.id.tv_rating)  }*/



        fun bind(prElement : PRObject) {
            if(!TextUtils.isEmpty(prElement.userObj?.imgUrl))
                logoIv.loadImage(prElement.userObj?.imgUrl)
            titleTv.text = prElement.userObj?.loginName
            prTitleTv.text = prElement.prTitle
            /*if(!TextUtils.isEmpty(resElement.restraunt?.rating?.avgRating))
                ratingBar.rating = resElement.restraunt?.rating?.avgRating?.toFloat()!!*/
        }

    }



}