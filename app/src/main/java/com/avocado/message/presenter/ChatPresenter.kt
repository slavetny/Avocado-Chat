package com.avocado.chat.presenter

import com.avocado.chat.ChatContract
import com.avocado.chat.ChatModel
import com.avocado.chat.domain.entity.Message

class ChatPresenter(val view: ChatContract.ChatView) : ChatContract.ChatPresenter {

    private var chatModel: ChatContract.ChatModel? = null

    override fun sendMessage(message: Message) {

        chatModel = ChatModel()

        chatModel?.sendInRemoteStorage(message)
    }
}