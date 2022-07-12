package com.example.musicserver.service

import com.example.musicserver.domain.Consumer

interface ConsumerService {
    fun addUser(consumer: Consumer?): Boolean

    fun updateUserMsg(consumer: Consumer?): Boolean

    fun updateUserAvator(consumer: Consumer?): Boolean

    fun updatePassword(consumer: Consumer?): Boolean

    fun existUser(username: String?): Boolean

    fun verityPasswd(username: String?, password: String?): Boolean

    fun deleteUser(id: Int?): Boolean

    fun allUser(): List<Consumer?>?

    fun userOfId(id: Int?): List<Consumer?>?

    fun loginStatus(username: String?): List<Consumer?>?

}