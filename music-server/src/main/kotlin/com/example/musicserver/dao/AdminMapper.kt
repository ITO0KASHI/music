package com.example.musicserver.dao

import com.example.musicserver.domain.Admin
import org.springframework.stereotype.Repository

@Repository
interface AdminMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Admin?): Int

    fun insertSelective(record: Admin?): Int

    fun selectByPrimaryKey(id: Int?): Admin?

    fun updateByPrimaryKeySelective(record: Admin?): Int

    fun updateByPrimaryKey(record: Admin?): Int

    fun verifyPassword(username: String?, password: String?): Int
}

