package com.rpn.totel.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemChipIconTagBinding
import com.rpn.totel.extensions.inflateWithBinding
import com.rpn.totel.model.TagModel

class TagAdapter : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    var list: MutableList<TagModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBinding =
            parent.inflateWithBinding<ItemChipIconTagBinding>(R.layout.item_chip_icon_tag)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<TagModel>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: TagModel) {
            binding as ItemChipIconTagBinding
            binding.imageView.setImageResource(item.icon ?: 0)
            binding.tvTitle.text = item.tagTitle
        }

        override fun onClick(view: View?) {

        }

    }
}

