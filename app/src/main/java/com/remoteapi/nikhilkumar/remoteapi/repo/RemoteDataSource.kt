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

    private fun getHeaderForAPI(): Map<String, String?> {
        val header = HashMap<String, String?>()
//        header[APIConstants.AUTHORIZATION] = APIConstants.AUTH_VALUE
        /*header[APIConstants.USER_KEY] = APIConstants.USER_KEY_VALUE
        header[APIConstants.CONTENT_TYPE] = APIConstants.CONTENT_TYPE_VALUE*/
        return header
    }


    fun getOpenPrList(pageNum: Int,pageSize : Int,repoName : String) : LiveData<Resource<List<PRObject>>>{
        val data = MutableLiveData<Resource<List<PRObject>>>()
        data.value = Resource.loading(isPaginatedLoading =  isPaginationState(pageNum))

        val url = "https://api.github.com/repos/$repoName/pulls?page=$pageNum&per_page=$pageSize"

        if (!URLUtil.isValidUrl(url)) {
            data.value = Resource.error(VolleyError("Url is Invalid"),isPaginationState(pageNum))
        } else {
            val queue = Volley.newRequestQueue(appContext)

            val getRequest: StringRequest = object : StringRequest(Request.Method.GET, url, Response.Listener {
                // response
                val gson = Gson()
                val myContestListType = object : TypeToken<List<PRObject>>() {}.type
                val listLiveData : List<PRObject> = gson.fromJson(it, myContestListType)
                Log.d("AK",it)
                data.value = Resource.success(listLiveData,isPaginationState(pageNum))

            }, Response.ErrorListener {
                data.value = Resource.error(it,isPaginationState(pageNum))
            })

             {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String?> {
                    val params = getHeaderForAPI()
                    return params
                }
            }
            queue.add(getRequest);
        }
        return data

    }



}