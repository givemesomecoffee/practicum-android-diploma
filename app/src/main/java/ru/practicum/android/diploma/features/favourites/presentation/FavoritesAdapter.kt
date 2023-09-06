package ru.practicum.android.diploma.features.favourites.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy

class FavoritesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var favoriteVacancyClickListener: FavoriteVacancyClickListener =
        FavoriteVacancyClickListener {}

    private var favoriteVacancies: List<Vacancy> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoritesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.job_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return favoriteVacancies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoritesViewHolder)
            .bind(favoriteVacancies[position], favoriteVacancyClickListener)
    }

    fun setVacancies(vacancies: List<Vacancy>) {
        favoriteVacancies = vacancies
    }

    fun setItemClickListener(clickListener: FavoriteVacancyClickListener) {
        favoriteVacancyClickListener = clickListener
    }

    fun interface FavoriteVacancyClickListener {
        fun onVacancyClick(vacancy: Vacancy)
    }
}