package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemListingsBinding
import com.rpn.totel.model.Listings


class ListingsAdapter : RecyclerView.Adapter<ListingsAdapter.ViewHolder>() {

    var list: MutableList<Listings> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_listings,
            parent,
            false
        ) as ItemListingsBinding

        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Listings>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(listings: Listings) {
            binding as ItemListingsBinding
            binding.apply {
                ratingBar.numStars = 5
                ratingBar.rating = 3f
            }
        }

        override fun onClick(view: View?) {

        }

    }
}