package com.avocado.message.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avocado.chat.domain.entity.Message
import com.avocado.message.R
import kotlinx.android.synthetic.main.message_item.view.*

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var messageList: ArrayList<Message>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)

        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messageList!!.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messageList!!.get(position))
    }

    fun setMessageList(messages: ArrayList<Message>) {
        messageList = ArrayList()

        messageList?.addAll(messages)
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(message: Message) {
            itemView.messageText.text = message.text
        }
    }
}