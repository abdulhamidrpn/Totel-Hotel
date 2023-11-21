package com.rpn.totel.interfaces

import android.view.View

interface ItemClickListener<T> {
    fun onItemClick(view: View, position: Int, item: T)
    fun onItemAddToWishlistClick(view: View, position: Int, item: T)
}

