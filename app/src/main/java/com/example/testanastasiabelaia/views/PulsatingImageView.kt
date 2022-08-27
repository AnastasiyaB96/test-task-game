package com.example.testanastasiabelaia.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class PulsatingImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var animator: ValueAnimator? = null

    init {
        pulsate()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (isEnabled){
            pulsate()
        } else {
            stopPulsate()
        }
    }

    private fun pulsate() {
        animator = ValueAnimator.ofFloat(1f, 1.1f).also {
            it.duration = 1200
            it.addUpdateListener { animation ->
                scaleX = animation.animatedValue as Float
                scaleY = animation.animatedValue as Float
            }
            it.repeatCount = ValueAnimator.INFINITE
            it.repeatMode = ValueAnimator.REVERSE
            it.start()
        }
    }

    private fun stopPulsate() {
        animator?.cancel()
        scaleX = 1f
        scaleY = 1f
        animator = null
    }
}