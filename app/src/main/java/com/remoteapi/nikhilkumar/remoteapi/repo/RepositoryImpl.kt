package com.remoteapi.nikhilkumar.remoteapi.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations

import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.Restaurant
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.Restaurants
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
                        Resource.error(it.error)
                }
                Status.ERROR -> {
                    Resource.error(it.error)
                }
                Status.LOADING -> {
                    Resource.loading(it.isPaginatedLoading)
                }
            }
        }
    }


    override fun getMyContestApiResponse(pageNum: Int, pageSize: Int): LiveData<Resource<List<MyContestAPIElement>>> {
        val remoteLiveData = remoteDataSource.getMyContestDetails(pageNum, pageSize)

        return Transformations.map(remoteLiveData) {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data != null)
                    Resource.success(it.data!!)
                    else
                    Resource.error(it.error)
                }
                Status.ERROR -> {
                    Resource.error(it.error)
                }
                Status.LOADING -> {
                    Resource.loading(it.isPaginatedLoading)
                }
            }
        }

    }

    override fun getZomatoApiResponse(pageNum: Int, pageSize: Int): LiveData<Resource<List<Restaurants>>> {
        val remoteLiveData = remoteDataSource.getZomatoAPiResponse(pageNum, pageSize)

        return Transformations.map(remoteLiveData) {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data?.restaurants?.isNotEmpty() == true)
                        Resource.success(it.data?.restaurants!!)
                    else
                        Resource.error(it.error)
                }
                Status.ERROR -> {
                    Resource.error(it.error)
                }
                Status.LOADING -> {
                    Resource.loading(it.isPaginatedLoading)
                }
            }
        }

    }

    override fun getZomatoSearchAPIResponse(query : String) : LiveData<Resource<List<Restaurants>>>{
        val remoteLiveData = remoteDataSource.getZomatoAPiResponse(query)
        return Transformations.map(remoteLiveData) {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data?.restaurants?.isNotEmpty() == true)
                        Resource.success(it.data?.restaurants!!)
                    else
                        Resource.error(it.error)
                }
                Status.ERROR -> {
                    Resource.error(it.error)
                }
                Status.LOADING -> {
                    Resource.loading(it.isPaginatedLoading)
                }
            }
        }
    }




}