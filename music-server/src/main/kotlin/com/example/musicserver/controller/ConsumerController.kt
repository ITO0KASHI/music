package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.FatalMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.common.WarningMessage
import com.example.musicserver.constant.Constants
import com.example.musicserver.domain.Consumer
import com.example.musicserver.service.impl.ConsumerServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.dao.DuplicateKeyException
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
import javax.servlet.http.HttpSession

@RestController
class ConsumerController {
    @Autowired
    lateinit var consumerService: ConsumerServiceImpl

    @Configuration
    class MyPicConfig : WebMvcConfigurer {
        override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
            registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations(Constants.AVATOR_IMAGES_PATH)
        }
    }

    //用户注册
    @ResponseBody
    @RequestMapping(value = ["/user/add"], method = [RequestMethod.POST])
    open fun addUser(req: HttpServletRequest): Any? {
        val username = req.getParameter("username").trim()
        val password = req.getParameter("password").trim()
        val sex = req.getParameter("sex").trim()
        val phone_num = req.getParameter("phone_num").trim()
        val email = req.getParameter("email").trim()
        val birth = req.getParameter("birth").trim()
        val introduction = req.getParameter("introduction").trim()
        val location = req.getParameter("location").trim()
        val avator = "/img/avatorImages/user.jpg"
        if (consumerService.existUser(username)) {
            return WarningMessage("用户名已注册").getMessage()
        }
        val consumer = Consumer()
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var myBirth: Date? = Date()
        try {
            myBirth = dateFormat.parse(birth)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        consumer.username = username
        consumer.password = password
        consumer.sex = sex.toByte()
        if ("" == phone_num) {
            consumer.phoneNum = null
        } else {
            consumer.phoneNum = phone_num
        }
        if ("" == email) {
            consumer.email = null
        } else {
            consumer.email = email
        }
        consumer.birth = myBirth
        consumer.introduction = introduction
        consumer.location = location
        consumer.avator = avator
        consumer.createTime = Date()
        consumer.updateTime = Date()
        return try {
            val res = consumerService.addUser(consumer)
            if (res) {
                SuccessMessage<ObjectUtils.Null>("注册成功").getMessage()
            } else {
                ErrorMessage("注册失败").getMessage()
            }
        } catch (e: DuplicateKeyException) {
            FatalMessage(e.message).getMessage()
        }
    }

    /**
     * 登录判断
     */
    @ResponseBody
    @RequestMapping(value = ["/user/login/status"], method = [RequestMethod.POST])
    fun loginStatus(req: HttpServletRequest, session: HttpSession): Any? {
        val username = req.getParameter("username")
        val password = req.getParameter("password")
        val res: Boolean = consumerService.verityPasswd(username, password)
        return if (res) {
            session.setAttribute("username", username)
            SuccessMessage<List<Consumer?>?>("登录成功", consumerService.loginStatus(username)).getMessage()
        } else {
            ErrorMessage("用户名或密码错误").getMessage()
        }
    }
    
    //返回所有用户
    @RequestMapping(value = ["/user"], method = [RequestMethod.GET])
    open fun allUser(): Any? {
        return SuccessMessage<List<Consumer?>?>(null, consumerService.allUser()).getMessage()
    }

    /**
     * 返回指定 ID 的用户
     */
    @RequestMapping(value = ["/user/detail"], method = [RequestMethod.GET])
    fun userOfId(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        return SuccessMessage<List<Consumer?>?>(null, consumerService.userOfId(id.toInt())).getMessage()
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = ["/user/delete"], method = [RequestMethod.GET])
    fun deleteUser(req: HttpServletRequest): Any? {
        val id = req.getParameter("id")
        val res = consumerService.deleteUser(id.toInt())
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("删除成功").getMessage()
        } else {
            ErrorMessage("删除失败").getMessage()
        }
    }

    /**
     * 更新用户信息
     */
    @ResponseBody
    @RequestMapping(value = ["/user/update"], method = [RequestMethod.POST])
    fun updateUserMsg(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val username = req.getParameter("username").trim()
        val sex = req.getParameter("sex").trim()
        val phone_num = req.getParameter("phone_num").trim()
        val email = req.getParameter("email").trim()
        val birth = req.getParameter("birth").trim()
        val introduction = req.getParameter("introduction").trim()
        val location = req.getParameter("location").trim()
        val consumer = Consumer()
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var myBirth: Date? = Date()
        try {
            myBirth = dateFormat.parse(birth)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        consumer.id = id.toInt()
        consumer.username = username
        consumer.sex = sex.toByte()
        consumer.phoneNum = phone_num
        consumer.email = email
        consumer.introduction = introduction
        consumer.location = location
        consumer.updateTime = Date()
        consumer.birth = myBirth
        val res = consumerService.updateUserMsg(consumer)
        return if (res) {
            SuccessMessage<ObjectUtils.Null>("修改成功").getMessage()
        } else {
            ErrorMessage("修改失败").getMessage()
        }
    }

    /**
     * 更新用户密码
     */
    @ResponseBody
    @RequestMapping(value = ["/user/updatePassword"], method = [RequestMethod.POST])
    fun updatePassword(req: HttpServletRequest): Any? {
        val id = req.getParameter("id").trim()
        val username = req.getParameter("username").trim()
        val old_password = req.getParameter("old_password").trim()
        val password = req.getParameter("password").trim()
        val res: Boolean = consumerService.verityPasswd(username, old_password)
        if (!res) {
            return ErrorMessage("密码输入错误").getMessage()
        }
        val consumer = Consumer()
        consumer.id = id.toInt()
        consumer.password = password
        val result = consumerService.updatePassword(consumer)
        return if (result) {
            SuccessMessage<ObjectUtils.Null>("密码修改成功").getMessage()
        } else {
            ErrorMessage("密码修改失败").getMessage()
        }
    }

    /**
     * 更新用户头像
     */
    @ResponseBody
    @RequestMapping(value = ["/user/avatar/update"], method = [RequestMethod.POST])
    fun updateUserPic(@RequestParam("file") avatorFile: MultipartFile, @RequestParam("id") id: Int): Any? {
        val fileName = System.currentTimeMillis().toString() + avatorFile.originalFilename
        val filePath =
            Constants.PROJECT_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages"
        val file1 = File(filePath)
        if (!file1.exists()) {
            file1.mkdir()
        }
        val dest = File(filePath + System.getProperty("file.separator") + fileName)
        val imgPath = "/img/avatorImages/$fileName"
        return try {
            avatorFile.transferTo(dest)
            val consumer = Consumer()
            consumer.id = id
            consumer.avator = imgPath
            val res = consumerService.updateUserAvator(consumer)
            if (res) {
                SuccessMessage("上传成功", imgPath).getMessage()
            } else {
                ErrorMessage("上传失败").getMessage()
            }
        } catch (e: IOException) {
            FatalMessage("上传失败" + e.message).getMessage()
        }
    }
}