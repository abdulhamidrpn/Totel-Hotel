package com.rpn.totel.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.rpn.totel.R
import com.rpn.totel.model.ImageMessage
import com.rpn.totel.model.Item
import com.rpn.totel.model.Message
import com.rpn.totel.model.TextMessage
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun Context.toast(msg: String) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}


const val LIGHT = "light"
const val DARK = "dark"
const val BATTERY = "battery"
const val DEFAULT = "default"
const val RC_SELECT_IMAGE = 2

fun applyTheme(theme: String?) {
    when (theme) {
        LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        BATTERY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
        DEFAULT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}

fun Context.loadImage(view: ImageView, imageUri: String? = null, drawableResId: Int? = null) {
    val drawableUri = drawableResId?.let { drawableToUri(this, it) }
    val uri = if (drawableUri != null) drawableUri else imageUri
    view.clipToOutline = true
    Glide.with(view).asBitmap().load(uri).into(view)

}

fun drawableToUri(context: Context, drawableResId: Int): Uri? {
    try {
        val res = context.resources
        val uriString = "android.resource://${res.getResourcePackageName(drawableResId)}/" +
                "${res.getResourceTypeName(drawableResId)}/${res.getResourceEntryName(drawableResId)}"
        return Uri.parse(uriString)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun getAlbumArt(uri: String): ByteArray? {
    var retriever = MediaMetadataRetriever()
    retriever.setDataSource(uri)
    var art = retriever.embeddedPicture
    retriever.release()
    Log.d("AudioDetails", "ART OF IMAGE $art uri $uri")
    return art
}

fun millisecondsConverter(millis: Long): String {
    var duration: String

    if (TimeUnit.MILLISECONDS.toMinutes(millis) < 60) {
        duration = String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(millis)
            )
        )
    } else {

        duration = String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
            TimeUnit.MILLISECONDS.toSeconds(millis) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        )
    }

    return duration
}


fun getSize(size: Long): String {
    var s = ""
    val kb = (size / 1024).toDouble()
    val mb = kb / 1024
    val gb = mb / 1024
    val tb = gb / 1024
    if (size < 1024L) {
        s = "$size Bytes"
    } else if (size >= 1024 && size < 1024L * 1024) {
        s = String.format("%.2f", kb) + " KB"
    } else if (size >= 1024L * 1024 && size < 1024L * 1024 * 1024) {
        s = String.format("%.2f", mb) + " MB"
    } else if (size >= 1024L * 1024 * 1024 && size < 1024L * 1024 * 1024 * 1024) {
        s = String.format("%.2f", gb) + " GB"
    } else if (size >= 1024L * 1024 * 1024 * 1024) {
        s = String.format("%.2f", tb) + " TB"
    }
    return s
}

fun scaleBitmapAndKeepRation(
    targetBmp: Bitmap,
    reqHeightInDp: Int,
    reqWidthInDp: Int
): Bitmap? {
    val matrix = Matrix()
    var reqHeightInPixels = reqHeightInDp * Resources.getSystem().displayMetrics.density
    var reqWidthInPixels = reqWidthInDp * Resources.getSystem().displayMetrics.density
    matrix.setRectToRect(
        RectF(
            0F, 0F, targetBmp.width.toFloat(),
            targetBmp.height.toFloat()
        ),
        RectF(0F, 0F, reqWidthInPixels, reqHeightInPixels),
        Matrix.ScaleToFit.CENTER
    )
    return Bitmap.createBitmap(targetBmp, 0, 0, targetBmp.width, targetBmp.height, matrix, true)
}


fun imageAnimation(context: Context, imageView: ImageView, bitmap: Bitmap) {
    var animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
    var animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
    animOut.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            Glide.with(context)
                .load(bitmap)
                .into(imageView)
            imageView.startAnimation(animIn)
        }

        override fun onAnimationRepeat(animation: Animation?) {

        }
    })
    imageView.startAnimation(animOut)
}

fun getRandomNumber(bound: Int): Int {
    var random = Random
    return random.nextInt(bound)
}


