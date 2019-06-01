package com.remoteapi.nikhilkumar.remoteapi.controllers

import android.content.Context
import android.content.SharedPreferences
import com.remoteapi.nikhilkumar.remoteapi.APIConstants
import com.remoteapi.nikhilkumar.remoteapi.utils.CJRSecureSharedPreferences

class LevelControllers(var ctx : Context) {

    private  var currLevel = 1

    private fun increaseLevel(){
        currLevel++
        val prefs = CJRSecureSharedPreferences(ctx)
        val editor = prefs.edit()
        editor.putInt(APIConstants.CURRENT_LEVEL, currLevel)
        editor.commit()
    }

    private fun getCurrLevel() : Int{
        val prefs = CJRSecureSharedPreferences(ctx)
        return prefs.getInt(APIConstants.CURRENT_LEVEL, 1)
    }
}