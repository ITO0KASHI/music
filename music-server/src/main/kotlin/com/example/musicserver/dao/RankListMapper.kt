package com.example.musicserver.dao

import com.example.musicserver.domain.RankList
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface RankListMapper {

    fun insert(record: RankList?): Int

    fun insertSelective(record: RankList?): Int

    /**
     * 查总分
     * @param songListId
     * @return
     */
    fun selectScoreSum(songListId: Long?): Int

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    fun selectRankNum(songListId: Long?): Int

    /**
     * 查制定用户评分
     * @param consumerId
     * @param songListId
     * @return
     */
    fun selectUserRank(@Param("consumerId") consumerId: Long?, @Param("songListId") songListId: Long?): Int
}