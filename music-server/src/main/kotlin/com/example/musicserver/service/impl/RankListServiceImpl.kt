package com.example.musicserver.service.impl

import com.example.musicserver.dao.RankListMapper
import com.example.musicserver.domain.RankList
import com.example.musicserver.service.RankListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RankListServiceImpl: RankListService {
    @Autowired
    lateinit var rankListMapper: RankListMapper
    override fun addRank(rankList: RankList?): Boolean {
        return rankListMapper.insertSelective(rankList) > 0
    }

    override fun rankOfSongListId(songListId: Long?): Int {

        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        val rankNum: Int = rankListMapper.selectRankNum(songListId)
        return if (rankNum <= 0) 0 else rankListMapper.selectScoreSum(songListId) / rankNum
    }

    override fun getUserRank(consumerId: Long?, songListId: Long?): Int {
        return rankListMapper.selectUserRank(consumerId, songListId)
    }
}