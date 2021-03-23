package com.wanblog.network

import com.wanblog.data.model.bean.ApiResponse
import com.wanblog.data.model.bean.BlogBean
import com.wanblog.data.model.bean.UserInfo
import com.wanblog.model.bean.Top3Bean
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    companion object {
        const val SERVER_URL = "http://www.yuelaiyuehao.top"
    }

    @GET(ApiSettings.blog_top3_list)
    suspend fun getTop3List(): ApiResponse<ArrayList<Top3Bean>>

    @GET(ApiSettings.blog_list)
    suspend fun getBlogList(
        @Query("currentPage") currentPage: Int, @Query("size") size: Int
    ): ApiResponse<ArrayList<BlogBean>>

    @POST(ApiSettings.login)
    suspend fun login(@Body body: RequestBody): ApiResponse<UserInfo>

    @GET(ApiSettings.logout)
    suspend fun logout(): ApiResponse<Any>

}