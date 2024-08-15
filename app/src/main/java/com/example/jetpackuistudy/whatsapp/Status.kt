package com.example.jetpackuistudy.whatsapp

import java.io.File

data class Status(val file: File, val type: StatusType)

enum class StatusType {
    IMAGE, VIDEO
}