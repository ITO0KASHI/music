package com.example.musicserver.dao

import com.example.musicserver.domain.Comment
import org.springframework.stereotype.Repository

@Repository
interface CommentMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Comment?): Int

    fun insertSelective(record: Comment?): Int

    fun selectByPrimaryKey(id: Int?): Comment?

    fun updateByPrimaryKeySelective(record: Comment?): Int

    fun updateByPrimaryKey(record: Comment?): Int

    fun updateCommentMsg(record: Comment?): Int

    fun deleteComment(id: Int?): Int

    fun commentOfSongId(songId: Int?): List<Comment?>?

    fun commentOfSongListId(songListId: Int?): List<Comment?>?

}