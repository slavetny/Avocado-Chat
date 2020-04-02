package com.avocado.chat.domain.entity

import android.net.Uri

data class Message(
    val type: String = "",
    val text: String = "",
    val image: Uri? = null
)