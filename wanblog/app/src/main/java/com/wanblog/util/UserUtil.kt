package com.wanblog.util

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.wanblog.bean.UserInfo

object UserUtil {

    private val USER: String = "user"
    private val IS_FIRST: String = "is_first"

    /**
     * 判断是否第一次打开APP
     */
    fun isFirst(context: Context): Boolean {
        return SharedPreferencesUtil.getInstance(context).getBoolean(IS_FIRST)
    }

    /**
     * 保存是否第一次打开过APP
     */
    fun saveIsFirst(context: Context, isFirst: Boolean) {
        SharedPreferencesUtil.getInstance(context).putBoolean(IS_FIRST, isFirst)
    }

    /**
     * 保存用户信息
     */
    fun saveUser(context: Context, userInfo: UserInfo?) {
        val userInfoStr = GsonUtil.bean2json(userInfo)
        SharedPreferencesUtil.getInstance(context).putString(USER, userInfoStr)
    }

    /**
     * 得到用户信息
     */
    fun getUser(context: Context): UserInfo? {
        val userInfoStr = SharedPreferencesUtil.getInstance(context).getString(USER)
        return GsonUtil.json2bean(userInfoStr, UserInfo::class.java)
    }

    /**
     * 判断是否登录
     */
    fun isLogin(context: Context): Boolean {
        val user = getUser(context)
        return user != null
    }

}