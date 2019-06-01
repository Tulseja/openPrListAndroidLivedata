package com.remoteapi.nikhilkumar.remoteapi.repo

import android.arch.lifecycle.LiveData
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject
import com.remoteapi.nikhilkumar.remoteapi.utils.Resource

interface Repository {


    fun getImageList(numRow : Int , numCol : Int) : LiveData<Resource<List<Int>>>?
}