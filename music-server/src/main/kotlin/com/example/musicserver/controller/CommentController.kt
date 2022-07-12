package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.domain.Comment
import com.example.musicserver.service.impl.CommentServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
class CommentController {
    @Autowired
    lateinit var commentService: CommentServiceImpl

    // 提交评论
    @ResponseBody
    @RequestMapping(value = ["/comment/add"], method = [RequestMethod.POST])
    fun addComment(req: HttpServletRequest): Any? {
        val user_id = req.getParameter("userId")
        val type = req.getParameter("type")
        val song_list_id = req.getParameter("songListId")
        val song_id = req.getParameter("songId")
        val content = req.getParameter("content").trim()

        val comment = Comment()
        comment.userId = user_id.toInt()
        comment.type = type.toByte()
        comment.content = content
        comment.createTime = Date()
        if(type.toInt() == 0){
            comment.songId = song_id.toInt()
        }else if (type.toInt() == 1)
            comment.songListId = song_list_id.toInt()
        val res = commentService.addComment(comment)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("评论成功").getMessage()
        }else{
            ErrorMessage("删除失败").getMessage()
        }
    }

    //删除评论
    @RequestMapping(value = ["/comment/delete"], method = [RequestMethod.GET])
    fun deleteComment(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        val res = commentService.deleteComment(id.toInt())
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("删除成功").getMessage()
        } else {
            ErrorMessage("删除失败").getMessage()
        }
    }

    // 获得指定歌曲 ID 的评论列表
    @RequestMapping(value = ["/comment/song/detail"], method = [RequestMethod.GET])
    fun commentOfSongId(req: HttpServletRequest): Any? {
        val songId = req.getParameter("songId")
        return SuccessMessage<List<Comment?>?>(null, commentService.commentOfSongId(songId.toInt())).getMessage()
    }

    // 获得指定歌单 ID 的评论列表
    @RequestMapping(value = ["/comment/songList/detail"], method = [RequestMethod.GET])
    fun commentOfSongListId(req: HttpServletRequest): Any? {
        val songListId = req.getParameter("songListId")
        return SuccessMessage<List<Comment?>?>(null, commentService.commentOfSongListId(songListId.toInt())).getMessage()
    }


    @ResponseBody
    @RequestMapping(value = ["/comment/like"], method = [RequestMethod.POST])
    fun commentOfLike(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val up = req.getParameter("up").trim()
        val comment = Comment()
        comment.id = id.toInt()
        comment.up = up.toInt()
        val res = commentService.updateCommentMsg(comment)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("点赞成功").getMessage()
        } else {
            ErrorMessage("点赞失败").getMessage()
        }
    }
}