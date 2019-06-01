package com.remoteapi.nikhilkumar.remoteapi.controllers

import android.os.CountDownTimer



object TimeController {

    var cTimer: CountDownTimer? = null


    fun startTimer(millisInFuture : Long  , countDownInterval : Long) {
        cTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {

            }
        }
        cTimer?.start()
    }


    fun cancelTimer() {
        cTimer?.cancel()
    }

    fun pauseTimer() {

    }

    fun resumeTimer(){

    }


}