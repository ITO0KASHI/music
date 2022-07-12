package com.example.musicserver.service

import com.example.musicserver.domain.Song

interface SongService {
    fun addSong(song: Song?): Boolean

    fun updateSongMsg(song: Song?): Boolean

    fun updateSongUrl(song: Song?): Boolean

    fun updateSongPic(song: Song?): Boolean

    fun deleteSong(id: Int?): Boolean

    fun allSong(): List<Song?>?

    fun songOfSingerId(singerId: Int?): List<Song?>?

    fun songOfId(id: Int?): List<Song?>?

    fun songOfSingerName(name: String?): List<Song?>?

}