package com.wanblog.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.CompoundButton
import android.widget.Toast
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class RegisterActivity : BaseActivity<RegisterViewModel>() {

    override fun layoutId(): Int = R.layout.activity_register

    override fun initView(savedInstanceState: Bundle?) {

    }

    inner class ProxyClick {
        /**清空*/
        fun clear() {
            mViewModel.username.value = ""
        }

        /**注册*/
        fun register() {
            val userName = mViewModel.username.value
            val password1 = mViewModel.password1.value
            val password2 = mViewModel.password1.value
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this@RegisterActivity, "账号不能为空", Toast.LENGTH_SHORT).show()
                return
            }
            if (TextUtils.isEmpty(password1)) {
                Toast.makeText(this@RegisterActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
                return
            }
            if (TextUtils.isEmpty(password2)) {
                Toast.makeText(this@RegisterActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
                return
            }
            if (password1 != password2) {
                Toast.makeText(this@RegisterActivity, "两次密码不一致", Toast.LENGTH_SHORT).show()
                return
            }
            mViewModel.signUp(userName!!, password1!!)
        }

        var onCheckedChangeListener1 =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd1.value = isChecked
            }

        var onCheckedChangeListener2 =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd2.value = isChecked
            }

    }

    override fun createObserver() {
    }

}