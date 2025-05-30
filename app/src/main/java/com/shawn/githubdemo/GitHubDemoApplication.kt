package com.shawn.githubdemo

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitHubDemoApplication :Application(){
    init{
        instance = this
    }

    companion object{
        private var instance : GitHubDemoApplication? = null
        fun applicationContext():Context{
            return instance!!.applicationContext
        }
    }
}