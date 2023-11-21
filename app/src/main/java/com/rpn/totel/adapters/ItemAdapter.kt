package com.rpn.totel.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemSimpleTextImageBinding
import com.rpn.totel.extensions.inflateWithBinding
import com.rpn.totel.model.Item

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var list: MutableList<Item> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBinding = parent.inflateWithBinding<ItemSimpleTextImageBinding>(R.layout.item_simple_text_image)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Item>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: Item) {
            binding as ItemSimpleTextImageBinding
            binding.imageView.setImageResource(item?.icon ?: 0)
            binding.textView.text = item?.text
        }

        override fun onClick(view: View?) {

        }

    }
}

