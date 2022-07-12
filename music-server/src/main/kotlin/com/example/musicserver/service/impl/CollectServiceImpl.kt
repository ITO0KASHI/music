package com.example.musicserver.service.impl

import com.example.musicserver.dao.CollectMapper
import com.example.musicserver.domain.Collect
import com.example.musicserver.service.CollectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CollectServiceImpl: CollectService {
    @Autowired
    lateinit var collectMapper: CollectMapper
    override fun addCollection(collect: Collect?): Boolean {
        return collectMapper.insertSelective(collect) > 0
    }

    override fun existSongId(userId: Int?, songId: Int?): Boolean {
        return collectMapper.existSongId(userId, songId) > 0
    }

    override fun deleteCollect(userId: Int?, songId: Int?): Boolean {
        return collectMapper.deleteCollect(userId, songId) > 0
    }

    override fun collectionOfUser(userId: Int?): List<Collect?>? {
        return collectMapper.collectionOfUser(userId)
    }
}
