package ru.practicum.android.diploma.features.filter.ui.screen.industry.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.filter.ui.screen.industry.IndustryUi

class IndustryAdapter(private val listener: IndustryClickListener) :
    RecyclerView.Adapter<IndustryViewHolder>() {
    var countryList = listOf<IndustryUi>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cell_with_radio_btn, parent, false)
        return IndustryViewHolder(view)
    }

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

}