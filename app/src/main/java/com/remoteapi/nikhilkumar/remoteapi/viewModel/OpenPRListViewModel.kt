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
        repoNameLiveData.value = userName
    }

    private val repoNameLiveData = MutableLiveData<String>()

    var openPRListLiveData = Transformations.switchMap(repoNameLiveData){
        repository.getOpenPRList(pageNum,pageSize,it)
    }


}