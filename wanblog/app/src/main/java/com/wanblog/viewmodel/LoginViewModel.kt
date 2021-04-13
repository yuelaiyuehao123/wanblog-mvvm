package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.data.model.bean.UserInfo
import com.wanblog.ext.request
import com.wanblog.network.RequsetUtil
import com.wanblog.network.apiService
import me.hgj.jetpackmvvm.callback.livedata.BooleanLiveData
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData
import org.json.JSONObject

class LoginViewModel : BaseViewModel() {

    //用户名
    var username = StringLiveData()
    //密码
    var password = StringLiveData()
    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanLiveData()
    //
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