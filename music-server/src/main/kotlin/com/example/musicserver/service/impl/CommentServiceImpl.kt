package com.example.musicserver.service.impl

import com.example.musicserver.dao.CommentMapper
import com.example.musicserver.domain.Comment
import com.example.musicserver.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl:CommentService {
    @Autowired
    lateinit var commentMapper: CommentMapper
    override fun addComment(comment: Comment?): Boolean {
        return commentMapper.insertSelective(comment) > 0
    }

    override fun updateCommentMsg(comment: Comment?): Boolean {
        return commentMapper.updateCommentMsg(comment) > 0
    }

    override fun deleteComment(id: Int?): Boolean {
        return commentMapper.deleteComment(id) > 0
    }

    override fun commentOfSongId(songId: Int?): List<Comment?>? {
        return commentMapper.commentOfSongId(songId)
    }

    override fun commentOfSongListId(songListId: Int?): List<Comment?>? {
        return commentMapper.commentOfSongListId(songListId)
    }


}