package com.shawn.githubdemo

import android.app.Application
import android.content.Context

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