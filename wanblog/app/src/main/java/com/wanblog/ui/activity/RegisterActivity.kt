package com.wanblog.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.wanblog.R
import com.wanblog.base.BaseActivity
import com.wanblog.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.include_toolbar.*

class RegisterActivity : BaseActivity<RegisterViewModel>() {

    override fun layoutId(): Int = R.layout.activity_register

    override fun initView(savedInstanceState: Bundle?) {

        iv_register_username_clear.setOnClickListener {
            et_register_username.setText("");
        }

        cb_register_password1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                et_register_password1.transformationMethod = HideReturnsTransformationMethod.getInstance();
            } else {
                et_register_password1.transformationMethod = PasswordTransformationMethod.getInstance();
            }
        }

        cb_register_password2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                et_register_password2.transformationMethod = HideReturnsTransformationMethod.getInstance();
            } else {
                et_register_password2.transformationMethod = PasswordTransformationMethod.getInstance();
            }
        }

        tv_register.setOnClickListener {
            val userName = et_register_username.text.toString()
            val password1 = et_register_password1.text.toString()
            val password2 = et_register_password2.text.toString()
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this@RegisterActivity, "账号不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password1)) {
                Toast.makeText(this@RegisterActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password2)) {
                Toast.makeText(this@RegisterActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password1 != password2) {
                Toast.makeText(this@RegisterActivity, "两次密码不一致", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mViewModel.signUp(userName, password1)
        }
    }

    override fun createObserver() {
        mViewModel.registerResult.observe(this, {
            if (it) {
                finish()
            }
        })
    }

}