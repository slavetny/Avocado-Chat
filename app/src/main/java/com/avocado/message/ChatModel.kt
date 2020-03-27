package com.avocado.chat

import com.avocado.chat.domain.entity.Message
import com.google.firebase.database.*


class ChatModel : ChatContract.ChatModel {

    private var database: DatabaseReference? = null
    private var messageList: ArrayList<String>? = null

    init {
        database = FirebaseDatabase.getInstance().reference

        messageList = ArrayList()
    }

    override fun sendInRemoteStorage(message: Message) {
        database?.child(message.type)?.push()?.setValue(message.text)
    }

    override fun getByRemoteStorage(onFinished: ChatContract.ChatModel.OnMessagesFinished) {
        database?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                //TODO fix crash

                if (p0.childrenCount != 0L) {

                    for (i in p0.children) {
                        messageList?.add(i.getValue(Message::class.java)!!.text)
                    }

                    onFinished.onMessagesFinished(messageList!!)
                }
            }
        })
    }
}