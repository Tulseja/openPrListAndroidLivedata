package com.remoteapi.nikhilkumar.remoteapi.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration

import android.support.v7.widget.LinearLayoutManager
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement
import com.remoteapi.nikhilkumar.remoteapi.utils.Status
import com.remoteapi.nikhilkumar.remoteapi.utils.*
import com.remoteapi.nikhilkumar.remoteapi.viewModel.MyContestListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import obtainViewModel


class MyContestHomeActivity : AppCompatActivity(){

    lateinit var viewModel: MyContestListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
        setOnScrollListener()
    }

    private fun observeViewModel(){
        viewModel = obtainViewModel(MyContestListViewModel::class.java)
        viewModel.setPageData(Pair(1,5))

        viewModel.myContestApiResult.observe(this, Observer {

            it?.let {
                when (it.status) {
                    Status.LOADING -> {
                        homeRv.hide()
                        errorLyt.hide()
                        progress_bar.show()
                    }
                    Status.ERROR -> {
                        progress_bar.hide()
                        errorLyt.show()
                        homeRv.hide()
                    }
                    Status.SUCCESS -> {
                        progress_bar.hide()
                        errorLyt.hide()

                        it.data?.let {
                            homeRv.show()
                            bindView(it)
                        }
                    }
                }
            }

        })

    }


    private fun bindView(contestList : List<MyContestAPIElement>){
        val adapter = MyContestHomeAdapter()
        homeRv.adapter = adapter
        adapter.updateData(contestList)

        val decorator = DividerItemDecoration( this,LinearLayoutManager.VERTICAL)
        homeRv.addItemDecoration(decorator)
    }

    private fun setOnScrollListener(){

    }
}