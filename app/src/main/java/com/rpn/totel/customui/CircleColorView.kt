package com.rpn.totel.customui

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.rpn.totel.R

class CircleColorView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val labeltext: TextView
    private val labelColor: View

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.CircleColorView, 0, 0
        )
        val titleText = a.getString(R.styleable.CircleColorView_titleText)
        val titleTextSize = a.getDimensionPixelSize(
            R.styleable.CircleColorView_titleTextSize,
            resources.getDimension(R.dimen.twelve_sp).toInt()
        )
        val valueColor = a.getColor(
            R.styleable.CircleColorView_labelColor,
            resources.getColor(R.color.blue)
        )
        val titleTextColor = a.getColor(
            R.styleable.CircleColorView_titleTextColor,
            resources.getColor(R.color.grey)
        )
        a.recycle()
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.legend_layout, this, true)
        labelColor = getChildAt(0)
        labeltext = getChildAt(1) as TextView
        labeltext.setTextColor(titleTextColor)
        labeltext.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
        setColorAndLabel(valueColor, titleText ?: "")
        orientation = HORIZONTAL
    }

    fun setColorAndLabel(@ColorInt color: Int, text: String) {
        labelColor.background.setColorFilter(color, PorterDuff.Mode.ADD)
        labeltext.text = text
        invalidate()
    }
}





fun Context.createDotIndicator(count: Int,container : LinearLayout) {
    val dotSize = resources.getDimension(com.intuit.sdp.R.dimen._8sdp).toInt() // Define dot size
    val dotMargin = resources.getDimension(com.intuit.sdp.R.dimen._2sdp).toInt() // Define margin between dots

    for (i in 0 until count) {
        val dot = ImageView(this)
        dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_indicator_inactive)) // Create an inactive dot drawable
        val params = LinearLayout.LayoutParams(dotSize, dotSize)
        params.setMargins(dotMargin, 0, dotMargin, 0)
        dot.layoutParams = params
        container.addView(dot)
    }

    // Highlight the first dot as active
    if (container.childCount > 0) {
        val params = LinearLayout.LayoutParams(dotSize, dotSize)
        params.setMargins(dotMargin, 0, dotMargin, 0)
        val firstDot = container.getChildAt(0) as ImageView
        firstDot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_indicator_active))
    }
}

fun Context.updateDotIndicator(position: Int,container : LinearLayout) {
    for (i in 0 until container.childCount) {
        val dot = container.getChildAt(i) as ImageView
        dot.setImageDrawable(
            if (i == position)
                ContextCompat.getDrawable(this, R.drawable.dot_indicator_active)
            else
                ContextCompat.getDrawable(this, R.drawable.dot_indicator_inactive)
        )
    }
}