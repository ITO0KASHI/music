package com.example.musicserver.service

import com.example.musicserver.domain.Singer

interface SingerService {
    fun addSinger(singer: Singer?): Boolean

    fun updateSingerMsg(singer: Singer?): Boolean

    fun updateSingerPic(singer: Singer?): Boolean

    fun deleteSinger(id: Int?): Boolean

    fun allSinger(): List<Singer?>?

    fun singerOfName(name: String?): List<Singer?>?

    fun singerOfSex(sex: Int?): List<Singer?>?

}