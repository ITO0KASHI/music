package com.example.musicserver.controller

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

    // 添加收藏的歌曲
    // 添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = ["/collection/add"], method = [RequestMethod.POST])
    open fun addCollection(req: HttpServletRequest): Any? {
        val user_id = req.getParameter("userId")
        val type = req.getParameter("type")
        val song_id = req.getParameter("songId")
        val song_list_id = req.getParameter("songListId")
        val collect = Collect()
        collect.userId = user_id.toInt()
        collect.type = type.toByte()
        if (type.toInt() == 0) {
            collect.songId = song_id.toInt()
        } else if (type.toInt() == 1) {
            collect.songListId = song_list_id.toInt()
        }
        collect.createTime = Date()
        val res = collectService.addCollection(collect)
        return if (res) {
            SuccessMessage("收藏成功", true).getMessage()
        } else {
            ErrorMessage("收藏失败").getMessage()
        }
    }

    // 是否收藏歌曲
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

    // 返回的指定用户 ID 收藏的列表
    @RequestMapping(value = ["/collection/detail"], method = [RequestMethod.GET])
    fun collectionOfUser(req: HttpServletRequest): Any? {
        val userId = req.getParameter("userId")
        return SuccessMessage<List<Collect?>?>("取消收藏", collectService.collectionOfUser(userId.toInt())).getMessage()
    }

    // 取消收藏的歌曲
    @RequestMapping(value = ["/collection/delete"], method = [RequestMethod.DELETE])
    fun deleteCollection(req: HttpServletRequest): Any? {
        val user_id = req.getParameter("userId").trim()
        val song_id = req.getParameter("songId").trim()
        val res = collectService.deleteCollect(user_id.toInt(), song_id.toInt())
        return if (res) {
            SuccessMessage("取消收藏", false).getMessage()
        } else {
            ErrorMessage("取消收藏失败").getMessage()
        }
    }
}