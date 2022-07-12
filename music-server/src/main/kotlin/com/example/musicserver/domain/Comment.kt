/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2022-07-10T16:22:32.2214307+08:00
 */
package com.example.musicserver.domain

import java.util.Date

data class Comment(
    var id: Int? = null,
    var userId: Int? = null,
    var songId: Int? = null,
    var songListId: Int? = null,
    var content: String? = null,
    var createTime: Date? = null,
    var type: Byte? = null,
    var up: Int? = null
)