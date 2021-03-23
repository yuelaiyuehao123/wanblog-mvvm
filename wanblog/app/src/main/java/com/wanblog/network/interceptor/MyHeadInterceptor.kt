package com.wanblog.network.interceptor

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
        builder.addHeader(ApiSettings.tokenKey, UserUtil.getToken(WanBlogApp.instance)).build()
        builder.addHeader("device", "Android").build()
        return chain.proceed(builder.build())
    }

}