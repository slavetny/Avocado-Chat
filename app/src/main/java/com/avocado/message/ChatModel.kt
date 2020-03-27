package com.avocado.chat

import com.avocado.chat.domain.entity.Message

class ChatModel : ChatContract.ChatModel {

//    val database = FirebaseDatabase.getInstance()

    override fun sendInRemoteStorage(message: Message) {
//        val myRef = database.getReference(message.type)
//
//        myRef.setValue(message.title)
    }
}