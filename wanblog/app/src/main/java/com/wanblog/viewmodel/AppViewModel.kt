package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.bean.UserInfo
import com.wanblog.ext.appContext
import com.wanblog.util.UserUtil

/**
 * 描述　:APP全局的ViewModel，可以存放公共数据，当他数据改变时，所有监听他的地方都会收到回调,也可以做发送消息
 * 比如 全局可使用的 地理位置信息，账户信息,App的基本配置等等，
 */
class AppViewModel : BaseViewModel() {

    var mUserInfo = MutableLiveData<UserInfo>()

    init {
        val user = UserUtil.getUser(appContext)
        mUserInfo.value = user
    }

}