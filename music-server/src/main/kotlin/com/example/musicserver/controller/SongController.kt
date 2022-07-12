package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.FatalMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.constant.Constants
import com.example.musicserver.domain.Song
import com.example.musicserver.service.impl.SongServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import org.springframework.util.unit.DataUnit
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.File
import java.io.IOException
import java.util.*
import javax.servlet.MultipartConfigElement
import javax.servlet.http.HttpServletRequest

@RestController
class SongController {
    @Autowired
    lateinit var songService: SongServiceImpl

    @Bean
    fun multipartConfigElement(): MultipartConfigElement? {
        val factory = MultipartConfigFactory()
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES))
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES))
        return factory.createMultipartConfig()
    }

    @Configuration
    class MyPicConfig : WebMvcConfigurer {
        override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
            registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations(Constants.SONG_PIC_PATH)
            registry.addResourceHandler("/song/**")
                .addResourceLocations(Constants.SONG_PATH)
        }
    }

    // 添加歌曲
    @ResponseBody
    @RequestMapping(value = ["/song/add"], method = [RequestMethod.POST])
    fun addSong(req: HttpServletRequest, @RequestParam("file") mpfile: MultipartFile): Any? {
        val singer_id = req.getParameter("singerId").trim()
        val name = req.getParameter("name").trim()
        val introduction = req.getParameter("introduction").trim()
        val pic = "/img/songPic/tubiao.jpg"
        val lyric = req.getParameter("lyric").trim()
        val fileName = mpfile.originalFilename
        val filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song"
        val file1 = File(filePath)
        if (!file1.exists()) {
            file1.mkdir()
        }
        val dest = File(filePath + System.getProperty("file.separator") + fileName)
        val storeUrlPath = "/song/$fileName"
        return try {
            mpfile.transferTo(dest)
            val song = Song()
            song.singerId = singer_id.toInt()
            song.name = name
            song.introduction = introduction
            song.createTime = Date()
            song.updateTime = Date()
            song.pic = pic
            song.lyric = lyric
            song.url = storeUrlPath
            val res = songService.addSong(song)
            if (res) {
                SuccessMessage<String>("上传成功", storeUrlPath).getMessage()
            } else {
                ErrorMessage("上传失败").getMessage()
            }
        } catch (e: IOException) {
            FatalMessage("上传失败" + e.message).getMessage()
        }
    }

    // 删除歌曲
    @RequestMapping(value = ["/song/delete"], method = [RequestMethod.GET])
    fun deleteSong(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        val res = songService.deleteSong(id.toInt())
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("删除成功").getMessage()
        } else {
            ErrorMessage("删除失败").getMessage()
        }
    }

    // 返回所有歌曲
    @RequestMapping(value = ["/song"], method = [RequestMethod.GET])
    fun allSong(): Any? {
        return SuccessMessage<List<Song?>?>(null, songService.allSong()).getMessage()
    }

    // 返回指定歌曲ID的歌曲
    @RequestMapping(value = ["/song/detail"], method = [RequestMethod.GET])
    fun songOfId(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        return SuccessMessage<List<Song?>?>(null, songService.songOfId(id.toInt())).getMessage()
    }

    // 返回指定歌手ID的歌曲
    @RequestMapping(value = ["/song/singer/detail"], method = [RequestMethod.GET])
    fun songOfSingerId(req: HttpServletRequest): Any? {
        val singerId = req.getParameter("singerId")
        return SuccessMessage<List<Song?>?>(null, songService.songOfSingerId(singerId.toInt())).getMessage()
    }

    // 返回指定歌手名的歌曲
    @RequestMapping(value = ["/song/singerName/detail"], method = [RequestMethod.GET])
    fun songOfSingerName(req: HttpServletRequest): Any? {
        val name = req.getParameter("name")
        return SuccessMessage<List<Song?>?>(null, songService.songOfSingerName("%$name%")).getMessage()
    }

    // 更新歌曲信息
    @ResponseBody
    @RequestMapping(value = ["/song/update"], method = [RequestMethod.POST])
    fun updateSongMsg(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val singer_id = req.getParameter("singerId").trim()
        val name = req.getParameter("name").trim()
        val introduction = req.getParameter("introduction").trim()
        val lyric = req.getParameter("lyric").trim()
        val song = Song()
        song.id = id.toInt()
        song.singerId = singer_id.toInt()
        song.name = name
        song.introduction = introduction
        song.updateTime = Date()
        song.lyric = lyric
        val res = songService.updateSongMsg(song)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("修改成功").getMessage()
        } else {
            ErrorMessage("修改失败").getMessage()
        }
    }

    // 更新歌曲图片
    @ResponseBody
    @RequestMapping(value = ["/song/img/update"], method = [RequestMethod.POST])
    fun updateSongPic(@RequestParam("file") urlFile: MultipartFile, @RequestParam("id") id: Int): Any? {
        val fileName = System.currentTimeMillis().toString() + urlFile.originalFilename
        val filePath =
            System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic"
        val file1 = File(filePath)
        if (!file1.exists()) {
            file1.mkdir()
        }
        val dest = File(filePath + System.getProperty("file.separator") + fileName)
        val storeUrlPath = "/img/songPic/$fileName"
        return try {
            urlFile.transferTo(dest)
            val song = Song()
            song.id = id
            song.pic = storeUrlPath
            val res = songService.updateSongPic(song)
            if (res) {
                SuccessMessage<String>("上传成功", storeUrlPath).getMessage()
            } else {
                ErrorMessage("上传失败").getMessage()
            }
        } catch (e: IOException) {
            FatalMessage("上传失败" + e.message).getMessage()
        }
    }

    // 更新歌曲
    @ResponseBody
    @RequestMapping(value = ["/song/url/update"], method = [RequestMethod.POST])
    fun updateSongUrl(@RequestParam("file") urlFile: MultipartFile, @RequestParam("id") id: Int): Any? {
        val fileName = urlFile.originalFilename
        val filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song"
        val file1 = File(filePath)
        if (!file1.exists()) {
            file1.mkdir()
        }
        val dest = File(filePath + System.getProperty("file.separator") + fileName)
        val storeUrlPath = "/song/$fileName"
        return try {
            urlFile.transferTo(dest)
            val song = Song()
            song.id = id
            song.url = storeUrlPath
            val res = songService.updateSongUrl(song)
            if (res) {
                SuccessMessage<String>("更新成功", storeUrlPath).getMessage()
            } else {
                ErrorMessage("更新失败").getMessage()
            }
        } catch (e: IOException) {
            FatalMessage("更新失败" + e.message).getMessage()
        }
    }
}