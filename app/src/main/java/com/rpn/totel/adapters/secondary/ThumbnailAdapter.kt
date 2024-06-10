package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemThumbnailBinding
import com.rpn.totel.databinding.ItemThumbnailLargeBinding
import com.rpn.totel.databinding.ItemThumbnailSmallBinding
import com.rpn.totel.extensions.inflateWithBinding
import com.rpn.totel.model.Item
import com.rpn.totel.utils.loadImage


const val THUMBNAIL_TYPE_SMALL = 0
const val THUMBNAIL_TYPE_NORMAL = 1
const val THUMBNAIL_TYPE_LARGE = 2
const val THUMBNAIL_TYPE_UPLOAD_IMAGE = 3

class ThumbnailAdapter(val viewType: Int) : RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>() {

    var list: MutableList<Item> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            THUMBNAIL_TYPE_SMALL -> {
                val viewBindingSmall =
                    parent.inflateWithBinding<ItemThumbnailSmallBinding>(R.layout.item_thumbnail_small)
                ViewHolder(viewBindingSmall, viewType)
            }

            THUMBNAIL_TYPE_NORMAL -> {
                val viewBindingNormal =
                    parent.inflateWithBinding<ItemThumbnailBinding>(R.layout.item_thumbnail)
                ViewHolder(viewBindingNormal, viewType)
            }

            THUMBNAIL_TYPE_LARGE -> {
                val viewBindingLarge =
                    parent.inflateWithBinding<ItemThumbnailLargeBinding>(R.layout.item_thumbnail_large)
                ViewHolder(viewBindingLarge, viewType)
            }

            THUMBNAIL_TYPE_UPLOAD_IMAGE -> {
                val viewBindingNormal =
                    parent.inflateWithBinding<ItemThumbnailLargeBinding>(R.layout.item_thumbnail_large)
                ViewHolder(viewBindingNormal, viewType)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
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
        if (viewType == THUMBNAIL_TYPE_SMALL) {
            holder.bind(getItem(position))

        } else if (viewType == THUMBNAIL_TYPE_LARGE) {
            holder.bind(getItem(position))

        } else if (viewType == THUMBNAIL_TYPE_UPLOAD_IMAGE) {
            holder.bindUploadImageLarge(getItem(position))
        } else {
            holder.bind(getItem(position))

        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    inner class ViewHolder(private val binding: ViewDataBinding, viewType: Int) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: Item) {
            if (viewType == THUMBNAIL_TYPE_SMALL) {
                binding as ItemThumbnailSmallBinding
                bindUploadImageSmall(item)
            } else if (viewType == THUMBNAIL_TYPE_LARGE) {
                binding as ItemThumbnailLargeBinding
                bindUploadImageLarge(item)
            } else {
                binding as ItemThumbnailBinding
                bindUploadImageNormal(item)
            }
        }

        fun bindUploadImageNormal(item: Item) {
            binding as ItemThumbnailBinding
            binding.tvTitle.visibility = View.GONE
            binding.btnCancel.visibility = View.GONE
            binding.root.context.loadImage(binding.ivThumbnail, drawableResId = item.icon)
        }
        fun bindUploadImageLarge(item: Item) {
            binding as ItemThumbnailLargeBinding
            binding.tvTitle.visibility = View.GONE
            binding.btnCancel.visibility = View.GONE
            binding.root.context.loadImage(binding.ivThumbnail, drawableResId = item.icon)
        }
        fun bindUploadImageSmall(item: Item) {
            binding as ItemThumbnailSmallBinding
            binding.tvTitle.visibility = View.GONE
            binding.root.context.loadImage(binding.ivThumbnail, drawableResId = item.icon)
        }

        override fun onClick(view: View?) {

        }

    }
}