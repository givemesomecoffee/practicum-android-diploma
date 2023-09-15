package ru.practicum.android.diploma.features.filter.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.getColorCompat

class TextWithHintAndIcon(context: Context, attributes: AttributeSet? = null) :
    LinearLayout(context, attributes) {

    private var labelHint: String? = null

    init {
        attributes?.let { initView(context, it) }
    }

    private fun initView(context: Context, attributes: AttributeSet) {
        inflate(context, R.layout.view_text_with_hint_and_icon, this).apply {
            val attrs = context.obtainStyledAttributes(attributes, R.styleable.TextWithHintAndIcon)
            labelHint = attrs.getString(R.styleable.TextWithHintAndIcon_cell_hint)
            attrs.recycle()
        }
    }

    fun setTitle(title: String?, onClear: (() -> Unit)) {
        if (title.isNullOrBlank()) {
            findViewById<TextView>(R.id.title)?.apply {
                text = labelHint
                setTextColor(resources.getColorCompat(R.color.gray))
            }
            findViewById<TextView>(R.id.label)?.isVisible = false
            setIcon(R.drawable.arrow_forward)
            findViewById<ImageView>(R.id.right_icon)?.isClickable = false
        } else {
            findViewById<TextView>(R.id.title)?.apply {
                text = title
                setTextColor(resources.getColorCompat(R.color.blackDayWhiteNight))
            }
            findViewById<TextView>(R.id.label)?.apply {
                isVisible = true
                text = labelHint
            }
            setIcon(R.drawable.cross_icon)
            findViewById<ImageView>(R.id.right_icon)?.apply {
                isClickable = true
                setOnClickListener {
                    onClear()
                }
            }
        }
    }

    private fun setIcon(@DrawableRes iconRes: Int) {
        val icon = ResourcesCompat.getDrawable(resources, iconRes, null)
        findViewById<ImageView>(R.id.right_icon)?.apply {
            icon?.let { setImageDrawable(it) }
        }
    }
}