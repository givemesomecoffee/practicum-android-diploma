package ru.practicum.android.diploma.core.ui.button

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.getColorCompat

class CustomButton(context: Context, attributes: AttributeSet? = null) :
    FrameLayout(context, attributes) {

    init {
        attributes?.let { initView(context, it) }
    }

    private fun initView(context: Context, attributes: AttributeSet) {
        inflate(context, R.layout.view_custom_button, this).apply {
            val attrs = context.obtainStyledAttributes(attributes, R.styleable.CustomButton)
            findViewById<TextView>(R.id.root_custom_button).apply {
                text = attrs.getString(R.styleable.CustomButton_custom_button_title)
                when (attrs.getInteger(R.styleable.CustomButton_custom_button_type, PRIMARY_TYPE)) {
                    GHOST_TYPE -> {
                        setTextColor(resources.getColorCompat(R.color.red))
                    }

                    else -> {
                        setTextColor(resources.getColorCompat(R.color.white))
                        setBackgroundResource(R.drawable.bg_primary_button)
                    }
                }
            }
            attrs.recycle()
        }
    }

    private companion object {
        private const val PRIMARY_TYPE = 1
        private const val GHOST_TYPE = 0
    }
}