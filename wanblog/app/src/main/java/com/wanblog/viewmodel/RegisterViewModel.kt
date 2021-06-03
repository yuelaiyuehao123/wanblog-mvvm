package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.ext.request
import com.wanblog.network.RequsetUtil
import com.wanblog.network.apiService
import org.json.JSONObject

class RegisterViewModel : BaseViewModel() {

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