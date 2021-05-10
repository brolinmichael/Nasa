package com.example.nasa.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class RobotoBoldTextView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatTextView(context, attrs){
    init {
        val tf: Typeface = Typeface.createFromAsset(getContext().assets, "Roboto-Bold.ttf")
        setTypeface(tf, Typeface.NORMAL)
    }
}