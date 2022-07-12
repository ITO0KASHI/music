package com.example.musicserver.dao

import com.example.musicserver.domain.Song
import org.springframework.stereotype.Repository

@Repository
interface SongMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Song?): Int

    fun insertSelective(record: Song?): Int

    fun selectByPrimaryKey(id: Int?): Song?

    fun updateByPrimaryKeySelective(record: Song?): Int

    fun updateByPrimaryKeyWithBLOBs(record: Song?): Int

    fun updateByPrimaryKey(record: Song?): Int

    fun updateSongMsg(record: Song?): Int

    fun updateSongUrl(record: Song?): Int

    fun updateSongPic(record: Song?): Int

    fun deleteSong(id: Int?): Int

    fun allSong(): List<Song?>?

    fun songOfSingerId(singerId: Int?): List<Song?>?

    fun songOfId(id: Int?): List<Song?>?

    fun songOfSingerName(name: String?): List<Song?>?
}