package ru.practicum.android.diploma.features.filter.ui.screen.country.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult

class CountryAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<CountryViewHolder>() {
    var countryList = listOf<AreaResult>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cell_with_arrow, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

}