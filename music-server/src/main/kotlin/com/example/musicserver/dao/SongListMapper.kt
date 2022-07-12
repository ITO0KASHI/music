package com.example.musicserver.dao

import com.example.musicserver.domain.SongList
import org.springframework.stereotype.Repository

@Repository
interface SongListMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: SongList?): Int

    fun insertSelective(record: SongList?): Int

    fun selectByPrimaryKey(id: Int?): SongList?

    fun updateByPrimaryKeySelective(record: SongList?): Int

    fun updateByPrimaryKeyWithBLOBs(record: SongList?): Int

    fun updateByPrimaryKey(record: SongList?): Int

    fun updateSongListMsg(record: SongList?): Int

    fun updateSongListImg(record: SongList?): Int

    fun deleteSongList(id: Int?): Int

    fun allSongList(): List<SongList?>?

    fun likeTitle(title: String?): List<SongList?>?

    fun likeStyle(style: String?): List<SongList?>?
}