package com.example.musicserver.service.impl

import com.example.musicserver.dao.AdminMapper
import com.example.musicserver.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl : AdminService{
    @Autowired
    lateinit var adminMapper:AdminMapper
    override fun verifyPasswd(name: String, password: String): Boolean {
        return adminMapper.verifyPassword(name, password) > 0
    }
}
