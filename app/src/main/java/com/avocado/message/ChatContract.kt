package com.avocado.chat

import com.avocado.chat.domain.entity.Message

interface ChatContract {

    interface ChatModel {

        interface OnMessagesFinished {

            fun onMessagesFinished(messageList: ArrayList<Message>)

            fun onMessagesFailure()

        }

        fun sendInRemoteStorage(message: Message)

        fun getByRemoteStorage(onFinished: OnMessagesFinished)

    }

    interface ChatPresenter {

        fun sendMessage(message: Message)

        fun getMessages()

    }

    interface ChatView {

        fun loadMessages(messageList: ArrayList<Message>)

    }
}