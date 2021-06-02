package com.wanblog.util

import com.google.gson.Gson

object GsonUtil {

    fun <T> json2bean(result: String, clazz: Class<T>): T? {
        val gson = Gson()
        return gson.fromJson(result, clazz)
    }

    fun bean2json(any: Any?): String {
        var s = ""
        try {
            val gson = Gson()
            s = gson.toJson(any)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return s
    }

}