package com.example.musicserver.service
import com.example.musicserver.domain.Comment

interface CommentService {
    fun addComment(comment: Comment?): Boolean

    fun updateCommentMsg(comment: Comment?): Boolean

    fun deleteComment(id: Int?): Boolean

    fun commentOfSongId(songId: Int?): List<Comment?>?

    fun commentOfSongListId(songListId: Int?): List<Comment?>?

}