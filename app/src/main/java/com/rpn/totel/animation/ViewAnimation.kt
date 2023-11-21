package com.rpn.totel.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator


object ViewAnimation {


    fun rotateViewByDegrees(view: View, degrees: Float) {
        view.animate()
            .rotationBy(degrees) // Rotate by the specified degrees
            .setDuration(500)    // Animation duration in milliseconds (adjust as needed)
            .start()
    }

    fun animateViewVisibility(view: View, visibility: Int) {
        val animationDuration = 500L // Adjust as needed

        if (view.visibility == visibility) return // No need to animate if already in the desired state

        if (visibility == View.VISIBLE) {
            view.alpha = 0f
            view.visibility = View.VISIBLE
            view.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .start()
        } else {
            view.animate()
                .alpha(0f)
                .setDuration(animationDuration)
                .withEndAction {
                    view.visibility = View.GONE
                }
                .start()
        }
    }

    fun animateViewSlide(view: View, slideIn: Boolean) {
        val translationY = if (slideIn) 0f else -view.height.toFloat()
        val duration = 500L // Adjust as needed
        val interpolator = DecelerateInterpolator()

        val animator = ObjectAnimator.ofFloat(view, "translationY", translationY)
        animator.duration = duration
        animator.interpolator = interpolator

        if (slideIn) {
            view.visibility = View.VISIBLE
        } else {
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.GONE
                }
            })
        }

        animator.start()
    }

    //SlideAnimation
    fun animateView(view: View, isVisible: Boolean, duration: Long) {
        val duration = 300L

        if (isVisible) {
            view.visibility = View.VISIBLE
            view.animate()
                .translationY(view.height.toFloat())
                .setDuration(duration)
                .start()
        } else {
            view.animate()
                .translationY(0f)
                .setDuration(duration)
                .withEndAction {
                    view.visibility = View.GONE
                }
                .start()
        }
    }

    //Fade in out animation
    fun animateView(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE
            // Fade-in animation
            val fadeInAnim = AnimationUtils.loadAnimation(view.context, android.R.anim.fade_in)
            view.startAnimation(fadeInAnim)
        } else {
            // Fade-out animation
            val fadeOutAnim = AnimationUtils.loadAnimation(view.context, android.R.anim.fade_out)
            fadeOutAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    view.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
            view.startAnimation(fadeOutAnim)
        }
    }
}
