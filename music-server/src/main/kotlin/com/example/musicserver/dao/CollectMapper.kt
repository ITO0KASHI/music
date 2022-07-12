package com.example.musicserver.dao

import com.example.musicserver.domain.Collect
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface CollectMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Collect?): Int

    fun insertSelective(record: Collect?): Int

    fun selectByPrimaryKey(id: Int?): Collect?

    fun updateByPrimaryKeySelective(record: Collect?): Int

    fun updateByPrimaryKey(record: Collect?): Int

    fun existSongId(@Param("userId") userId: Int?, @Param("songId") songId: Int?): Int

    fun deleteCollect(@Param("userId") userId: Int?, @Param("songId") songId: Int?): Int

    fun collectionOfUser(userId: Int?): List<Collect?>?

}