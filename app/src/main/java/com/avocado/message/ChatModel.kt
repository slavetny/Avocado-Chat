package com.avocado.chat

import com.avocado.chat.domain.entity.Message
import com.google.firebase.database.*
import java.lang.Exception


class ChatModel : ChatContract.ChatModel {

    private var database: DatabaseReference? = null
    private var messageList: ArrayList<Message>? = null

    init {
        database = FirebaseDatabase.getInstance().reference

        messageList = ArrayList()
    }

    override fun sendInRemoteStorage(message: Message) {
        database?.child(message.type)?.push()?.setValue(message.text)
    }

    override fun getByRemoteStorage(onFinished: ChatContract.ChatModel.OnMessagesFinished) {
//        database?.addChildEventListener(object: ChildEventListener {
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
//
//            }
//
//            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
//
//            }
//
//            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
//                messageList?.clear()
//
//                for (i in p0.children.iterator()) {
//                    messageList?.add(Message(i.value.toString(), "message"))
//                }
//
//                onFinished.onMessagesFinished(messageList!!)
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot) {
//
//            }
//        })

        database?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                messageList?.clear()

                try {
                    for (i in p0.children.iterator().next().children) {
                        messageList?.add(Message(i.value.toString(), null, "message"))
                    }
                } catch (e: Exception) {
                    e.fillInStackTrace()
                }

                onFinished.onMessagesFinished(messageList!!)
            }
        })
    }

//    private fun gioag(p0: DataSnapshot) {
//        var message = p0.children.iterator().next().value
//
//        for (i in p0.children.iterator()) {
//            onFinished.onMessagesFinished(messageList!!)
//
//            message
//        }
//    }
}