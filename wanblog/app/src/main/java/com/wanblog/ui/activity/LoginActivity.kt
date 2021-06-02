package com.wanblog.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.wanblog.R
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseActivity
import com.wanblog.ext.*
import com.wanblog.util.UserUtil
import com.wanblog.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity<LoginViewModel>() {

    //请求数据ViewModel
    private val mLoginViewModel: LoginViewModel by viewModels()

    override fun layoutId(): Int = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {

        toolbar.initClose("登录") {
            finish()
        }

        tv_go_login.setOnClickListener {
            login()
        }

        tv_go_register.setOnClickListener {
            goRegister()
        }

    }

    override fun createObserver() {
        mLoginViewModel.loginResult.observe(this, Observer {
            UserUtil.saveUser(WanBlogApp.instance, it)
            appViewModel.mUserInfo.value = it
            finish()
        })
    }

    private fun login() {
        val userName = et_username.text.toString()
        val password = et_password.text.toString()
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this@LoginActivity, "账号不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this@LoginActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        mViewModel.login(userName, password)
    }

    private fun goRegister() {
        hideSoftKeyboard(this@LoginActivity)
        startActivity<RegisterActivity>()
    }

}