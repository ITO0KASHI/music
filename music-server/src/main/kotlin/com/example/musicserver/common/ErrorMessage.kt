package com.example.musicserver.common

import com.alibaba.fastjson.JSONObject


class ErrorMessage(message: String?) {
    private var jsonObject = JSONObject()
    init{
        jsonObject["code"] = 200
        jsonObject["message"] = message
        jsonObject["success"] = false
        jsonObject["type"] = "error"
        jsonObject["data"] = null
    }

    fun getMessage(): JSONObject {
        return jsonObject
    }
}