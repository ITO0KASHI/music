package com.example.musicserver.controller

import com.example.musicserver.service.impl.AdminServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import com.alibaba.fastjson.JSONObject

@RestController
@Controller
class AdminController {
    @Autowired
    lateinit var adminService: AdminServiceImpl

    @ResponseBody
    @RequestMapping(value = ["/admin/login/status"], method = [RequestMethod.POST])
    public fun loginStatus(req: HttpServletRequest, session: HttpSession): JSONObject {
        val jsonObject = JSONObject()

        val name = req.getParameter("name")
        val password = req.getParameter("password")

        val res: Boolean = adminService.verifyPasswd(name, password)
        return if (res) {
            jsonObject["code"] = 1
            jsonObject["msg"] = "登录成功"
            session.setAttribute("name", name)
            jsonObject
        } else {
            jsonObject["code"] = 0
            jsonObject["msg"] = "用户名或密码错误"
            jsonObject
        }
    }
}
