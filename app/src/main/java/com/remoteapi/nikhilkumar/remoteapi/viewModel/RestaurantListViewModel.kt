package com.remoteapi.nikhilkumar.remoteapi.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.remoteapi.nikhilkumar.remoteapi.repo.Repository
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement

class RestaurantListViewModel(private val repository: Repository) : ViewModel() {

    private var pageNum = 1
    private var pageSize = 10

    private val myContestParamsLiveData = MutableLiveData<Pair<Int, Int?>>()

    private val resEntityId  = MutableLiveData<Int>()

    private val myContestListLiveData = MutableLiveData<List<MyContestAPIElement>>()

    var myContestApiResult = Transformations.switchMap(myContestParamsLiveData) {
        repository.getMyContestApiResponse(pageNum,pageSize)
    }

    fun setPageData(pair: Pair<Int, Int>) {
        myContestParamsLiveData.value = pair
    }

    fun setEntityId(id : Int) {
        resEntityId.value = id
    }

    fun loadNextPage() {
        pageNum++
        val pair = Pair(pageNum,pageSize)
        myContestParamsLiveData.value = pair
    }

    var zomatoApiResult = Transformations.switchMap(resEntityId) {
        repository.getZomatoApiResponse(pageNum,pageSize)
    }

    private val searchQuery = MutableLiveData<String>()

    fun setSearchQuery(q : String){
        searchQuery.value = q
    }

    var searchApiResult = Transformations.switchMap(searchQuery) {
        repository.getZomatoSearchAPIResponse(it)
    }



}
