package com.wanblog.ui.activity

import android.os.Bundle
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.base.BaseViewModel

class CollectActivity : BaseActivity<BaseViewModel>() {

    override fun layoutId(): Int = R.layout.activity_collect

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createObserver() {
    }

}