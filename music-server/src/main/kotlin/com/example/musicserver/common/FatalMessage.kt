package com.example.musicserver.common

import com.alibaba.fastjson.JSONObject

class FatalMessage(message: String?) {
    private var jsonObject = JSONObject()

    init {
        jsonObject["code"] = 500
        jsonObject["message"] = message
        jsonObject["success"] = false
        jsonObject["type"] = "error"
        jsonObject["data"] = null
    }

    fun getMessage(): JSONObject {
        return jsonObject
    }
}