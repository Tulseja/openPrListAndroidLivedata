package com.remoteapi.nikhilkumar.remoteapi.responsePOJO

import com.google.gson.annotations.SerializedName

data class OpenPRResponse(var openPrList : List<PRObject> ? = null)

data class PRObject(@SerializedName("title") var prTitle : String ? = null,
                    @SerializedName("user") var userObj : User ? = null)

data class User(@SerializedName("login") var loginName : String ? = null,
                @SerializedName("avatar_url") var imgUrl : String ? = null)