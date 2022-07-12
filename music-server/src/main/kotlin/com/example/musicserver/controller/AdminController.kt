package com.example.musicserver.controller

import com.example.musicserver.common.ErrorMessage
import com.example.musicserver.common.SuccessMessage
import com.example.musicserver.service.impl.AdminServiceImpl
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@RestController
@Controller
class AdminController {


    @Autowired
    lateinit var adminService: AdminServiceImpl

    @ResponseBody
    @RequestMapping(value = ["/admin/login/status"], method = [RequestMethod.POST])
    fun loginStatus(req: HttpServletRequest, session: HttpSession): Any? {
        val name = req.getParameter("name")
        val password = req.getParameter("password")
        val res: Boolean = adminService.verifyPasswd(name, password)
        return if (res) {
            session.setAttribute("name", name)
            SuccessMessage<ObjectUtils.Null>("登录成功").getMessage()
        } else {
            ErrorMessage("用户名或密码错误").getMessage()
        }
    }
}
