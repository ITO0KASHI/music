package com.example.musicserver.domain

import org.apache.commons.lang3.builder.ToStringBuilder


data class Admin(
    var id:Long,
    var name:String,
    var password:String
    )
{
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}