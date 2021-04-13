package com.wanblog.ui.activity

import android.os.Bundle
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.viewmodel.SettingViewModel

class SettingActivity : BaseActivity<SettingViewModel>() {

    override fun layoutId(): Int = R.layout.activity_setting

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createObserver() {
    }

}