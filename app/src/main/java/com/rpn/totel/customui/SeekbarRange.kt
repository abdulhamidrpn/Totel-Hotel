package com.rpn.totel.customui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.google.android.material.slider.RangeSlider
import org.jetbrains.annotations.Nullable
import java.lang.reflect.InvocationTargetException


class MyCustomRangeSlider : RangeSlider {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        //in case this View is inside a ScrollView you can listen to OnScrollChangedListener to redraw the View
        viewTreeObserver.addOnScrollChangedListener { invalidate() }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setSliderTooltipAlwaysVisible(this)
    }

    companion object {
        fun setSliderTooltipAlwaysVisible(slider: RangeSlider?) {
            try {
                val baseSliderCls: Class<*>? = RangeSlider::class.java.superclass
                if (baseSliderCls != null) {
                    val ensureLabelsAddedMethod =
                        baseSliderCls.getDeclaredMethod("ensureLabelsAdded")
                    ensureLabelsAddedMethod.isAccessible = true
                    ensureLabelsAddedMethod.invoke(slider)
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
        }
    }
}

