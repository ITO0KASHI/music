package com.example.musicserver.dao

import com.example.musicserver.domain.Consumer
import org.springframework.stereotype.Repository

@Repository
interface ConsumerMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Consumer?): Int

    /**
     * 增加新用户
     * @param record - 用户信息
     * @return int
     */
    fun insertSelective(record: Consumer?): Int

    fun selectByPrimaryKey(id: Int?): Consumer?

    fun updateByPrimaryKeySelective(record: Consumer?): Int

    fun updateByPrimaryKey(record: Consumer?): Int

    fun verifyPassword(username: String?, password: String?): Int

    fun existUsername(username: String?): Int

    fun addUser(consumer: Consumer?): Int

    fun updateUserMsg(record: Consumer?): Int

    fun updateUserAvator(record: Consumer?): Int

    fun updatePassword(record: Consumer?): Int

    fun deleteUser(id: Int?): Int

    fun allUser(): List<Consumer?>?

    fun userOfId(id: Int?): List<Consumer?>?

    fun loginStatus(username: String?): List<Consumer?>?

}