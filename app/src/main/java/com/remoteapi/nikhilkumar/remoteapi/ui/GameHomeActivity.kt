package com.remoteapi.nikhilkumar.remoteapi.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager

import android.support.v7.widget.LinearLayoutManager
import com.remoteapi.nikhilkumar.remoteapi.APIConstants
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.controllers.TimeController
import com.remoteapi.nikhilkumar.remoteapi.controllers.TimerRefreshThread
import com.remoteapi.nikhilkumar.remoteapi.utils.Status
import com.remoteapi.nikhilkumar.remoteapi.utils.*
import com.remoteapi.nikhilkumar.remoteapi.viewModel.GameImageListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import obtainViewModel


class GameHomeActivity : AppCompatActivity() , TimerRefreshThread.Callback{
    override fun onSecondComplete() {

    }

    override fun onPauseTimer() {

    }

    override fun onTimerComplete() {

    }

    lateinit var viewModel: GameImageListViewModel
    var isLoading = false
    var isLastPage = false
    var mHomeAdapter : GameHomeAdapter? = null
    var timerRefreshThread : TimerRefreshThread ?= null


    var task = Runnable {
        //TODO task to decrease seconds
    }

    override fun onPause() {
        super.onPause()
        TimeController.pauseTimer()
    }

    override fun onResume() {
        super.onResume()
        TimeController.resumeTimer()
    }


    override fun onDestroy() {
        super.onDestroy()
        timerRefreshThread?.quit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyclerView()
        viewModel = obtainViewModel(GameImageListViewModel::class.java)

        viewModel.imageListLiveData.observe(this, Observer {
            when(it?.status){
                Status.ERROR -> {

                }

                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    if(it.data?.isNotEmpty() == true)
                        bindView(it.data!!)
                }
            }
        })

        initThread()
    }

    private fun initThread(){
        timerRefreshThread = TimerRefreshThread(APIConstants.WORKER_THREAD)
        timerRefreshThread?.initThread(Handler(),this)
        timerRefreshThread?.start()
        timerRefreshThread?.postTasks(task)
    }


    private fun initializeRecyclerView(){
        val layoutManager = GridLayoutManager(this, 2)
        homeRv.layoutManager = layoutManager

        mHomeAdapter = GameHomeAdapter()
        homeRv.adapter = mHomeAdapter

        homeRv.addOnScrollListener(OnScrollListener(layoutManager))

        val decorator = DividerItemDecoration( this,GridLayoutManager.HORIZONTAL)
        homeRv.addItemDecoration(decorator)
    }

    private fun bindView(prList : List<Int>){
        mHomeAdapter?.updateData(prList)
    }

    inner class OnScrollListener(layoutManager: LinearLayoutManager) : PaginationScrollListener(layoutManager) {
        override fun isLoading() = isLoading
        override fun loadMoreItems() = loadNextPage()
        override fun isLastPage() = isLastPage
    }

    private fun loadNextPage() {
        mHomeAdapter?.addLoadingViewFooter()
//        viewModel.loadNextPage(searchTv.text.toString())
    }
}