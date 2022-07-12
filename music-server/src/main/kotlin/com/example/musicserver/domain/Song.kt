/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2022-07-10T16:22:32.2334316+08:00
 */
package com.example.musicserver.domain

import java.util.Date

data class Song(
    var id: Int? = null,
    var singerId: Int? = null,
    var name: String? = null,
    var introduction: String? = null,
    var createTime: Date? = null,
    var updateTime: Date? = null,
    var pic: String? = null,
    var url: String? = null,
    var lyric: String? = null
)