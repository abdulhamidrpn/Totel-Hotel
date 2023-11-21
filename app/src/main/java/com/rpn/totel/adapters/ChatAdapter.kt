package com.rpn.hello.fragments.single_chat

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.RowImageReceiveBinding
import com.rpn.totel.databinding.RowImageSentBinding
import com.rpn.totel.databinding.RowReceiveMessageBinding
import com.rpn.totel.databinding.RowSentMessageBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Claims
import com.rpn.totel.model.Message
import com.rpn.totel.utils.SettingsUtility
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChatAdapter() : ListAdapter<Message, RecyclerView.ViewHolder>(DiffCallbackMessages()),
    KoinComponent {

    val TAG = "ChatTAG"
    private val settingsUtility by inject<SettingsUtility>()

    var itemClickListener: ItemClickListener<Message>? = null
    var list: MutableList<Message> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Message>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_TXT_SENT = 0
        private const val TYPE_TXT_RECEIVED = 1
        private const val TYPE_IMG_SENT = 2
        private const val TYPE_IMG_RECEIVE = 3

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TXT_SENT -> {
                val binding = RowSentMessageBinding.inflate(layoutInflater, parent, false)
                TxtSentVHolder(binding)
            }

            TYPE_TXT_RECEIVED -> {
                val binding = RowReceiveMessageBinding.inflate(layoutInflater, parent, false)
                TxtReceiveVHolder(binding)
            }

            TYPE_IMG_SENT -> {
                val binding = RowImageSentBinding.inflate(layoutInflater, parent, false)
                ImageSentVHolder(binding)
            }

            TYPE_IMG_RECEIVE -> {
                val binding = RowImageReceiveBinding.inflate(layoutInflater, parent, false)
                ImageReceiveVHolder(binding)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TxtSentVHolder ->
                holder.bind(getItem(position))

            is TxtReceiveVHolder ->
                holder.bind(getItem(position))

            is ImageSentVHolder ->
                holder.bind(getItem(position))

            is ImageReceiveVHolder ->
                holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        val fromMe = message.from == settingsUtility.uid


        Log.i(TAG, "adapter $position: \n" +
                "setti uid ${settingsUtility.uid}\n" +
                "from:     ${message.from} \n" +
                "to:       ${message.to}\n" +
                "from me:  $fromMe")

        if (fromMe && message.type == "text")
            return TYPE_TXT_SENT
        else if (!fromMe && message.type == "text")
            return TYPE_TXT_RECEIVED
        else if (fromMe && message.type == "image" && message.imageMessage?.imageType == "image")
            return TYPE_IMG_SENT
        else if (!fromMe && message.type == "image" && message.imageMessage?.imageType == "image")
            return TYPE_IMG_RECEIVE

        return super.getItemViewType(position)
    }

    inner class TxtSentVHolder(val binding: RowSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.message = item
            binding.messageList = list as ArrayList<Message>
            if (adapterPosition > 0) {
                val message = list[adapterPosition - 1]
                if (message.from == item.from)
                    binding.txtMsg.setBackgroundResource(R.drawable.shape_send_msg_corned)
            }
            binding.executePendingBindings()
        }
    }

    inner class TxtReceiveVHolder(val binding: RowReceiveMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.message = item
            if (adapterPosition > 0) {
                val message = list[adapterPosition - 1]
                if (message.from == item.from)
                    binding.txtMsg.setBackgroundResource(R.drawable.shape_receive_msg_corned)
            }
            binding.executePendingBindings()
        }
    }

    inner class ImageSentVHolder(val binding: RowImageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.message = item
            binding.executePendingBindings()
        }
    }

    inner class ImageReceiveVHolder(val binding: RowImageReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.message = item
            binding.executePendingBindings()
        }
    }

}

class DiffCallbackMessages : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.createdAt == newItem.createdAt
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}
