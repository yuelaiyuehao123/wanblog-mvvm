package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.WanBlogApp
import com.wanblog.base.BaseViewModel
import com.wanblog.bean.UserInfo
import com.wanblog.ext.request
import com.wanblog.network.apiService
import com.wanblog.util.UserUtil

class MeViewModel : BaseViewModel() {

    var logoutResult = MutableLiveData<Boolean>()

    fun logout() {
        request({
            apiService.logout()
        }, {
            logoutResult.value = true
        }, isShowDialog = true)
    }

}