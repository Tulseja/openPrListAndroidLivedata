package com.remoteapi.nikhilkumar.remoteapi.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.Contests
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.UserTeam


class TeamListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var VIEW_TYPE_HEADER = 0
    var VIEW_TYPE_ITEMS = 1
    var _allTeamList = mutableListOf<UserTeam>()

    fun updateData(teams : List<UserTeam>){
        if(_allTeamList.isEmpty()){
            _allTeamList.addAll(teams)
            notifyItemRangeChanged(0,_allTeamList.size)
        }
    }

    override fun getItemViewType(position: Int): Int {
       return when(position){
            0 -> VIEW_TYPE_HEADER
           else -> VIEW_TYPE_ITEMS

        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return when(p1){
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(p0.context).inflate(R.layout.team_details_header_lyt, p0, false)
                return HeaderViewHolder(view)}
            else -> {
                val view = LayoutInflater.from(p0.context).inflate(R.layout.team_details_item_lyt, p0, false)
                TeamListItemViewHolder(view)
            }

        }

    }

    override fun getItemCount(): Int {
        return _allTeamList.size +1
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if(p0 is TeamListItemViewHolder){
            p0.bind(_allTeamList[p1-1])
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class TeamListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val rankValueTv by lazy { itemView.findViewById<TextView>(R.id.rank_value_tv) }
        val teamValueTv by lazy { itemView.findViewById<TextView>(R.id.team_value_tv) }
        val pointValueTv by lazy { itemView.findViewById<TextView>(R.id.points_value_tv)}

        fun bind(teamItem : UserTeam) {
            if(teamItem.rank != null) {
                if (teamItem.rank!! < 10) {
                    rankValueTv.text = "0"+teamItem.rank?.toString()
                } else{
                    rankValueTv.text = teamItem.rank?.toString()
                }
            }

            teamValueTv.text = teamItem.name
            pointValueTv.text = teamItem.points?.toString()
            //TODO
            /*val adapter = TeamListAdapter()
            teamDetailsRv.adapter = adapter
            adapter.updateData(contestElement.userTeamList)*/

        }
    }


}
