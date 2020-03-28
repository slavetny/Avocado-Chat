package com.avocado.chat.domain.entity

import android.net.Uri

data class Message (
    val text: String = "",
    val image: Uri? = null,
    val type: String
)