package com.rpn.totel.model

import java.io.Serializable
import java.util.Date

data class Message(
    val createdAt: Long, var deliveryTime: Long = 0L,
    var seenTime: Long = 0L,
    val from: String, val to: String,
    val senderName: String,
    val senderImage: String?=null,
    var type: String = "text",//0=text,1=audio,2=image,3=video,4=file
    var status: Int = 0,//0=sending,1=sent,2=delivered,3=seen,4=failed
    var textMessage: TextMessage? = null,
    var imageMessage: ImageMessage? = null,
    var audioMessage: AudioMessage? = null,
    var videoMessage: VideoMessage? = null,
    var fileMessage: FileMessage? = null,
    var chatUsers: ArrayList<String>? = null,
    var chatUserId: String? = null
) : Serializable{

}

data class TextMessage(val text: String? = null) : Serializable

data class AudioMessage(var uri: String? = null, val duration: Int = 0) : Serializable

data class ImageMessage(var uri: String? = null, var imageType: String = "image") : Serializable

data class VideoMessage(val uri: String? = null, val duration: Int = 0) : Serializable

data class FileMessage(
    val name: String? = null,
    val uri: String? = null, val duration: Int = 0
) : Serializable
