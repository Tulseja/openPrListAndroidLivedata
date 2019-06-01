package com.remoteapi.nikhilkumar.remoteapi.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations


import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.utils.Resource
import com.remoteapi.nikhilkumar.remoteapi.utils.Status

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getImageList(numRow: Int, numCol: Int): LiveData<Resource<List<Int>>>? {

        val list = mutableListOf<Int>()
                //TODO
        return null
    }


}