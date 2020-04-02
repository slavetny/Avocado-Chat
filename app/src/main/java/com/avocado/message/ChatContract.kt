package com.avocado.chat

import android.graphics.Bitmap
import android.net.Uri
import com.avocado.chat.domain.entity.Message

interface ChatContract {

    interface ChatModel {

        interface OnMessagesFinished {

            fun onMessagesFinished(messageList: ArrayList<Message>)

            fun onMessagesFailure()

        }

        fun sendInRemoteStorage(message: Message)

        fun getByRemoteStorage(onFinished: OnMessagesFinished)

        fun sendPhotoInRemoteStorage(photo: Uri?)

    }

    interface ChatPresenter {

        fun sendMessage(message: Message)

        fun getMessages()

    }

    interface ChatView {

        fun loadMessages(messageList: ArrayList<Message>)

    }
}