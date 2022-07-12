package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.domain.ListSong
import com.example.musicserver.service.impl.ListSongServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class ListSongController {
    @Autowired
    lateinit var listSongService: ListSongServiceImpl

    // 给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = ["/listSong/add"], method = [RequestMethod.POST])
    fun addListSong(req: HttpServletRequest): Any? {
        val song_id = req.getParameter("songId").trim()
        val song_list_id = req.getParameter("songListId").trim()
        val listsong = ListSong()
        listsong.songId = song_id.toInt()
        listsong.songListId = song_list_id.toInt()
        val res = listSongService.addListSong(listsong)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("添加成功").getMessage()
        } else {
            ErrorMessage("添加失败").getMessage()
        }
    }

    // 删除歌单里的歌曲
    @RequestMapping(value = ["/listSong/delete"], method = [RequestMethod.GET])
    fun deleteListSong(req: HttpServletRequest): Any? {
        val songId = req.getParameter("songId")
        val res = listSongService.deleteListSong(songId.toInt())
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("删除成功").getMessage()
        } else {
            ErrorMessage("删除失败").getMessage()
        }
    }

    // 返回歌单里指定歌单 ID 的歌曲
    @RequestMapping(value = ["/listSong/detail"], method = [RequestMethod.GET])
    fun listSongOfSongId(req: HttpServletRequest): Any? {
        val songListId = req.getParameter("songListId")
        val listSong = listSongService.listSongOfSongId(songListId.toInt())
        return SuccessMessage<List<ListSong?>?>("添加成功", listSong).getMessage()
    }

    // 更新歌单里面的歌曲信息
    @ResponseBody
    @RequestMapping(value = ["/listSong/update"], method = [RequestMethod.POST])
    fun updateListSongMsg(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val song_id = req.getParameter("songId").trim()
        val song_list_id = req.getParameter("songListId").trim()
        val listsong = ListSong()
        listsong.id = id.toInt()
        listsong.songId = song_id.toInt()
        listsong.songListId = song_list_id.toInt()
        val res = listSongService.updateListSongMsg(listsong)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("修改成功").getMessage()
        } else {
            ErrorMessage("修改失败").getMessage()
        }
    }
}