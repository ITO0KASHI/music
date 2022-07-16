package com.example.musicserver.constant

import com.example.musicserver.constant.Constants.PROJECT_PATH

object Constants {
    /* 歌曲图片，歌手图片，歌曲文件，歌单图片等文件的存放路径 */
    var PROJECT_PATH: String = System.getProperty("user.dir")
    var AVATOR_IMAGES_PATH = "file:$PROJECT_PATH/img/avatorImages/"
    var SONGLIST_PIC_PATH = "file:$PROJECT_PATH/img/songListPic/"
    var SONG_PIC_PATH = "file:$PROJECT_PATH/img/songPic/"
    var SONG_PATH = "file:$PROJECT_PATH/song/"
    var SINGER_PIC_PATH = "file:$PROJECT_PATH/img/singerPic/"
}

