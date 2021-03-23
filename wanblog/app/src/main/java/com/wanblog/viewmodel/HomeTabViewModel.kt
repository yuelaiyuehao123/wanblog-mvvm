package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.data.model.bean.BlogBean
import com.wanblog.ext.request
import com.wanblog.model.bean.Top3Bean
import com.wanblog.network.apiService

class HomeTabViewModel : BaseViewModel() {

    private val mSize = 10
    private var mCurrentPage = 1

    // 首页banner
    var top3ListLiveData: MutableLiveData<ArrayList<Top3Bean>> = MutableLiveData()

    // 首页文章列表
    var blogListLiveData: MutableLiveData<ArrayList<BlogBean>> = MutableLiveData()

    // 获取首页文章列表的时候，是否是下拉刷新
    var isRefreshLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getTop3List() {
        request({
            apiService.getTop3List()
        }, {
            top3ListLiveData.value = it
        })
    }

    fun getBlogList(isRefresh: Boolean) {
        isRefreshLiveData.value = isRefresh

        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        request({ apiService.getBlogList(mCurrentPage, mSize) }, {
            blogListLiveData.value = it
        })
    }

}
