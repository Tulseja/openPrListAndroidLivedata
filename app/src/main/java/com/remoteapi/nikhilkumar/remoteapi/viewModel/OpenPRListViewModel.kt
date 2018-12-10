package com.remoteapi.nikhilkumar.remoteapi.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.remoteapi.nikhilkumar.remoteapi.repo.Repository
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.OpenPRResponse


class OpenPRListViewModel(private val repository: Repository) : ViewModel() {

    private var pageNum = 1
    private var pageSize = 10


    fun setUserName(userName : String){
        //on search icon hit. new search is being processed. so back to page 1.
        pageNum = 1
        repoNameLiveData.value = userName
    }

    private val repoNameLiveData = MutableLiveData<String>()

    var openPRListLiveData = Transformations.switchMap(repoNameLiveData){
        repository.getOpenPRList(pageNum,pageSize,it)
    }

    fun loadNextPage(userName : String) {
        ++pageNum
        repoNameLiveData.value = userName
    }

}