package com.remoteapi.nikhilkumar.remoteapi.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.remoteapi.nikhilkumar.remoteapi.repo.Repository
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement

class MyContestListViewModel(private val repository: Repository) : ViewModel() {

    private var pageNum = 1
    private var pageSize = 5

    private val myContestParamsLiveData = MutableLiveData<Pair<Int, Int?>>()


    private val myContestListLiveData = MutableLiveData<List<MyContestAPIElement>>()

    var myContestApiResult = Transformations.switchMap(myContestParamsLiveData) {
        repository.getMyContestApiResponse(pageNum,pageSize)
    }

    fun setPageData(pair: Pair<Int, Int>) {
        myContestParamsLiveData.value = pair
    }

}
