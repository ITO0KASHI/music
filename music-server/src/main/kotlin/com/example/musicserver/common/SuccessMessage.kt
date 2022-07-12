package com.example.musicserver.common

import com.alibaba.fastjson.JSONObject

class SuccessMessage<T> {
    private var jsonObject = JSONObject()

    constructor(message: String?){
        jsonObject["code"] = 200
        jsonObject["message"] = message
        jsonObject["success"] = true
        jsonObject["type"] = "success"
        jsonObject["data"] = null
    }

    constructor(message: String?, data: T?) {
        jsonObject["code"] = 200
        jsonObject["message"] = message
        jsonObject["success"] = true
        jsonObject["type"] = "success"
        jsonObject["data"] = data
    }

    fun getMessage(): JSONObject {
        return jsonObject
    }
}