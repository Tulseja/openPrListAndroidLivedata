package com.remoteapi.nikhilkumar.remoteapi.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.remoteapi.nikhilkumar.remoteapi.controllers.TimeController
import com.remoteapi.nikhilkumar.remoteapi.repo.Repository
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.OpenPRResponse


class GameImageListViewModel(private val repository: Repository) : ViewModel() {

    private val imgListLiveData = MutableLiveData<Pair<Int,Int>>()

    var imageListLiveData = Transformations.switchMap(imgListLiveData){
        repository.getImageList(it.first,it.second)
    }

    fun loadNextPage( pair : Pair<Int, Int>) {
        imgListLiveData.value = pair
    }

}