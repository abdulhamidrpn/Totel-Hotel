package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemWishlistBinding
import com.rpn.totel.databinding.ItemWishlistSmallBinding
import com.rpn.totel.extensions.inflateWithBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Claims

const val WISHLIST_TYPE_SMALL = 0
const val WISHLIST_TYPE_NORMAL = 1
const val WISHLIST_TYPE_LARGE = 2

class WishlistAdapter(val viewType: Int) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    var list: MutableList<Claims> = mutableListOf()

    var itemClickListener: ItemClickListener<Claims>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            WISHLIST_TYPE_SMALL -> {
                val viewBindingSmall =
                    parent.inflateWithBinding<ItemWishlistSmallBinding>(R.layout.item_wishlist_small)
                ViewHolder(viewBindingSmall)
            }

            WISHLIST_TYPE_NORMAL -> {
                val viewBindingNormal =
                    parent.inflateWithBinding<ItemWishlistBinding>(R.layout.item_wishlist)
                ViewHolder(viewBindingNormal)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Claims>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun getItemViewType(position: Int): Int {
        return viewType
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (viewType == WISHLIST_TYPE_SMALL) {
            holder.bindWishlistSmall(getItem(position))
        } else {
            holder.bindWishlist(getItem(position))
        }
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindWishlistSmall(claims: Claims) {
            binding as ItemWishlistSmallBinding
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(it, adapterPosition, claims)
            }
        }

        fun bindWishlist(Booked: Claims) {
            binding as ItemWishlistBinding

        }

    }
}