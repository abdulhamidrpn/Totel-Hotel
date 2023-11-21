package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemCardAddBinding
import com.rpn.totel.extensions.inflateWithBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Claims
import com.rpn.totel.model.Item
import com.rpn.totel.utils.loadImage


class CardAddAdapter() :
    RecyclerView.Adapter<CardAddAdapter.ViewHolder>() {

    var list: MutableList<Item> = mutableListOf()

    var itemClickListener: ItemClickListener<Item>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBindingNormal =
            parent.inflateWithBinding<ItemCardAddBinding>(R.layout.item_card_add)
        return ViewHolder(viewBindingNormal)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Item>) {
        // TODO: Show only 3 items
        list = newList.toMutableList().subList(0,3)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding as ItemCardAddBinding

            binding.root.context.loadImage(binding.ivLogo, drawableResId = item.icon)
            binding.tvCardNumber.text = item.text

            binding.root.setOnClickListener {
                binding.optionRadioButton.isChecked = !binding.optionRadioButton.isChecked
                itemClickListener?.onItemClick(it, adapterPosition, item)
            }
        }

    }
}