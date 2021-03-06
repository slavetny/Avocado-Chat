package com.avocado.message.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.avocado.chat.ChatContract
import com.avocado.chat.domain.entity.Message
import com.avocado.chat.domain.utils.openGalleryForPickingImage
import com.avocado.chat.presenter.ChatPresenter
import com.avocado.message.R
import com.avocado.message.ui.adapter.MessageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.input_field.*

class ChatActivity : AppCompatActivity(), View.OnClickListener, ChatContract.ChatView {

    private var presenter: ChatPresenter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ChatPresenter(this)
        presenter?.getMessages()

        initRecyclerView()

        sendButton.setOnClickListener(this)
        attachButton.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun loadMessages(messageList: ArrayList<Message>) {
        adapter = MessageAdapter()
        adapter?.setMessageList(messageList)
        adapter?.notifyDataSetChanged()

        recyclerView.adapter = adapter

        recyclerView.scrollToPosition(messageList.size - 1)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.sendButton -> {
                if (!inputField.text.isEmpty()) {
                    presenter?.sendMessage(Message("message", inputField.text.toString()))
                    inputField.text.clear()
                } else {
                    Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.attachButton -> {
                openGalleryForPickingImage(1000)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 1000){
            presenter?.sendMessage(Message("photo", "", data?.data))
        }
    }
}