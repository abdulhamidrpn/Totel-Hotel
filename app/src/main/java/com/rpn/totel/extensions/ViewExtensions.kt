package com.rpn.totel.extensions

//import com.github.florent37.kotlin.pleaseanimate.please
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import com.rpn.totel.utils.StatusBarColorLifecycleObserver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun View.delayOnLifecycle(
    durationInMillis: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job? = findViewTreeLifecycleOwner().let { lifecycleowner ->
    lifecycleowner?.lifecycle?.coroutineScope?.launch(dispatcher) {
        delay(durationInMillis)
        block()
    }
}

fun <T : ViewDataBinding> ViewGroup.inflateWithBinding(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = false
): T {
    val layoutInflater = LayoutInflater.from(context)
    return DataBindingUtil.inflate(layoutInflater, layoutRes, this, attachToRoot) as T
}

fun View?.show(animated: Boolean = false) {
    val view = this ?: return
    visibility = VISIBLE
}

fun View?.gone(animated: Boolean = false) {
    val view = this ?: return
    view.visibility = GONE
}

fun View?.toggle(viewGroup: ViewGroup, show: Boolean) {
    val transition: Transition = Fade()
    transition.duration = 500
    this?.let { transition.addTarget(it) }
    TransitionManager.beginDelayedTransition(viewGroup, transition)
    this?.visibility = if (show) VISIBLE else GONE
}

fun View.setPaddings(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    setPadding(
        left ?: paddingLeft,
        top ?: paddingTop,
        right ?: paddingRight,
        bottom ?: paddingBottom
    )
}

fun View?.animateScale(from: Float, to: Float, dur: Long) {
    this ?: return
    val scaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, from, to)
    val scaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, from, to)
    val animatorSet = AnimatorSet().apply {
        interpolator = OvershootInterpolator()
        duration = dur
        playTogether(scaleX, scaleY)
    }
    animatorSet.start()
}


fun Snackbar.setIcon(drawable: Drawable, @ColorInt colorTint: Int): Snackbar {
    return this.apply {
        setAction(" ") {}
        val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
        textView.text = ""

        drawable.setTint(colorTint)
        drawable.setTintMode(PorterDuff.Mode.SRC_ATOP)
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }
}


internal fun View?.findSuitableParent(): ViewGroup? {
    var view = this
    var fallback: ViewGroup? = null
    do {
        if (view is CoordinatorLayout) {
            return view
        } else if (view is FrameLayout) {
            if (view.id == android.R.id.content) {
                return view
            } else {
                fallback = view
            }
        }

        if (view != null) {
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)

    return fallback
}

fun RecyclerView.snapToPosition(
    position: Int,
    snapMode: Int = LinearSmoothScroller.SNAP_TO_START,
    smooth: Boolean = true
) {
    if (smooth) {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    } else {
        (layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(position, 20)
    }
}




fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun dpToPx(dp: Int, context: Context): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        context.resources.displayMetrics,
    ).toInt()

internal val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

internal val Context.inputMethodManager
    get() = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

internal fun Context.getDrawableCompat(@DrawableRes drawable: Int): Drawable =
    requireNotNull(ContextCompat.getDrawable(this, drawable))

internal fun Context.getColorCompat(@ColorRes color: Int) =
    ContextCompat.getColor(this, color)

internal fun TextView.setTextColorRes(@ColorRes color: Int) =
    setTextColor(context.getColorCompat(color))

fun Fragment.addStatusBarColorUpdate(@ColorRes colorRes: Int) {
    view?.findViewTreeLifecycleOwner()?.lifecycle?.addObserver(
        StatusBarColorLifecycleObserver(
            requireActivity(),
            requireContext().getColorCompat(colorRes),
        ),
    )
}

fun View.createGradientBackground() {
    val colorBright = "#00FFFFFF"
    val colorDark = "#E6000000"
//        val colorDark = "#BE000000"
    val colors = intArrayOf(Color.parseColor(colorBright), Color.parseColor(colorDark))
    val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
    gd.cornerRadius = 0f
    background = gd
}

val screenHeight by lazy {
    Resources.getSystem().displayMetrics.heightPixels
}

fun RecyclerView.smoothScrollToPos(position: Int) {
    Handler(Looper.getMainLooper()).postDelayed({
        this.smoothScrollToPosition(position)
    }, 300)
}