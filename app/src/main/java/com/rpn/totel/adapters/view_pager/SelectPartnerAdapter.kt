package com.rpn.totel.adapters.view_pager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemSelectPartnerBinding
import com.rpn.totel.extensions.createGradientBackground
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Item

class SelectPartnerAdapter(private val items: List<Item>, private val context: Context) :
    PagerAdapter() {

    override fun getCount(): Int = items.size
    var itemClickListener: ItemClickListenerSingle<Item>? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_select_partner, container, false
        ) as ItemSelectPartnerBinding

        val item = items[position]

        viewBinding.ivPartener.setImageResource(item.icon)
        viewBinding.tvName.text = item.text
        viewBinding.imageForeground.createGradientBackground()

        viewBinding.btnWishlist.setOnClickListener {
            itemClickListener?.onItemClick(viewBinding.root, position, item)
        }
        container.addView(viewBinding.root)
        return viewBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
