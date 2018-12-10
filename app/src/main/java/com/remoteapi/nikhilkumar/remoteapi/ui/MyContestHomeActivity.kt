package com.remoteapi.nikhilkumar.remoteapi.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration

import android.support.v7.widget.LinearLayoutManager
import com.remoteapi.nikhilkumar.remoteapi.R
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.*
import com.remoteapi.nikhilkumar.remoteapi.utils.Status
import com.remoteapi.nikhilkumar.remoteapi.utils.*
import com.remoteapi.nikhilkumar.remoteapi.viewModel.OpenPRListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import obtainViewModel


class MyContestHomeActivity : AppCompatActivity(){

    private var isLoading = false
    private var isLastPage = false
    lateinit var viewModel: OpenPRListViewModel
    var shouldSearch: Boolean = false
    var mHomeAdapter : MyContestHomeAdapter ? = null

    var serachObserver = Observer<Resource<List<PRObject>>>{
        it?.let {
            when (it.status) {
                Status.SUCCESS -> {
                            progress_bar.hide()
                            errorLyt.hide()
                            mHomeAdapter?.removeLoadingViewFooter()
                        it.data?.let {
                            homeRv.show()
                            isLoading = false
                            if(it.isNotEmpty()){
                                bindView(it)
                            }
                        }
                }
                Status.ERROR -> {
                    isLoading = false
                    progress_bar.hide()
                    /*if(!it.isPaginatedLoading) {
                        errorLyt.show()
                        homeRv.hide()
                    }*/
                    isLastPage = true
                mHomeAdapter?.removeLoadingViewFooter()
                }
                Status.LOADING -> {
                    if (!it.isPaginatedLoading) {
                        homeRv.hide()
                        errorLyt.hide()
                        progress_bar.show()
                    }
                    isLoading = true
                    errorLyt.hide()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyclerView()
        viewModel = obtainViewModel(OpenPRListViewModel::class.java)
        searchIv.setOnClickListener { hitSearchAPI() }

        viewModel.openPRListLiveData.observe(this,serachObserver)
    }

    private fun hitSearchAPI(){
        shouldSearch = true
        isLastPage = false
        viewModel.setUserName(searchTv.text.toString())
    }

    private fun initializeRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)
        homeRv.layoutManager = linearLayoutManager

        mHomeAdapter = MyContestHomeAdapter()
        homeRv.adapter = mHomeAdapter

        homeRv.addOnScrollListener(OnScrollListener(linearLayoutManager))

        val decorator = DividerItemDecoration( this,LinearLayoutManager.VERTICAL)
        homeRv.addItemDecoration(decorator)
    }

    private fun bindView(prList : List<PRObject>){
        mHomeAdapter?.updateData(shouldSearch,prList)
    }

    private fun loadNextPage() {
        shouldSearch = false
        mHomeAdapter?.addLoadingViewFooter()
        viewModel.loadNextPage(searchTv.text.toString())
    }

    inner class OnScrollListener(layoutManager: LinearLayoutManager) : PaginationScrollListener(layoutManager) {
        override fun isLoading() = isLoading
        override fun loadMoreItems() = loadNextPage()
        override fun isLastPage() = isLastPage
    }
}