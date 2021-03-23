package com.wanblog.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wanblog.base.BaseViewModel
import com.wanblog.ext.request
import com.wanblog.network.apiService

class HomeViewModel : BaseViewModel() {

    var titleData: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun getTabData() {
        request({ apiService.getBlogList(1, 10) }, {
            val arrayList = arrayListOf(
                "Java",
                "Kotlin",
                "Swift",
                "Go",
                "PHP",
                "Python",
                "C",
                "C++",
                "HTML",
                "JavaScript",
                "CSS",
                "C#"
            )
            titleData.value = arrayList
        })
    }

}