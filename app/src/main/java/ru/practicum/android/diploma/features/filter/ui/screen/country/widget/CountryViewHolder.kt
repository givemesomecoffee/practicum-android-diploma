package ru.practicum.android.diploma.features.filter.ui.screen.country.widget

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.filter.domain.model.Area

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemTitle = itemView.findViewById<TextView>(R.id.cell_title)

    fun bind(model: Area, listener: ItemClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(model)
        }
        itemTitle.text = model.name
    }
}
