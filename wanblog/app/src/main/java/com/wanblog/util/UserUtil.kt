package com.wanblog.util

import android.content.Context
import android.text.TextUtils

object UserUtil {

    private val TOKEN: String = "token"
    private val USER_NAME: String = "user_name"
    private val USER_ID: String = "user_id"
    private val USER_IMAGE: String = "user_image"

    private val IS_FIRST: String = "is_first"

    fun isFirst(context: Context): Boolean {
        return SharedPreferencesUtil.getInstance(context).getBoolean(IS_FIRST)
    }

    /**
     * 保存是否第一次打开过APP
     */
    fun saveIsFirst(context: Context, isFirst: Boolean) {
        SharedPreferencesUtil.getInstance(context).putBoolean(IS_FIRST, isFirst)
    }

    fun isLogin(context: Context): Boolean {
        val token = getToken(context);
        return !TextUtils.isEmpty(token);
    }

    /**
     * 保存Token
     */
    fun saveToken(context: Context, token: String) {
        SharedPreferencesUtil.getInstance(context).putString(TOKEN, token)
    }

    /**
     * 得到Token
     */
    fun getToken(context: Context): String =
        SharedPreferencesUtil.getInstance(context).getString(TOKEN)

    /**
     * 得到用户名
     */
    fun getUserName(context: Context): String =
        SharedPreferencesUtil.getInstance(context).getString(USER_NAME)

    /**
     * 保存用户名
     */
    fun saveUserName(context: Context, name: String) {
        SharedPreferencesUtil.getInstance(context).putString(USER_NAME, name)
    }

    /**
     * 得到用户名头像
     */
    fun getUserImage(context: Context): String {
//        return    SharedPreferencesUtil.getInstance(context).getString(USER_IMAGE)
        return ColorUtil.randomImage()
    }


    /**
     * 保存用户名头像
     */
    fun saveUserImage(context: Context, imageUrl: String) {
        SharedPreferencesUtil.getInstance(context).putString(USER_IMAGE, imageUrl)
    }


    /**
     * 得到用户id
     */
    fun getUserId(context: Context): Long =
        SharedPreferencesUtil.getInstance(context).getLong(USER_ID)

    /**
     * 保存用户id
     */
    fun saveUserId(context: Context, id: Long) {
        SharedPreferencesUtil.getInstance(context).putLong(USER_ID, id)
    }

    fun logout(context: Context) {
        val isFirst = isFirst(context)
        SharedPreferencesUtil.getInstance(context).clearSp()
        saveIsFirst(context, isFirst)
    }
}