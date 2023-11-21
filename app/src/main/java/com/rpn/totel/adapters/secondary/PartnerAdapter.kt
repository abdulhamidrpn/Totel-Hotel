package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.adapters.view_pager.ImageAdapter
import com.rpn.totel.databinding.ItemPartnerBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Booked
import com.rpn.totel.model.Item
import com.rpn.totel.utils.Constants


class PartnerAdapter : RecyclerView.Adapter<PartnerAdapter.ViewHolder>() {

    var list: MutableList<Item> = mutableListOf()

    var itemClickListener: ItemClickListener<Item>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_partner,
            parent,
            false
        ) as ItemPartnerBinding

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
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {

            binding as ItemPartnerBinding
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(
                    binding.root, adapterPosition, getItem(adapterPosition)
                )
            }

            init()
        }

        fun init() {
            binding as ItemPartnerBinding
            val adapter = ImageAdapter()
            adapter.updateDataSet(Constants.demoProfileList)
            adapter.itemClickListener = object : ItemClickListenerSingle<Item> {
                override fun onItemClick(view: View, position: Int, item: Item) {

                }
            }
            binding.viewpager.adapter = adapter
            binding.dotsIndicator.attachTo(binding.viewpager)
        }

    }
}