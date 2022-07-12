package com.example.musicserver.service.impl

import com.example.musicserver.dao.ConsumerMapper
import com.example.musicserver.domain.Consumer
import com.example.musicserver.service.ConsumerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConsumerServiceImpl : ConsumerService {
    @Autowired
    lateinit var consumerMapper: ConsumerMapper
    override fun addUser(consumer: Consumer?): Boolean {
        return consumerMapper.insertSelective(consumer) > 0
    }

    override fun updateUserMsg(consumer: Consumer?): Boolean {
        return consumerMapper.updateUserMsg(consumer) > 0
    }

    override fun updateUserAvator(consumer: Consumer?): Boolean {
        return consumerMapper.updateUserAvator(consumer) > 0
    }

    override fun updatePassword(consumer: Consumer?): Boolean {
        return consumerMapper.updatePassword(consumer) > 0
    }

    override fun existUser(username: String?): Boolean {
        return consumerMapper.existUsername(username) > 0
    }

    override fun verityPasswd(username: String?, password: String?): Boolean {
        return consumerMapper.verifyPassword(username, password) > 0
    }

    override fun deleteUser(id: Int?): Boolean {
        return consumerMapper.deleteUser(id) > 0
    }

    override fun allUser(): List<Consumer?>? {
        return consumerMapper.allUser()
    }

    override fun userOfId(id: Int?): List<Consumer?>? {
        return consumerMapper.userOfId(id)
    }

    override fun loginStatus(username: String?): List<Consumer?>? {
        return consumerMapper.loginStatus(username)
    }

}