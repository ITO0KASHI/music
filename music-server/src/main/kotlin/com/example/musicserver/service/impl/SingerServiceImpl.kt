package com.example.musicserver.service.impl

import com.example.musicserver.dao.SingerMapper
import com.example.musicserver.domain.Singer
import com.example.musicserver.service.SingerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SingerServiceImpl: SingerService {
    @Autowired
    lateinit var singerMapper: SingerMapper
    override fun addSinger(singer: Singer?): Boolean {
        return singerMapper.insertSelective(singer) > 0
    }

    override fun updateSingerMsg(singer: Singer?): Boolean {
        return singerMapper.updateSingerMsg(singer) > 0
    }

    override fun updateSingerPic(singer: Singer?): Boolean {
        return singerMapper.updateSingerPic(singer) > 0
    }

    override fun deleteSinger(id: Int?): Boolean {
        return singerMapper.deleteSinger(id) > 0
    }

    override fun allSinger(): List<Singer?>? {
        return singerMapper.allSinger()
    }

    override fun singerOfName(name: String?): List<Singer?>? {
        return singerMapper.singerOfName(name)
    }

    override fun singerOfSex(sex: Int?): List<Singer?>? {
        return singerMapper.singerOfSex(sex)
    }


}