package com.wanblog.network

import com.wanblog.bean.ApiResponse
import com.wanblog.bean.BlogBean
import com.wanblog.bean.UserInfo
import com.wanblog.bean.Top3Bean
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    companion object {
        const val SERVER_URL = "http://www.yuelaiyuehao.top"
    }

    @POST(ApiSettings.signUp)
    suspend fun signUp(@Body body: RequestBody): ApiResponse<Any>

    @POST(ApiSettings.login)
    suspend fun login(@Body body: RequestBody): ApiResponse<UserInfo>

    @GET(ApiSettings.logout)
    suspend fun logout(): ApiResponse<Any>

    @GET(ApiSettings.blog_top3_list)
    suspend fun getTop3List(): ApiResponse<ArrayList<Top3Bean>>

    @GET(ApiSettings.blog_list)
    suspend fun getBlogList(
        @Query("currentPage") currentPage: Int, @Query("size") size: Int
    ): ApiResponse<ArrayList<BlogBean>>

}