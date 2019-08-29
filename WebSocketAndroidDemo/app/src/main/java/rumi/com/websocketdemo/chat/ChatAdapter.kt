package rumi.com.websocketdemo.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rumi.com.websocketdemo.R
import rumi.com.websocketdemo.databinding.ItemChatBinding

class ChatAdapter(
    private var dataList: List<ChatModel>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHOlder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHOlder {
        return ChatViewHOlder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_chat,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ChatViewHOlder, position: Int) {
        holder.bind(dataList[position])
    }

    class ChatViewHOlder(
        private val binding: ItemChatBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chatModel: ChatModel) {
            binding.tvUsername.text = String.format(chatModel.username + " : ")
            binding.tvMessage.text = chatModel.message
        }
    }
}