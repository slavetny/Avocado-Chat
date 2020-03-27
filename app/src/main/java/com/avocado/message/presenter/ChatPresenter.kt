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
        chatModel?.sendInRemoteStorage(message)
    }

    override fun getMessages() {
        chatModel?.getByRemoteStorage(this)
    }

    override fun onMessagesFinished(messageList: ArrayList<String>) {
        var list = ArrayList<Message>()

        list.add(Message(messageList.get(0), "message"))

        view.loadMessages(list)
    }

    override fun onMessagesFailure() {

    }
}