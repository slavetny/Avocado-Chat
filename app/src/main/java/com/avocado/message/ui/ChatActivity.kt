package com.avocado.message.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.avocado.chat.ChatContract
import com.avocado.chat.domain.entity.Message
import com.avocado.chat.presenter.ChatPresenter
import com.avocado.message.R
import kotlinx.android.synthetic.main.input_field.*

class ChatActivity : AppCompatActivity(), View.OnClickListener, ChatContract.ChatView {

    private var presenter: ChatPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ChatPresenter(this)
    }

    override fun loadMessages(messageList: ArrayList<Message>) {

    }

    private fun abc(): Message {

        return Message(inputField.text.toString(), "message")
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.sendButton -> {
//                presenter?.sendMessage(abc())

//                val database = FirebaseDatabase.getInstance()
//                val myRef = database.getReference("message")
//
//                myRef.setValue("Hello, World!")
            }

        }
    }
}