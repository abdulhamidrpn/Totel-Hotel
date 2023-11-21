package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.animation.ViewAnimation
import com.rpn.totel.databinding.ItemReviewsBinding
import com.rpn.totel.databinding.ItemReviewsTemplate1Binding
import com.rpn.totel.databinding.ItemReviewsTemplate2Binding
import com.rpn.totel.extensions.inflateWithBinding
import com.rpn.totel.model.Reviews


const val REVIEWS_TYPE_SMALL = 0
const val REVIEWS_TYPE_NORMAL = 1
const val REVIEWS_TYPE_LARGE = 2

class ReviewsAdapter(val viewType: Int) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    var list: MutableList<Reviews> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            REVIEWS_TYPE_SMALL -> {
                val viewBindingSmall =
                    parent.inflateWithBinding<ItemReviewsTemplate2Binding>(R.layout.item_reviews_template_2)

                fullViews.add(viewBindingSmall.root)
                ViewHolder(viewBindingSmall, viewType)
            }

            REVIEWS_TYPE_NORMAL -> {
                val viewBindingNormal =
                    parent.inflateWithBinding<ItemReviewsBinding>(R.layout.item_reviews)
                fullViews.add(viewBindingNormal.root)
                ViewHolder(viewBindingNormal, viewType)
            }

            THUMBNAIL_TYPE_LARGE -> {
                val viewBindingSmall =
                    parent.inflateWithBinding<ItemReviewsTemplate1Binding>(R.layout.item_reviews_template_1)
                fullViews.add(viewBindingSmall.root)
                ViewHolder(viewBindingSmall, viewType)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Reviews>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (viewType == REVIEWS_TYPE_SMALL) {
            holder.bindTemplate2(getItem(position))
        } else if (viewType == REVIEWS_TYPE_LARGE) {
            holder.bindTemplate1(getItem(position))
        } else {
            holder.bindNormal(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    inner class ViewHolder(private val binding: ViewDataBinding, viewType: Int) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bindNormal(reviews: Reviews) {
            binding as ItemReviewsBinding
            binding.apply {

                ratingBar.numStars = 5
                ratingBar.rating = 4f
            }
            binding.container.setOnClickListener {

                if (binding.containerDetails.visibility == View.VISIBLE) {
                    ViewAnimation.animateView(binding.containerDetails, false)
                    ViewAnimation.rotateViewByDegrees(binding.ivDropdown, 180f)
                } else {
                    ViewAnimation.animateView(binding.containerDetails, true)
                    ViewAnimation.rotateViewByDegrees(binding.ivDropdown, 180f)
                }
            }
        }

        fun bindTemplate1(reviews: Reviews) {

            binding as ItemReviewsTemplate1Binding
            binding.apply {
                ratingBar.numStars = 5
                ratingBar.rating = 3f
            }
        }

        fun bindTemplate2(reviews: Reviews) {

            binding as ItemReviewsTemplate2Binding
            binding.apply {
                ratingBar.numStars = 5
                ratingBar.rating = 3f
            }
        }

        override fun onClick(view: View?) {

        }

    }

    private val fullViews = mutableListOf<View>()

    fun getFullView(position: Int): View {
        return fullViews[position]
    }
}