package com.example.musicserver.service

import com.example.musicserver.domain.RankList

interface RankListService {
    fun addRank(rankList: RankList?): Boolean

    fun rankOfSongListId(songListId: Long?): Int

    fun getUserRank(consumerId: Long?, songListId: Long?): Int

}