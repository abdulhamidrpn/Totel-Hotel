package com.rpn.totel.interfaces

import android.view.View

interface ItemNotificationClickListener<T> {
    fun onItemClick(view: View, position: Int, item: T)
    fun onItemDurationAlertClick(view: View, position: Int, item: T)
}

