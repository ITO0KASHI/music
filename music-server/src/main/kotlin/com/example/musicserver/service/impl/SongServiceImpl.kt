package com.example.musicserver.service.impl

import com.example.musicserver.dao.SongMapper
import com.example.musicserver.domain.Song
import com.example.musicserver.service.SongService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongServiceImpl:SongService{
    @Autowired
    lateinit var songMapper: SongMapper
    override fun addSong(song: Song?): Boolean {
        return songMapper.insertSelective(song) > 0
    }

    override fun updateSongMsg(song: Song?): Boolean {
        return songMapper.updateSongMsg(song) > 0
    }

    override fun updateSongUrl(song: Song?): Boolean {
        return songMapper.updateSongUrl(song) > 0
    }

    override fun updateSongPic(song: Song?): Boolean {
        return songMapper.updateSongPic(song) > 0
    }

    override fun deleteSong(id: Int?): Boolean {
        return songMapper.deleteSong(id) > 0
    }

    override fun allSong(): List<Song?>? {
        return songMapper.allSong()
    }

    override fun songOfSingerId(singerId: Int?): List<Song?>? {
        return songMapper.songOfSingerId(singerId)
    }

    override fun songOfId(id: Int?): List<Song?>? {
        return songMapper.songOfId(id)
    }

    override fun songOfSingerName(name: String?): List<Song?>? {
        return songMapper.songOfSingerName(name)
    }

}