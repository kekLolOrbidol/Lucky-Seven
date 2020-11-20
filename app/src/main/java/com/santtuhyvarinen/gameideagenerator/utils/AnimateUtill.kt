package com.santtuhyvarinen.gameideagenerator.utils

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.TextView

class AnimateUtill {
    companion object {
        const val TEXT_ANIMATION_SPEED = 100L

        fun animateTextChange(view : View, textView : TextView, newText : String) {
            val scaleAnimationExit = ScaleAnimation(
                1f, 1f,
                1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)

            val scaleAnimationEntrance = ScaleAnimation(
                1f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)

            scaleAnimationExit.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    textView.text = newText
                    view.startAnimation(scaleAnimationEntrance)
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            })

            scaleAnimationExit.interpolator = DecelerateInterpolator()
            scaleAnimationEntrance.interpolator = AccelerateDecelerateInterpolator()

            scaleAnimationExit.duration = TEXT_ANIMATION_SPEED
            scaleAnimationEntrance.duration = TEXT_ANIMATION_SPEED

            //First play scaleAnimationExit and then play scaleAnimationEntrance
            view.startAnimation(scaleAnimationExit)
        }
    }
}