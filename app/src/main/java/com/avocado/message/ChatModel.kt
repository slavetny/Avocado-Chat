package com.avocado.chat

import android.net.Uri
import com.avocado.chat.domain.entity.Message
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception

class ChatModel : ChatContract.ChatModel {

    private var database: DatabaseReference? = null
    private var messageList: ArrayList<Message>? = null
    private var storage: StorageReference? = null

    init {
        database = FirebaseDatabase.getInstance().reference
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://avocado-chat-ef306.appspot.com/")

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
                messageList?.clear()

                try {
                    for (i in p0.children.iterator().next().children) {
                        messageList?.add(Message("message", i.value.toString()))
                    }
                } catch (e: Exception) {
                    e.fillInStackTrace()
                }

                onFinished.onMessagesFinished(messageList!!)
            }
        })
    }

    override fun sendPhotoInRemoteStorage(photo: Uri?) {
        storage?.child("photo")?.child(photo.toString())

        storage?.putFile(photo!!)
            ?.addOnSuccessListener {
                // success
            }
    }
}