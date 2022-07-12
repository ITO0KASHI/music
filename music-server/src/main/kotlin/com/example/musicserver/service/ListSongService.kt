package com.example.musicserver.service

import com.example.musicserver.domain.ListSong

interface ListSongService {
    fun addListSong(listSong: ListSong?): Boolean

    fun updateListSongMsg(listSong: ListSong?): Boolean

    fun deleteListSong(songId: Int?): Boolean

    fun allListSong(): List<ListSong?>?

    fun listSongOfSongId(songListId: Int?): List<ListSong?>?

}