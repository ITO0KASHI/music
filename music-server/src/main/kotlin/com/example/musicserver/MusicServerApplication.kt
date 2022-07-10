package com.example.musicserver

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@MapperScan("com.example.musicserver.dao")
@SpringBootApplication
class MusicServerApplication

fun main(args: Array<String>) {
    runApplication<MusicServerApplication>(*args)
}
