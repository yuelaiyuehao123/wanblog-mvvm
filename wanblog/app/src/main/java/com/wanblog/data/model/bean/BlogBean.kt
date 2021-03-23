package com.wanblog.data.model.bean

data class BlogBean(
    val bid: Long,
    val uid: Long,
    val title: String,
    val description: String,
    val content: String,
    val created: String,
    val username: String,
    val avatar: String,
    val status: Int
)