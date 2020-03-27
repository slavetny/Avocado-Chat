package com.avocado.message.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avocado.chat.ChatContract
import com.avocado.chat.domain.entity.Message
import com.avocado.chat.presenter.ChatPresenter
import com.avocado.message.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.input_field.*

class ChatActivity : AppCompatActivity(), View.OnClickListener, ChatContract.ChatView {

    private var presenter: ChatPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ChatPresenter(this)

        presenter?.getMessages()

//        var database = FirebaseDatabase.getInstance().reference
//
//        database?.addValueEventListener(object: ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                Toast.makeText(this@ChatActivity, p0.children.iterator().next().children.iterator().next().value.toString(), Toast.LENGTH_SHORT).show()
//            }
//        })

        sendButton.setOnClickListener(this)
    }

    override fun loadMessages(messageList: ArrayList<Message>) {
        Toast.makeText(this, messageList.get(0).text, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.sendButton -> {
                presenter?.sendMessage(Message(inputField.text.toString(), "message"))
            }

        }
    }
}