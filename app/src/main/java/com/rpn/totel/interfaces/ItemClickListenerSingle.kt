package com.rpn.totel.interfaces

import android.view.View


interface ItemClickListenerSingle<T> {
    fun onItemClick(view: View, position: Int, item: T)
}