package com.rpn.totel.extensions

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.chip.Chip
import com.rpn.totel.R
import com.rpn.totel.model.Message
import com.rpn.totel.ui.activity.App
import com.rpn.totel.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

object BindingAdapters {

    @BindingAdapter("main", "secondText")
    @JvmStatic
    fun setBoldString(view: TextView, maintext: String, sequence: String) {
        view.text = getBoldText(maintext, sequence)
    }

    @JvmStatic
    fun getBoldText(text: String, name: String): SpannableStringBuilder {
        val str = SpannableStringBuilder(text)
        val textPosition = text.indexOf(name)
        str.setSpan(
            android.text.style.StyleSpan(Typeface.BOLD),
            textPosition, textPosition + name.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return str
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        if (url.isNullOrEmpty())
            return
        else {
            ImageViewCompat.setImageTintList(view, null) //removing image tint
            view.setPadding(0)
        }
        ImageUtils.loadUserImage(view, url)
    }

    @BindingAdapter("lastMessage")
    @JvmStatic
    fun setLastMessage(txtView: TextView, msgList: List<Message>) {
        val lastMsg = msgList.last()
        txtView.text = getLastMsgTxt(lastMsg)
    }

    @InverseMethod("messageBsg")
    @JvmStatic
    fun messageBsg(msg: Message): String {
        return "sd"
    }


    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imgView: ImageView, message: Message) {
        val url = message.imageMessage?.uri
        val imageType = message.imageMessage?.imageType
        loadMsgImage(imgView, url!!, imageType!!)
    }

    private fun loadMsgImage(imgView: ImageView, url: String, imageType: String) {
        try {
            if (imageType == "gif") {
//                val imageLoader = ImageLoader.Builder(imgView.context)
//                    .componentRegistry {
//                        add(GifDecoder())
//                    }
//                    .build()
//                imgView.load(url,imageLoader){
//                    diskCachePolicy(CachePolicy.ENABLED)
//                    placeholder(R.drawable.gif)
//                    error(R.drawable.gif)
//                }
            } else {
                val isSticker = imageType == "sticker"
                val placeHolder =
                    if (isSticker) R.drawable.ic_sticker else R.drawable.ic_gallery_holder
                imgView.load(url) {
                    diskCachePolicy(CachePolicy.ENABLED)
                    placeholder(placeHolder)
                    error(placeHolder)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getLastMsgTxt(msg: Message): String {
        return when (msg.type) {
            "text" -> {
                msg.textMessage?.text.toString()
            }

            "audio" -> {
                "Audio"
            }

            "image" -> {
                msg.imageMessage?.imageType.toString().capitalize(Locale.getDefault())
            }

            "video" -> {
                "Video"
            }

            "file" -> {
                "File"
            }

            else -> {
                "Steotho Image"
            }
        }
    }


    @BindingAdapter("messageSendTime")
    @JvmStatic
    fun setMessageTime(txtView: TextView, msgList: List<Message>) {
        val lastMsg = msgList.last()
        val sentTime = lastMsg.createdAt
        txtView.text = Utils.getTime(sentTime)
    }

    @BindingAdapter("showMsgTime")
    @JvmStatic
    fun showMsgTime(txtView: TextView, lastMsg: Message) {
        val sentTime = lastMsg.createdAt
        txtView.text = Utils.getTime(sentTime)
    }

    @BindingAdapter("loadAsDrawable")
    @JvmStatic
    fun loadAsDrawable(chip: Chip, url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val drawable = getBitmap(url)
            withContext(Dispatchers.Main) {
                chip.chipIcon = drawable
            }
        }
    }


    private suspend fun getBitmap(url: String): Drawable {
        val context = App.appContext
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .transformations(CircleCropTransformation())
            .build()
        return (loader.execute(request) as SuccessResult).drawable
    }

    @BindingAdapter("messageStatus")
    @JvmStatic
    fun setState(txtStatus: TextView, status: Int) {
        txtStatus.text = when (status) {
            0 -> "Sending.."
            1 -> "Sent"
            2 -> "Delivered"
            3 -> "Seen"
            else -> "Failed"
        }
    }

    @BindingAdapter("progressState")
    @JvmStatic
    fun setProgressState(view: ProgressBar, state: LoadState?) {
        state?.let {
            view.visibility = when (it) {
                LoadState.OnLoading -> View.VISIBLE
                else ->
                    View.GONE
            }
        }
    }

    @BindingAdapter("setUnReadCount")
    @JvmStatic
    fun setUnReadCount(txtView: TextView, msgList: List<Message>) {
        val fromUser = SettingsUtility(txtView.context).uid
        val unReadCount = msgList.filter { it.to == fromUser && it.status < 3 }.size
        txtView.text = unReadCount.toString()
        txtView.visibility = if (unReadCount == 0) View.GONE else View.VISIBLE
    }

    @BindingAdapter("setUnReadCount2")
    @JvmStatic
    fun setUnReadCount(txtView: TextView, count: Int) {
        txtView.text = count.toString()
        txtView.visibility = if (count == 0) View.GONE else View.VISIBLE
    }

    @BindingAdapter("showSelected")
    @JvmStatic
    fun showSelected(view: LottieAnimationView, isSelected: Boolean) {
        if (isSelected) {
            view.playAnimation()
            view.show()
        } else
            view.gone()
    }


}