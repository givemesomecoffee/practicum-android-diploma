package ru.practicum.android.diploma.features.filter.ui.screen.industry.widget

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.filter.ui.screen.industry.IndustryUi

class IndustryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemTitle = itemView.findViewById<TextView>(R.id.cell_title)
    private val radioButton = itemView.findViewById<RadioButton>(R.id.btn_check)

    fun bind(model: IndustryUi, listener: IndustryClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(model)
        }
        radioButton.setOnClickListener {
            listener.onItemClick(model)
        }
        itemTitle.text = model.industry.name
        radioButton.isChecked = model.isChecked
    }
}
