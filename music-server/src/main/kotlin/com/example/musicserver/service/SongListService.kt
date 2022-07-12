package com.example.musicserver.service

import com.example.musicserver.domain.SongList

interface SongListService {

    fun addSongList(songList: SongList?): Boolean

    fun updateSongListMsg(songList: SongList?): Boolean

    fun updateSongListImg(songList: SongList?): Boolean

    fun deleteSongList(id: Int?): Boolean

    fun allSongList(): List<SongList?>?

    fun likeTitle(title: String?): List<SongList?>?

    fun likeStyle(style: String?): List<SongList?>?

}