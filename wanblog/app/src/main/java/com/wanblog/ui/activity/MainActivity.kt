package com.wanblog.ui.activity

import android.os.Bundle
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createObserver() {
    }

}