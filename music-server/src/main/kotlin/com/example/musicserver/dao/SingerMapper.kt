package com.example.musicserver.dao

import com.example.musicserver.domain.Singer
import org.springframework.stereotype.Repository

@Repository
interface SingerMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Singer?): Int

    fun insertSelective(record: Singer?): Int

    fun selectByPrimaryKey(id: Int?): Singer?

    fun updateByPrimaryKeySelective(record: Singer?): Int

    fun updateByPrimaryKey(record: Singer?): Int

    fun updateSingerMsg(record: Singer?): Int

    fun updateSingerPic(record: Singer?): Int

    fun deleteSinger(id: Int?): Int

    fun allSinger(): List<Singer?>?

    fun singerOfName(name: String?): List<Singer?>?

    fun singerOfSex(sex: Int?): List<Singer?>?
}