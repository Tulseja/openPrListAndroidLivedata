package com.remoteapi.nikhilkumar.remoteapi.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.Contests



class ContestListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val _allContestList = mutableListOf<Contests>()
    var VIEW_TYPE_PUBLIC_CONTEST = 0
    var VIEW_TYPE_PRIVATE_CONTEST = 1

    fun updateData(contestList : List<Contests>){
        if (_allContestList.isEmpty()) {
            _allContestList.addAll(contestList)
            notifyItemRangeChanged(0,_allContestList.size)
        }

    }


    override fun getItemViewType(position: Int): Int {

        if(_allContestList.isNotEmpty()){
            if(_allContestList[position].type?.equals("public",true) == true){
                return VIEW_TYPE_PUBLIC_CONTEST
            } else if(_allContestList[position].type?.equals("private",true) == true){
                return VIEW_TYPE_PRIVATE_CONTEST
            }
        }

        return position
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
       return when(p1){
            VIEW_TYPE_PUBLIC_CONTEST ->{
                val view = LayoutInflater.from(p0.context).inflate(R.layout.public_contest_item_lyt, p0, false)
                PublicContestViewHolder(view)
            }
            VIEW_TYPE_PRIVATE_CONTEST ->{
                val view = LayoutInflater.from(p0.context).inflate(R.layout.private_contest_item_lyt, p0, false)
                PrivateContestViewHolder(view)
            } else ->{
               val view = LayoutInflater.from(p0.context).inflate(R.layout.private_contest_item_lyt, p0, false)
               PrivateContestViewHolder(view)
           }
        }
    }

    override fun getItemCount(): Int {
        return _allContestList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if(p0 is PublicContestViewHolder){
            p0.bind(_allContestList[p1])
        }
        if(p0 is PrivateContestViewHolder){
            p0.bind(_allContestList[p1])
        }
    }


    inner class PublicContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context by lazy { itemView.context }

        val totalPrizeTv by lazy { itemView.findViewById<TextView>(R.id.total_prize_value_tv) }

        val coinsValueTv by lazy { itemView.findViewById<TextView>(R.id.coins_value_tv) }
        val winnersValueTv by lazy { itemView.findViewById<TextView>(R.id.winner_value_tv) }

        val playingValueTv by lazy { itemView.findViewById<TextView>(R.id.playing_value_tv) }
        val teamDetailsRv by lazy { itemView.findViewById<RecyclerView>(R.id.teamDetailsRv)}

        fun bind(contestElement : Contests) {
            if(contestElement.winnerPrizeList?.isNotEmpty() == true){
                //TODO no key is there
                totalPrizeTv.text = "50,000"

                coinsValueTv.text = contestElement.winnerPrizeList?.get(0)?.totalPrize?.toString()
                winnersValueTv.text = contestElement.winnerPrizeList?.get(0)?.totalWinner.toString()


            }
            playingValueTv.text = contestElement.playingNumber?.toString()

            val adapter = TeamListAdapter()
            teamDetailsRv.adapter = adapter
            if(contestElement.userTeamList != null)
                adapter.updateData(contestElement.userTeamList!!)


        }

    }

    inner class PrivateContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val teamsPerPlayerValueTv by lazy { itemView.findViewById<TextView>(R.id.teams_per_player_value_tv) }


        val playingValueTv by lazy { itemView.findViewById<TextView>(R.id.playing_value_tv) }
        val teamDetailsRv by lazy { itemView.findViewById<RecyclerView>(R.id.teamDetailsRv)}

        fun bind(contestElement : Contests) {

            playingValueTv.text = contestElement.playingNumber?.toString()
            teamsPerPlayerValueTv.text = contestElement.maxTeamPerUser?.toString()

            val adapter = TeamListAdapter()
            teamDetailsRv.adapter = adapter

            if(contestElement.userTeamList != null)
                adapter.updateData(contestElement.userTeamList!!)

        }
    }
}