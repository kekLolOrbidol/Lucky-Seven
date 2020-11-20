package com.santtuhyvarinen.gameideagenerator.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.santtuhyvarinen.R

class DotDivid(context : Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    var dotRadius = 10f
    var dotColor = Color.WHITE
    set(value) {
        field = value
        paint.color = value
    }

    var dots = 5

    val paint : Paint = Paint()
    init {
        paint.isAntiAlias = true
        paint.color = dotColor
        paint.style = Paint.Style.FILL

        if (attributeSet != null) {
            val a = context.obtainStyledAttributes(attributeSet, R.styleable.DotDivider)
            if (a.hasValue(R.styleable.DotDivider_color)) {
                dotColor = a.getColor(R.styleable.DotDivider_color, Color.WHITE)
            }
            if (a.hasValue(R.styleable.DotDivider_dots)) {
                dots = a.getInt(R.styleable.DotDivider_dots, 5)
            }
            if (a.hasValue(R.styleable.DotDivider_dot_radius)) {
                dotRadius = a.getFloat(R.styleable.DotDivider_dot_radius, 10f)
            }
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(dots <= 0) return

        val step = width / dots
        val offset = step / 2f

        val Y = height / 2f

        for(X in 0 until dots) {
            canvas.drawCircle(offset + (X * step), Y, dotRadius, paint)
        }
    }
}