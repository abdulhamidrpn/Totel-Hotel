package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemMessageBinding
import com.rpn.totel.model.Messages


class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var messagesList: MutableList<Messages> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_message,
            parent,
            false
        ) as ItemMessageBinding

        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Messages>) {
        messagesList = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = messagesList[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(messages: Messages) {

        }

        override fun onClick(view: View?) {

        }

    }
}