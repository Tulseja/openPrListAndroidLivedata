package com.remoteapi.nikhilkumar.remoteapi.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations


import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.utils.Resource
import com.remoteapi.nikhilkumar.remoteapi.utils.Status

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {


    override fun getOpenPRList(pageNum: Int, pageSize: Int, repoName: String): LiveData<Resource<List<PRObject>>> {
        val remoteLiveData = remoteDataSource.getOpenPrList(pageNum, pageSize,repoName)

        return Transformations.map(remoteLiveData) {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data?.isEmpty() == false )
                        Resource.success(it.data!!)
                    else
                        Resource.error(it.error,it.isPaginatedLoading)
                }
                Status.ERROR -> {
                    Resource.error(it.error,it.isPaginatedLoading)
                }
                Status.LOADING -> {
                    Resource.loading(it.isPaginatedLoading)
                }
            }
        }
    }
}