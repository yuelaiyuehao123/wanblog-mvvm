package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseViewModel
import com.wanblog.ext.request
import com.wanblog.network.apiService
import com.wanblog.util.UserUtil

class MeViewModel : BaseViewModel() {

    var userName = MutableLiveData<String>()
    var imageUrl = MutableLiveData<String>()

    fun logout() {
        request({
            apiService.logout()
        }, {
            userName.value = "请登录"
            imageUrl.value = ""
            UserUtil.logout(WanBlogApp.instance)
        }, isShowDialog = true)
    }

}