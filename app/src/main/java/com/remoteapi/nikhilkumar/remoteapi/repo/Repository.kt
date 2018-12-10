package com.remoteapi.nikhilkumar.remoteapi.repo

import android.arch.lifecycle.LiveData
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.utils.Resource

interface Repository {
    fun getOpenPRList(pageNum: Int, pageSize: Int,repoName : String ): LiveData<Resource<List<PRObject>>>
}