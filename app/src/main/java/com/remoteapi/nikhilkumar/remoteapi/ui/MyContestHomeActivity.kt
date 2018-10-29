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

    private var isLoading = false
    private var isLastPage = false
    lateinit var viewModel: MyContestListViewModel
    var mHomeAdapter : MyContestHomeAdapter ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
        initializeRecyclerView()

    }


    fun initializeRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)
        homeRv.layoutManager = linearLayoutManager
        homeRv.addOnScrollListener(OnScrollListener(linearLayoutManager))

        mHomeAdapter = MyContestHomeAdapter()
        homeRv.adapter = mHomeAdapter


        val decorator = DividerItemDecoration( this,LinearLayoutManager.VERTICAL)
        homeRv.addItemDecoration(decorator)
    }

    private fun observeViewModel(){
        viewModel = obtainViewModel(MyContestListViewModel::class.java)
        viewModel.setPageData(Pair(1,5))

        viewModel.myContestApiResult.observe(this, Observer {

            it?.let {
                when (it.status) {
                    Status.LOADING -> {
                        if (!it.isPaginatedLoading) {
                            homeRv.hide()
                            errorLyt.hide()
                            progress_bar.show()
                        }
                        isLoading = true
                    errorLyt.hide()
                    }
                    Status.ERROR -> {
                        isLoading = false
                        progress_bar.hide()
                        errorLyt.show()
                        homeRv.hide()
                        mHomeAdapter?.removeLoadingViewFooter()
                    }
                    Status.SUCCESS -> {
                        isLoading = false
                        progress_bar.hide()
                        errorLyt.hide()
                        mHomeAdapter?.removeLoadingViewFooter()
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
        mHomeAdapter?.updateData(contestList)
    }

    private fun loadNextPage() {
        mHomeAdapter?.addLoadingViewFooter()
        viewModel.loadNextPage()
    }

    inner class OnScrollListener(layoutManager: LinearLayoutManager) : PaginationScrollListener(layoutManager) {
        override fun isLoading() = isLoading
        override fun loadMoreItems() = loadNextPage()
        override fun isLastPage() = isLastPage
    }
}