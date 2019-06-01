package com.remoteapi.nikhilkumar.remoteapi.controllers

import android.os.Handler
import android.os.HandlerThread



class TimerRefreshThread(var threadName : String) : HandlerThread(threadName) {

    lateinit var mUiHandler : Handler
    var uiEventCallback : Callback ? = null
    var workerHandler : Handler ?= null


    fun initThread(uiHandler: Handler , callBack : Callback) {
        mUiHandler = uiHandler
        uiEventCallback = callBack
    }

    interface  Callback {
        fun onSecondComplete()
        fun onPauseTimer()
        fun onTimerComplete()
    }

    fun postTasks(task : Runnable) {
        workerHandler?.post(task)
        //on Completion of this Runnable. use ui handler to post seconds on ui
    }


    override fun onLooperPrepared() {
        super.onLooperPrepared()
        if(looper != null) {
            workerHandler = Handler(looper)
        }
    }

}