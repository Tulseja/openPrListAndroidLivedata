package com.remoteapi.nikhilkumar.remoteapi.repo

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log

import android.webkit.URLUtil
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.remoteapi.nikhilkumar.remoteapi.utils.Resource
import java.util.HashMap
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.PRObject



class RemoteDataSource(private val appContext: Context){

    companion object {
        var DEFAULT_PAGE_NUM =1
        fun getInstance(appContext: Application): RemoteDataSource {
            return RemoteDataSource(appContext)
        }
    }

    private fun isPaginationState(pageNum: Int) = pageNum > DEFAULT_PAGE_NUM




}