fun createGradientBackground(view: ImageView, colorBright: String, colorDark: String) {
    //Color.parseColor() method allow us to convert
// a hexadecimal color string to an integer value (int color)
    //Color.parseColor() method allow us to convert
// a hexadecimal color string to an integer value (int color)
    val colors = intArrayOf(Color.parseColor(colorBright), Color.parseColor(colorDark))

//create a new gradient color

//create a new gradient color
    val gd = GradientDrawable(
        GradientDrawable.Orientation.LEFT_RIGHT, colors
    )

    gd.cornerRadius = 0f
//apply the button background to newly created drawable gradient
//apply the button background to newly created drawable gradient
    view.setImageDrawable(gd)
}

fun createGradientDrawable(): Drawable {
    val drawable = Constants.gradientColor.random()
    val colorBright = drawable["color1"]!!
    val colorDark = drawable["color2"]!!
    val colors = intArrayOf(Color.parseColor(colorBright), Color.parseColor(colorDark))

    val gd = GradientDrawable(
        GradientDrawable.Orientation.LEFT_RIGHT, colors
    )

    gd.cornerRadius = 0f
    return gd
}
fun getCurrentTime(extraMinute :Int = 0 ): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MINUTE, extraMinute) // Add one minute to the current time

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    // Format the time as a string (e.g., "HH:mm")
    return calendar.time.time
}
fun drawableResourceToUri(context: Context, drawableId: Int): Uri? {
    val res = context.resources
    val uriString = "android.resource://${context.packageName}/$drawableId"
    return Uri.parse(uriString)
}
object Constants {

    val workingStatus: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val demoCardList = listOf(
        Item(R.drawable.visa, "**** **** **** 1728"),
        Item(R.drawable.wester_union, "**** **** **** 6587"),
        Item(R.drawable.master_card, "**** **** **** 1954")
    )
    val demoPaymentMethodList = listOf(
        Item(R.drawable.google_pay, "Google Pay"),
        Item(R.drawable.paypal, "Pay Pal"),
        Item(R.drawable.applelogo, "Apple pay")
    )
    val demoItemList = listOf(
        Item(R.drawable.thumbnail_hotel_1, "Radison Blue"),
        Item(R.drawable.thumbnail_hotel_2, "Western Sydney"),
        Item(R.drawable.thumbnail_hotel_3, "Burj Al Arab"),
        Item(R.drawable.thumbnail_hotel_4, "Tower Great Britain"),
        Item(R.drawable.thumbnail_hotel_5, "Pinensula, Chittagong"),
        Item(R.drawable.thumbnail_hotel_6, "Hotel Soikat"),
        Item(R.drawable.thumbnail_hotel_7, "Shitakundo Top hill view"),
        Item(R.drawable.thumbnail_hotel_8, "Brunel's SS Great Britain")
    )
    val demoProfileList = listOf(
        Item(R.drawable.thumbnail_profile_1, "Radison Blue"),
        Item(R.drawable.thumbnail_profile_2, "Western Sydney"),
        Item(R.drawable.thumbnail_profile_3, "Burj Al Arab"),
        Item(R.drawable.thumbnail_profile_4, "Tower Great Britain"),
        Item(R.drawable.thumbnail_profile_5, "Pinensula, Chittagong"),
        Item(R.drawable.thumbnail_profile_6, "profile Soikat"),
        Item(R.drawable.thumbnail_profile_7, "Shitakundo Top hill view")
    )



    val audioType = arrayOf("LECTURE", "SONG", "SHORTS", "RECITATION")
    val audioLanguage = arrayOf("BANGLA", "ENGLISH")

