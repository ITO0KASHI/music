package com.example.musicserver.service.impl

import com.example.musicserver.dao.ListSongMapper
import com.example.musicserver.domain.ListSong
import com.example.musicserver.service.ListSongService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ListSongServiceImpl: ListSongService {
    @Autowired
    lateinit var listSongMapper: ListSongMapper
    override fun addListSong(listSong: ListSong?): Boolean {
        return listSongMapper.insertSelective(listSong) > 0
    }

    override fun updateListSongMsg(listSong: ListSong?): Boolean {
        return listSongMapper.updateListSongMsg(listSong) > 0
    }

    override fun deleteListSong(songId: Int?): Boolean {
        return listSongMapper.deleteListSong(songId) > 0
    }

    override fun allListSong(): List<ListSong?>? {
        return listSongMapper.allListSong()
    }

    override fun listSongOfSongId(songListId: Int?): List<ListSong?>? {
        return listSongMapper.listSongOfSongId(songListId)
    }

}