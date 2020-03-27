package com.avocado.chat

import com.avocado.chat.domain.entity.Message

interface ChatContract {

    interface ChatModel {

        fun sendInRemoteStorage(message: Message)

    }

    interface ChatPresenter {

        fun sendMessage(message: Message)

    }

    interface ChatView {

        fun loadMessages(messageList: ArrayList<Message>)

    }
}