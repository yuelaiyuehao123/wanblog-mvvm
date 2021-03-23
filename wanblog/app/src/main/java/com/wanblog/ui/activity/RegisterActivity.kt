package com.wanblog.ui.activity

import android.os.Bundle
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.databinding.ActivityRegisterBinding
import com.wanblog.viewmodel.RegisterViewModel

class RegisterActivity  : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override fun layoutId(): Int = R.layout.activity_register

    override fun initView(savedInstanceState: Bundle?) {
    }

}