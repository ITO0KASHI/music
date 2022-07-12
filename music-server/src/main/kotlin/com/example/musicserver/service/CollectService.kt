package com.example.musicserver.service

import com.example.musicserver.domain.Collect

interface CollectService {

    fun addCollection(collect: Collect?): Boolean

    fun existSongId(userId: Int?, songId: Int?): Boolean

    fun deleteCollect(userId: Int?, songId: Int?): Boolean

    fun collectionOfUser(userId: Int?): List<Collect?>?
}