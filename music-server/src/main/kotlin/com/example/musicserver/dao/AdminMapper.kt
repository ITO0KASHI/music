package com.example.musicserver.dao

import com.example.musicserver.domain.Admin
import org.apache.ibatis.annotations.Select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
interface AdminMapper {
    fun selectAdminById(id: String): Admin
    fun verifyPassword(name: String, password: String): Int
}

