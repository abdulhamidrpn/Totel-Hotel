package com.rpn.totel.ui.fragment.secondary.message

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.hello.fragments.single_chat.ChatAdapter
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentChatBinding
import com.rpn.totel.extensions.show
import com.rpn.totel.extensions.smoothScrollToPos
import com.rpn.totel.model.ImageMessage
import com.rpn.totel.model.Message
import com.rpn.totel.model.TextMessage
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.utils.Utils
import com.rpn.totel.utils.Utils.edtValue
import com.rpn.totel.utils.Utils.getRandomSentence
import com.rpn.totel.utils.Utils.getRandomString
import com.rpn.totel.utils.drawableResourceToUri
import com.rpn.totel.utils.getCurrentTime
import com.straiberry.android.common.extensions.hide
import kotlinx.coroutines.delay
import java.util.ArrayList

class ChatFragment : BaseFragment() {
    val TAG = "ChatTAG"
    private val rvAdapter: ChatAdapter = ChatAdapter()
    private var _binding: FragmentChatBinding? = null
    var demoMessageList = mutableListOf<Message>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
        setDataInView()
        demoList()
    }

    fun onClick() {
        binding.viewChatBtm.lottieSend.setOnClickListener {
            sendMessage()
        }
    }

    fun init() {
        binding.viewChatBtm.edtMsg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.viewChatBtm.lottieSend.isAnimating)
                    return
                if (s.isNullOrBlank()) {
                    binding.viewChatBtm.ivRecord.show()
                    binding.viewChatBtm.lottieSend.hide()
                } else {
                    binding.viewChatBtm.lottieSend.show()
                    binding.viewChatBtm.ivRecord.hide()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    private fun setDataInView() {
        try {
            val manager = LinearLayoutManager(context)
            binding.list.list.apply {
                manager.stackFromEnd = true
                layoutManager = manager
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
                itemAnimator = null
                adapter = rvAdapter
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun demoList() {

        settingsUtility.uid = getRandomString()
        settingsUtility.demoOtherUid = "P"+getRandomString()
        val myUid = settingsUtility.uid
        val othersUid = settingsUtility.demoOtherUid

        val myName = "Abdul Hamid"
        Log.i(
            TAG, "demoList: \n" +
                    "otherUserId: $othersUid \n" +
                    "myUid:       $myUid"
        )
        demoMessageList = mutableListOf<Message>(
            Message(
                createdAt = getCurrentTime(),
                deliveryTime = getCurrentTime(),
                seenTime = getCurrentTime(),
                from = myUid,
                to = othersUid,
                senderName = myName,
                type = "text",
                status = 1,
                textMessage = TextMessage("Hello how are you"),
                chatUserId = othersUid
            ), Message(
                createdAt = getCurrentTime(1),
                deliveryTime = getCurrentTime(1),
                seenTime = getCurrentTime(1),
                from = othersUid,
                to = myUid,
                senderName = myName,
                type = "text",
                status = 1,
                textMessage = TextMessage("I'm fine,and you?"),
                chatUserId = othersUid
            ),
            Message(
                createdAt = getCurrentTime(2),
                deliveryTime = getCurrentTime(2),
                seenTime = getCurrentTime(2),
                from = myUid,
                to = othersUid,
                senderName = myName,
                type = "image",
                status = 1,
                imageMessage = ImageMessage(
                    drawableResourceToUri(
                        requireContext(),
                        R.drawable.thumbnail_hotel_1
                    ).toString()
                ),
                chatUserId = othersUid
            )
        )

        rvAdapter.updateDataSet(demoMessageList)
        rvAdapter.submitList(demoMessageList)
    }

    fun sendMessage() {

        val msg = edtValue(binding.viewChatBtm.edtMsg)
        if (msg.isEmpty())
            return
        binding.viewChatBtm.lottieSend.playAnimation()
        val newMessage = createMessage().apply {
            textMessage = TextMessage(msg)
            chatUsers = ArrayList()
        }

        demoMessageList.add(newMessage)
        rvAdapter.updateDataSet(demoMessageList)
        rvAdapter.submitList(demoMessageList)

        binding.viewChatBtm.edtMsg.setText("")

        //change to live listener
        binding.list.list.smoothScrollToPos(demoMessageList.lastIndex)

        launch {
            delay(1000)
            sendReplyMessage()
        }
    }
    fun sendReplyMessage() {

        val text = getRandomSentence()
        val myUid = settingsUtility.uid
        val othersUid = settingsUtility.demoOtherUid
        val newMessage = Message(
            createdAt = getCurrentTime(),
            deliveryTime = getCurrentTime(),
            seenTime = getCurrentTime(),
            from = othersUid,
            to = myUid,
            senderName = "Abdul Hamid",
            type = "text",
            status = 1,
            textMessage = TextMessage(text.trim()),
            chatUserId = myUid
        )
        demoMessageList.add(newMessage)
        rvAdapter.updateDataSet(demoMessageList)
        rvAdapter.submitList(demoMessageList)

        //change to live listener
        binding.list.list.smoothScrollToPos(demoMessageList.lastIndex)
    }
    private fun createMessage(): Message {

        return Message(
            System.currentTimeMillis(),
            from = settingsUtility.uid.toString(),
            chatUserId = settingsUtility.demoOtherUid.toString(),
            status = 1,
            to =settingsUtility.demoOtherUid, senderName ="Nothing"
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}