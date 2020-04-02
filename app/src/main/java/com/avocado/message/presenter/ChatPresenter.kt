package com.avocado.chat.presenter

import com.avocado.chat.ChatContract
import com.avocado.chat.ChatModel
import com.avocado.chat.domain.entity.Message

class ChatPresenter(val view: ChatContract.ChatView) : ChatContract.ChatPresenter, ChatContract.ChatModel.OnMessagesFinished {

    private var chatModel: ChatContract.ChatModel? = null

    init {
        chatModel = ChatModel()
    }

    override fun sendMessage(message: Message) {
        when (message.type) {
            "message" -> chatModel?.sendInRemoteStorage(message)

            "photo" -> chatModel?.sendPhotoInRemoteStorage(message.image)
        }
    }

    override fun getMessages() {
        chatModel?.getByRemoteStorage(this)
    }

    override fun onMessagesFinished(messageList: ArrayList<Message>) {
        var list = ArrayList<Message>()

        list.addAll(messageList)

        view.loadMessages(list)
    }

    override fun onMessagesFailure() {

    }
}