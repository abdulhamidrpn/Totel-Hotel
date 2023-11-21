package com.rpn.totel.adapters.view_pager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemImageBinding
import com.rpn.totel.extensions.createGradientBackground
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Item
import com.rpn.totel.utils.loadImage

class ImageAdapter() : PagerAdapter() {

    var list: MutableList<Item> = mutableListOf()
    override fun getCount(): Int = list.size
    var itemClickListener: ItemClickListenerSingle<Item>? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Item>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_image, container, false
        ) as ItemImageBinding

        val item = list[position]

        container.context.loadImage(viewBinding.ivContent, drawableResId = item.icon)

        viewBinding.imageForeground.createGradientBackground()

        container.addView(viewBinding.root)
        return viewBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