    val gradientColor = arrayOf(
        mapOf("color1" to "#ffafbd", "color2" to "#ffc3a0"),
        mapOf("color1" to "#2193b0", "color2" to "#6dd5ed"),
        mapOf("color1" to "#cc2b5e", "color2" to "#753a88"),
        mapOf("color1" to "#ee9ca7", "color2" to "#ffdde1"),
        mapOf("color1" to "#42275a", "color2" to "#734b6d"),
        mapOf("color1" to "#2c3e50", "color2" to "#bdc3c7"),
        mapOf("color1" to "#de6262", "color2" to "#ffb88c"),
        mapOf("color1" to "#06beb6", "color2" to "#48b1bf"),
        mapOf("color1" to "#eb3349", "color2" to "#f45c43"),
        mapOf("color1" to "#dd5e89", "color2" to "#f7bb97"),
        mapOf("color1" to "#56ab2f", "color2" to "#a8e063"),
        mapOf("color1" to "#614385", "color2" to "#516395"),
        mapOf("color1" to "#eecda3", "color2" to "#ef629f"),
        mapOf("color1" to "#eacda3", "color2" to "#d6ae7b"),
        mapOf("color1" to "#02aab0", "color2" to "#00cdac"),
        mapOf("color1" to "#d66d75", "color2" to "#e29587"),
        mapOf("color1" to "#000428", "color2" to "#004e92"),
        mapOf("color1" to "#ddd6f3", "color2" to "#faaca8"),
        mapOf("color1" to "#7b4397", "color2" to "#dc2430"),
        mapOf("color1" to "#43cea2", "color2" to "#185a9d"),
        mapOf("color1" to "#ba5370", "color2" to "#f4e2d8"),
        mapOf("color1" to "#ff512f", "color2" to "#dd2476"),
        mapOf("color1" to "#4568dc", "color2" to "#b06ab3"),
        mapOf("color1" to "#ec6f66", "color2" to "#f3a183"),
        mapOf("color1" to "#ffd89b", "color2" to "#19547b"),
        mapOf("color1" to "#3a1c71", "color2" to "#d76d77"),
        mapOf("color1" to "#4ca1af", "color2" to "#c4e0e5"),
        mapOf("color1" to "#ff5f6d", "color2" to "#ffc371"),
        mapOf("color1" to "#36d1dc", "color2" to "#5b86e5"),
        mapOf("color1" to "#c33764", "color2" to "#1d2671"),
        mapOf("color1" to "#141e30", "color2" to "#243b55"),
        mapOf("color1" to "#ff7e5f", "color2" to "#feb47b"),
        mapOf("color1" to "#ed4264", "color2" to "#ffedbc"),
        mapOf("color1" to "#2b5876", "color2" to "#4e4376"),
        mapOf("color1" to "#ff9966", "color2" to "#ff5e62"),
        mapOf("color1" to "#aa076b", "color2" to "#61045f"),
        mapOf("color1" to "#2E3192", "color2" to "#1BFFFF"),
        mapOf("color1" to "#009245", "color2" to "#FCEE21"),
        mapOf("color1" to "#662D8C", "color2" to "#ED1E79"),
        mapOf("color1" to "#EE9CA7", "color2" to "#FFDDE1"),
        mapOf("color1" to "#BFF098", "color2" to "#6FD6FF"),
        mapOf("color1" to "#4E65FF", "color2" to "#92EFFD"),

        mapOf("color1" to "#FF512F", "color2" to "#F09819"),
        mapOf("color1" to "#1A2980", "color2" to "#26D0CE"),
        mapOf("color1" to "#403B4A", "color2" to "#E7E9BB"),
        mapOf("color1" to "#E55D87", "color2" to "#5FC3E4"),
        mapOf("color1" to "#003973", "color2" to "#E5E5BE"),
        mapOf("color1" to "#D31027", "color2" to "#EA384D"),
        mapOf("color1" to "#16A085", "color2" to "#F4D03F"),
        mapOf("color1" to "#e52d27", "color2" to "#b31217"),
        mapOf("color1" to "#ff6e7f", "color2" to "#bfe9ff"),
        mapOf("color1" to "#314755", "color2" to "#26a0da"),
        mapOf("color1" to "#2b5876", "color2" to "#4e4376"),
        mapOf("color1" to "#e65c00", "color2" to "#F9D423"),
        mapOf("color1" to "#ec008c", "color2" to "#fc6767"),
        mapOf("color1" to "#b92b27", "color2" to "#1565C0"),
        mapOf("color1" to "#f12711", "color2" to "#f5af19"),

        mapOf("color1" to "#A9F1DF", "color2" to "#FFBBBB")
    )
}
