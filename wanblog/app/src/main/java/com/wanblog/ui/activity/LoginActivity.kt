package com.wanblog.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.ext.*
import com.wanblog.util.SettingUtil
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
        //设置颜色跟主题颜色一致
        appViewModel.appColor.value?.let {
            SettingUtil.setShapColor(loginSub, it)
            loginGoregister.setTextColor(it)
            toolbar.setBackgroundColor(it)
        }

    }

    override fun createObserver() {
        mLoginViewModel.loginResult.observe(this, Observer {
            UserUtil.saveToken(this, it.token)
            UserUtil.saveUserId(this, it.uid)
            UserUtil.saveUserName(this, it.username)
            appViewModel.userName.value = it.username
            finish()
        })
    }

    inner class ProxyClick {

        fun clear() {
            mViewModel.username.value = ""
        }

        fun login() {
            val userName = mViewModel.username.value
            val password = mViewModel.password.value
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

        fun goRegister() {
            hideSoftKeyboard(this@LoginActivity)
            startActivity<RegisterActivity>()
        }

        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.value = isChecked
            }
    }

}