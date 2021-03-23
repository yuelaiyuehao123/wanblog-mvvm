package com.wanblog.data.model.bean

/**
 * 用户信息
 */
data class UserInfo(
    val uid: Long,
    val username: String,
    val avatar: String,
    val token: String
)
