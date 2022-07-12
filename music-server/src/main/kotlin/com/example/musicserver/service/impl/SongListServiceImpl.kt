package com.example.musicserver.service.impl

import com.example.musicserver.dao.SongListMapper
import com.example.musicserver.domain.SongList
import com.example.musicserver.service.SongListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongListServiceImpl : SongListService {
    @Autowired
    lateinit var songListMapper: SongListMapper
    override fun addSongList(songList: SongList?): Boolean {
        return songListMapper.insertSelective(songList) > 0
    }

    override fun updateSongListMsg(songList: SongList?): Boolean {
        return songListMapper.updateSongListMsg(songList) > 0
    }

    override fun updateSongListImg(songList: SongList?): Boolean {
        return songListMapper.updateSongListImg(songList) > 0
    }

    override fun deleteSongList(id: Int?): Boolean {
        return songListMapper.deleteSongList(id) > 0
    }

    override fun allSongList(): List<SongList?>? {
        return songListMapper.allSongList()
    }

    override fun likeTitle(title: String?): List<SongList?>? {
        return songListMapper.likeTitle(title)
    }

    override fun likeStyle(style: String?): List<SongList?>? {
        return songListMapper.likeStyle(style)
    }
}