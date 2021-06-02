package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.bean.UserInfo
import com.wanblog.ext.request
import com.wanblog.network.RequsetUtil
import com.wanblog.network.apiService
import org.json.JSONObject

class LoginViewModel : BaseViewModel() {

    var loginResult = MutableLiveData<UserInfo>()

    /**
     * 登录
     */
    fun login(username: String, password: String) {
        request({
            val jsonObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)
            val body = RequsetUtil.getRequestBody(jsonObject)
            apiService.login(body)
        }, {
            loginResult.value = it
        }, isShowDialog = true)
    }

}