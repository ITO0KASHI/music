package com.example.musicserver.service

interface AdminService {
    fun verifyPasswd(name:String, password:String):Boolean
}