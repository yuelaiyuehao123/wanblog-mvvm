package com.wanblog

import androidx.multidex.MultiDex
import com.wanblog.base.BaseApp

class WanBlogApp : BaseApp() {

    companion object {
        lateinit var instance: WanBlogApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
    }

}