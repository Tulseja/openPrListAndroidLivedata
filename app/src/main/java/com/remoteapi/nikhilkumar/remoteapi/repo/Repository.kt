package com.remoteapi.nikhilkumar.remoteapi.repo

import android.arch.lifecycle.LiveData
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement
import com.remoteapi.nikhilkumar.remoteapi.utils.Resource

interface Repository {
    fun getMyContestApiResponse(pageNum : Int , pageSize : Int) : LiveData<Resource<List<MyContestAPIElement>>>
}