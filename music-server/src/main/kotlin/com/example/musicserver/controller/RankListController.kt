package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.domain.RankList
import com.example.musicserver.service.impl.RankListServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class RankListController {
    @Autowired
    lateinit var rankListService: RankListServiceImpl

    // 提交评分
    @ResponseBody
    @RequestMapping(value = ["/rankList/add"], method = [RequestMethod.POST])
    fun addRank(req: HttpServletRequest): Any? {
        val songListId = req.getParameter("songListId").trim()
        val consumerId = req.getParameter("consumerId").trim()
        val score = req.getParameter("score").trim()
        val rank_list = RankList()
        rank_list.songListId = songListId.toLong()
        rank_list.consumerId = consumerId.toLong()
        rank_list.score = score.toInt()
        val res = rankListService.addRank(rank_list)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("评价成功").getMessage()
        } else {
            ErrorMessage("评价失败").getMessage()
        }
    }

    // 获取指定歌单的评分
    @RequestMapping(value = ["/rankList"], method = [RequestMethod.GET])
    fun rankOfSongListId(req: HttpServletRequest): Any? {
        val songListId = req.getParameter("songListId")
        return SuccessMessage<Number>(null, rankListService.rankOfSongListId(songListId.toLong())).getMessage()
    }

    // 获取指定用户的歌单评分
    @RequestMapping(value = ["/rankList/user"], method = [RequestMethod.GET])
    fun getUserRank(req: HttpServletRequest): Any? {
        val consumerId = req.getParameter("consumerId")
        val songListId = req.getParameter("songListId")
        return SuccessMessage<Number>(
            null,
            rankListService.getUserRank(consumerId.toLong(), songListId.toLong())
        ).getMessage()
    }
}