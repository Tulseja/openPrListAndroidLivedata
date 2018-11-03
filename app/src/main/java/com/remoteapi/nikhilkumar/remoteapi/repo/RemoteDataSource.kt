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
import com.remoteapi.nikhilkumar.remoteapi.APIConstants
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.MyContestAPIElement

import com.remoteapi.nikhilkumar.remoteapi.utils.Resource
import java.util.HashMap
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.remoteapi.nikhilkumar.remoteapi.responsePOJO.ZomatoAPIResponse


class RemoteDataSource(private val appContext: Context){

    companion object {
        var DEFAULT_PAGE_NUM =1
        fun getInstance(appContext: Application): RemoteDataSource {
            return RemoteDataSource(appContext)
        }
    }

    private fun isPaginationState(pageNum: Int) = pageNum > DEFAULT_PAGE_NUM

    fun getMyContestDetails(pageNum : Int , pageSize : Int) : LiveData<Resource<List<MyContestAPIElement>>> {
        val data = MutableLiveData<Resource<List<MyContestAPIElement>>>()
        data.value = Resource.loading(isPaginatedLoading = isPaginationState(pageNum))

        val url = "http://stgapi.cricplay.com/cric/user/test/27243041-46a3-4588-9445-9b71d24da6b9/contest?page=$pageNum&size=$pageSize"

        if (!URLUtil.isValidUrl(url)) {
            data.value = Resource.error(VolleyError("Url is Invalid"))
        } else {
            val queue = Volley.newRequestQueue(appContext)

            val getRequest: StringRequest = object : StringRequest(Request.Method.GET, url, Response.Listener {
                // response
                val gson = Gson()
                val myContestListType = object : TypeToken<List<MyContestAPIElement>>() {}.type
                val listLiveData : List<MyContestAPIElement> = gson.fromJson(it, myContestListType)
                Log.d("AK",it)
                data.value = Resource.success(listLiveData)

            }, Response.ErrorListener {
                data.value = Resource.error(it)
            }
            ) {
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


    private fun getHeaderForAPI(): Map<String, String?> {
        val header = HashMap<String, String?>()
//        header[APIConstants.AUTHORIZATION] = APIConstants.AUTH_VALUE
        header[APIConstants.USER_KEY] = APIConstants.USER_KEY_VALUE
        header[APIConstants.CONTENT_TYPE] = APIConstants.CONTENT_TYPE_VALUE
        return header
    }

    fun getZomatoAPiResponse(pageNum : Int , pageSize : Int) : LiveData<Resource<ZomatoAPIResponse>> {
        val data = MutableLiveData<Resource<ZomatoAPIResponse>>()
        data.value = Resource.loading(isPaginatedLoading = isPaginationState(pageNum))

        val url = "https://developers.zomato.com/api/v2.1/search?entity_type=city&collection_id=1&entity_id=280"

        if (!URLUtil.isValidUrl(url)) {
            data.value = Resource.error(VolleyError("Url is Invalid"))
        } else {
            val queue = Volley.newRequestQueue(appContext)

            val getRequest: StringRequest = object : StringRequest(Request.Method.GET, url, Response.Listener {
                // response
                val gson = Gson()
                /*val myContestListType = object : TypeToken<List<MyContestAPIElement>>() {}.type
                val listLiveData : List<MyContestAPIElement> = gson.fromJson(it, myContestListType)*/
                val apiResponse : ZomatoAPIResponse = gson.fromJson(it,ZomatoAPIResponse::class.java)
                Log.d("AK",it)
                data.value = Resource.success(apiResponse)

            }, Response.ErrorListener {
                data.value = Resource.error(it)
            }
            ) {
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

    fun getZomatoAPiResponse(query : String) : LiveData<Resource<ZomatoAPIResponse>> {
        val data = MutableLiveData<Resource<ZomatoAPIResponse>>()
        data.value = Resource.loading()


        val url = "https://developers.zomato.com/api/v2.1/search?q=$query"

        if (!URLUtil.isValidUrl(url)) {
            data.value = Resource.error(VolleyError("Url is Invalid"))
        } else {
            val queue = Volley.newRequestQueue(appContext)

            val getRequest: StringRequest = object : StringRequest(Request.Method.GET, url, Response.Listener {
                // response
                val gson = Gson()
                /*val myContestListType = object : TypeToken<List<MyContestAPIElement>>() {}.type
                val listLiveData : List<MyContestAPIElement> = gson.fromJson(it, myContestListType)*/
                val apiResponse : ZomatoAPIResponse = gson.fromJson(it,ZomatoAPIResponse::class.java)
                Log.d("AK",it)
                data.value = Resource.success(apiResponse)

            }, Response.ErrorListener {
                data.value = Resource.error(it)
            }
            ) {
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