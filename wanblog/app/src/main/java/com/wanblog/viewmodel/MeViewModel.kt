package com.wanblog.viewmodel

import com.wanblog.WanBlogApp
import com.wanblog.base.BaseViewModel
import com.wanblog.ext.request
import com.wanblog.network.apiService
import com.wanblog.util.UserUtil
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

class MeViewModel : BaseViewModel() {

    var userName = StringLiveData()
    var imageUrl = StringLiveData()

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