package ru.practicum.android.diploma.features.filter.ui.screen.region.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.filter.ui.screen.region.model.RegionUi

class RegionAdapter(private val listener: RegionClickListener) :
    RecyclerView.Adapter<RegionViewHolder>() {
    var countryList = listOf<RegionUi>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cell_with_radio_btn, parent, false)
        return RegionViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

}