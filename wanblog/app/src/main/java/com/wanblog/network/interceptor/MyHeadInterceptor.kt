package com.wanblog.network.interceptor

import android.text.TextUtils
import com.wanblog.WanBlogApp
import com.wanblog.network.ApiSettings
import com.wanblog.util.UserUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class MyHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val user = UserUtil.getUser(WanBlogApp.instance)
        val token = user?.token
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader(ApiSettings.tokenKey, token!!).build()
        }
        builder.addHeader("device", "Android").build()
        return chain.proceed(builder.build())
    }

}