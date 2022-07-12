package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.FatalMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.constant.Constants
import com.example.musicserver.domain.Singer
import com.example.musicserver.service.impl.SingerServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.File
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
class SingerController {
    @Autowired
    lateinit var singerService: SingerServiceImpl

    @Configuration
    class MyPicConfig : WebMvcConfigurer {
        override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
            registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations(Constants.SINGER_PIC_PATH)
        }
    }

    // 添加歌手
    @ResponseBody
    @RequestMapping(value = ["/singer/add"], method = [RequestMethod.POST])
    fun addSinger(req: HttpServletRequest): Any? {
        val name = req.getParameter("name").trim()
        val sex = req.getParameter("sex").trim()
        val birth = req.getParameter("birth").trim()
        val location = req.getParameter("location").trim()
        val introduction = req.getParameter("introduction").trim()
        val pic = "/img/avatorImages/user.jpg"
        val singer = Singer()
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var myBirth: Date? = Date()
        try {
            myBirth = dateFormat.parse(birth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        singer.name = name
        singer.sex = sex.toByte()
        singer.birth = myBirth
        singer.location = location
        singer.introduction = introduction
        singer.pic = pic
        val res = singerService.addSinger(singer)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("添加成功").getMessage()
        } else {
            ErrorMessage("添加失败").getMessage()
        }
    }

    // 删除歌手
    @RequestMapping(value = ["/singer/delete"], method = [RequestMethod.GET])
    fun deleteSinger(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        val res = singerService.deleteSinger(id.toInt())
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("删除成功").getMessage()
        } else {
            ErrorMessage("删除失败").getMessage()
        }
    }

    // 返回所有歌手
    @RequestMapping(value = ["/singer"], method = [RequestMethod.GET])
    fun allSinger(): Any? {
        return SuccessMessage<List<Singer?>?>(null, singerService.allSinger()).getMessage()
    }

    // 根据歌手名查找歌手
    @RequestMapping(value = ["/singer/name/detail"], method = [RequestMethod.GET])
    fun singerOfName(req: HttpServletRequest): Any? {
        val name = req.getParameter("name").trim()
        return SuccessMessage<List<Singer?>?>(null, singerService.singerOfName(name)).getMessage()
    }

    // 根据歌手性别查找歌手
    @RequestMapping(value = ["/singer/sex/detail"], method = [RequestMethod.GET])
    fun singerOfSex(req: HttpServletRequest): Any? {
        val sex = req.getParameter("sex").trim()
        return SuccessMessage<List<Singer?>?>(null, singerService.singerOfSex(sex.toInt())).getMessage()
    }

    // 更新歌手信息
    @ResponseBody
    @RequestMapping(value = ["/singer/update"], method = [RequestMethod.POST])
    fun updateSingerMsg(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val name = req.getParameter("name").trim()
        val sex = req.getParameter("sex").trim()
        val birth = req.getParameter("birth").trim()
        val location = req.getParameter("location").trim()
        val introduction = req.getParameter("introduction").trim()
        val singer = Singer()
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var myBirth: Date? = Date()
        try {
            myBirth = dateFormat.parse(birth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        singer.name = name
        singer.sex = sex.toByte()
        singer.birth = myBirth
        singer.location = location
        singer.introduction = introduction
        singer.id = id.toInt()
        val res = singerService.updateSingerMsg(singer)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("修改成功").getMessage()
        } else {
            ErrorMessage("修改失败").getMessage()
        }
    }

    // 更新歌手头像
    @ResponseBody
    @RequestMapping(value = ["/singer/avatar/update"], method = [RequestMethod.POST])
    fun updateSingerPic(@RequestParam("file") avatorFile: MultipartFile, @RequestParam("id") id: Int): Any? {
        val fileName = System.currentTimeMillis().toString() + avatorFile.originalFilename
        val filePath = (System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic")
        val file1 = File(filePath)
        if (!file1.exists()) {
            file1.mkdir()
        }
        val dest = File(filePath + System.getProperty("file.separator") + fileName)
        val imgPath = "/img/singerPic/$fileName"
        return try {
            avatorFile.transferTo(dest)
            val singer = Singer()
            singer.id = id
            singer.pic = imgPath
            val res = singerService.updateSingerPic(singer)
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