package com.rpn.totel.ui.viewmodel


import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel

class SingleChatViewModel : ViewModel() {

    private val typingHandler = Handler(Looper.getMainLooper())

    private var isTyping = false

    private var canScroll = false

    private var chatUserOnline = false


}