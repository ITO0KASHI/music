package com.example.musicserver.controller

import com.alibaba.fastjson.JSONObject
import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.domain.Collect
import com.example.musicserver.service.impl.CollectServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
class CollectController {
    @Autowired
    lateinit var collectService: CollectServiceImpl

    @ResponseBody
    @RequestMapping(value = ["/collection/add"], method = [RequestMethod.POST])
    public fun addCollection(req: HttpServletRequest): Any? {
        val user_id = req.getParameter("userId").toInt()
        val type = req.getParameter("type").toByte()
        val song_id = req.getParameter("songId").toInt()
        val song_list_id = req.getParameter("songListId").toInt()
        val collect = Collect()
        collect.userId = user_id
        collect.type = type
        if(type.toInt() == 0){
            collect.songId = song_id
        }else if(type.toInt() == 1){
            collect.songListId = song_list_id
        }
        collect.createTime  = Date()
        val res = collectService.addCollection(collect)
        if(res){
            return SuccessMessage<Boolean>("收藏成功", true).getMessage()
        } else {
            return ErrorMessage("收藏失败").getMessage()
        }
    }

    @RequestMapping(value = ["/collection/status"], method = [RequestMethod.POST])
    fun isCollection(req: HttpServletRequest): Any? {
        val user_id = req.getParameter("userId").trim()
        val song_id = req.getParameter("songId").trim()
        val res = collectService.existSongId(user_id.toInt(), song_id.toInt())
        return if (res) {
            SuccessMessage("已收藏", true).getMessage()
        } else {
            SuccessMessage("未收藏", false).getMessage()
        }
    }

    @RequestMapping(value = ["/collection/detail"], method = [RequestMethod.GET])
    fun collectionOfUser(req: HttpServletRequest): Any? {
        val userId = req.getParameter("userId")
        return SuccessMessage<List<Collect?>?>("取消收藏", collectService.collectionOfUser(userId.toInt())).getMessage()
    }
}