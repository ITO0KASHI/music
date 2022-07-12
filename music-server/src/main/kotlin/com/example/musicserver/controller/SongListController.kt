package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.FatalMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.constant.Constants
import com.example.musicserver.domain.SongList
import com.example.musicserver.service.impl.SongListServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.File
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
class SongListController {
    @Autowired
    lateinit var songListService:SongListServiceImpl

    @Configuration
    class MyPicConfig : WebMvcConfigurer {
        override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
            registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations(Constants.SONGLIST_PIC_PATH)
        }
    }

    // 添加歌单
    @ResponseBody
    @RequestMapping(value = ["/songList/add"], method = [RequestMethod.POST])
    fun addSongList(req: HttpServletRequest): Any? {
        val title = req.getParameter("title").trim()
        val introduction = req.getParameter("introduction").trim()
        val style = req.getParameter("style").trim()
        val pic = "/img/songListPic/123.jpg"
        val songList = SongList()
        songList.title = title
        songList.introduction = introduction
        songList.style = style
        songList.pic = pic
        val res = songListService.addSongList(songList)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("添加成功").getMessage()
        } else {
            ErrorMessage("添加失败").getMessage()
        }
    }

    // 删除歌单
    @RequestMapping(value = ["/songList/delete"], method = [RequestMethod.GET])
    fun deleteSongList(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        val res = songListService.deleteSongList(id.toInt())
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("删除成功").getMessage()
        } else {
            ErrorMessage("删除失败").getMessage()
        }
    }

    // 返回所有歌单
    @RequestMapping(value = ["/songList"], method = [RequestMethod.GET])
    fun allSongList(): Any? {
        return SuccessMessage<List<SongList?>?>(null, songListService.allSongList()).getMessage()
    }

    // 返回标题包含文字的歌单
    @RequestMapping(value = ["/songList/likeTitle/detail"], method = [RequestMethod.GET])
    fun songListOfLikeTitle(req: HttpServletRequest): Any? {
        val title = req.getParameter("title").trim()
        return SuccessMessage<List<SongList?>?>(null, songListService.likeTitle("%$title%")).getMessage()
    }

    // 返回指定类型的歌单
    @RequestMapping(value = ["/songList/style/detail"], method = [RequestMethod.GET])
    fun songListOfStyle(req: HttpServletRequest): Any? {
        val style = req.getParameter("style").trim()
        return SuccessMessage<List<SongList?>?>(null, songListService.likeStyle("%$style%")).getMessage()
    }

    // 更新歌单信息
    @ResponseBody
    @RequestMapping(value = ["/songList/update"], method = [RequestMethod.POST])
    fun updateSongListMsg(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val title = req.getParameter("title").trim()
        val introduction = req.getParameter("introduction").trim()
        val style = req.getParameter("style").trim()
        val songList = SongList()
        songList.id = id.toInt()
        songList.title = title
        songList.introduction = introduction
        songList.style = style
        val res = songListService.updateSongListMsg(songList)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("修改成功").getMessage()
        } else {
            ErrorMessage("修改失败").getMessage()
        }
    }

    // 更新歌单图片
    @ResponseBody
    @RequestMapping(value = ["/songList/img/update"], method = [RequestMethod.POST])
    fun updateSongListPic(@RequestParam("file") avatorFile: MultipartFile, @RequestParam("id") id: Int): Any? {
        val fileName = System.currentTimeMillis().toString() + avatorFile.originalFilename
        val filePath =
            System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic"
        val file1 = File(filePath)
        if (!file1.exists()) {
            file1.mkdir()
        }
        val dest = File(filePath + System.getProperty("file.separator") + fileName)
        val imgPath = "/img/songListPic/$fileName"
        return try {
            avatorFile.transferTo(dest)
            val songList = SongList()
            songList.id = id
            songList.pic = imgPath
            val res = songListService.updateSongListImg(songList)
            if (res) {
                SuccessMessage<String>("上传成功", imgPath).getMessage()
            } else {
                ErrorMessage("上传失败").getMessage()
            }
        } catch (e: IOException) {
            FatalMessage("上传失败" + e.message).getMessage()
        }
    }
}