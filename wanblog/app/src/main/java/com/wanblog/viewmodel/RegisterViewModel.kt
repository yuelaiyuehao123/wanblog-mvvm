package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.ext.request
import com.wanblog.network.RequsetUtil
import com.wanblog.network.apiService
import me.hgj.jetpackmvvm.callback.livedata.BooleanLiveData
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData
import org.json.JSONObject

class RegisterViewModel : BaseViewModel() {

    // 用户名
    var username = StringLiveData()

    // 密码1
    var password1 = StringLiveData()

    //是否显示明文密码（登录注册界面）
    var isShowPwd1 = BooleanLiveData()

    // 密码2
    var password2 = StringLiveData()


    //是否显示明文密码（登录注册界面）
    var isShowPwd2 = BooleanLiveData()

    // 注册结果
    var registerResult = MutableLiveData<Boolean>()

    /**
     * 注册
     */
    fun signUp(username: String, password: String) {
        request({
            val jsonObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)
            val body = RequsetUtil.getRequestBody(jsonObject)
            apiService.signUp(body)
        }, {
            registerResult.value = true
        }, isShowDialog = true)
    }

}