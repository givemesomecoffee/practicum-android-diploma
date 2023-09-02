package ru.practicum.android.diploma.features.filter.ui.screen.region.widget

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.filter.ui.screen.region.model.RegionUi

class RegionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemTitle = itemView.findViewById<TextView>(R.id.cell_title)
    private val radioButton = itemView.findViewById<RadioButton>(R.id.btn_check)

    fun bind(model: RegionUi, listener: RegionClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(model)
        }
        radioButton.setOnClickListener {
            listener.onItemClick(model)
        }
        itemTitle.text = model.region.name
        radioButton.isChecked = model.isChecked
    }
}
