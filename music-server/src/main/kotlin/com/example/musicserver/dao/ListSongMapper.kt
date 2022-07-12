package com.example.musicserver.dao

import com.example.musicserver.domain.ListSong
import org.springframework.stereotype.Repository

@Repository
interface ListSongMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: ListSong?): Int

    fun insertSelective(record: ListSong?): Int

    fun selectByPrimaryKey(id: Int?): ListSong?

    fun updateByPrimaryKeySelective(record: ListSong?): Int

    fun updateByPrimaryKey(record: ListSong?): Int

    fun updateListSongMsg(record: ListSong?): Int

    fun deleteListSong(songId: Int?): Int

    fun allListSong(): List<ListSong?>?

    fun listSongOfSongId(songListId: Int?): List<ListSong?>?
}