package com.remoteapi.nikhilkumar.remoteapi.controllers

import android.content.Context
import com.remoteapi.nikhilkumar.remoteapi.APIConstants
import com.remoteapi.nikhilkumar.remoteapi.utils.CJRSecureSharedPreferences

//TODO : Pass Application Context in this
class ScoreControllers(var ctx : Context) {

    private fun prepareScore(timeTaken : Long) {

    }

    private  var currScore = 0

    private fun editScore(score : Int){
        currScore++
        val prefs = CJRSecureSharedPreferences(ctx)
        val editor = prefs.edit()
        editor.putInt(APIConstants.CURRENT_LEVEL, score)
        editor.commit()
    }

    private fun getCurrScore() : Int{
        val prefs = CJRSecureSharedPreferences(ctx)
        return prefs.getInt(APIConstants.CURRENT_SCORE, 0)
    }

}