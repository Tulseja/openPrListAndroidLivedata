package com.remoteapi.nikhilkumar.remoteapi.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement
import com.remoteapi.nikhilkumar.remoteapi.utils.loadImage
import java.text.SimpleDateFormat
import java.util.*

class MyContestHomeAdapter : RecyclerView.Adapter<MyContestHomeAdapter.AllContestViewHolder>(){

    private val _allContestList = mutableListOf<MyContestAPIElement>()
    protected var scrollListener: RecyclerView.OnScrollListener? = null
    private val visibleThreshold = 5
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    var isLoading: Boolean = false

    fun updateData(contestList : List<MyContestAPIElement>){
        if (_allContestList.isEmpty()) {
            _allContestList.addAll(contestList)
            notifyItemRangeChanged(0,_allContestList.size)
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AllContestViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.my_contest_list_tem, p0, false)
        return AllContestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _allContestList.size
    }

    override fun onBindViewHolder(p0: AllContestViewHolder, p1: Int) {
        p0.bind(_allContestList[p1])
    }


    inner class AllContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

    }
}