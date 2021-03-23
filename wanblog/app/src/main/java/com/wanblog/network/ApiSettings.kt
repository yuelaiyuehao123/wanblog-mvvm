package com.wanblog.network

object ApiSettings {

    //token的key
    const val tokenKey = "Authorization"

    //注册
    const val signUp = "api/user/signUp"

    //登录
    const val login = "api/user/login"

    //退出登录
    const val logout = "api/user/logout"

    //首页博客列表
    const val blog_list = "api/blog/list"

    //发布博客前三名列表
    const val blog_top3_list = "api/blog/top3List"

    //博客详情
    const val blog = "api/blog/{bid}"

    //编辑博客
    const val blog_edit = "api/blog/edit"

    //发布博客
    const val blog_publish = "api/blog/publish"

    //删除博客
    const val blog_delete = "api/blog/delete"

}