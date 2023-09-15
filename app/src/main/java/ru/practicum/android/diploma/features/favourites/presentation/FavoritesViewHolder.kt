package ru.practicum.android.diploma.features.favourites.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy

class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val jobIconImageView: ImageView = itemView.findViewById(R.id.jobIcon)
    private val jobNameTextView: TextView = itemView.findViewById(R.id.jobName)
    private val jobEmployerTextview: TextView = itemView.findViewById(R.id.jobEmployer)
    private val jobSalaryTextView: TextView = itemView.findViewById(R.id.jobSalary)

    fun bind(
        vacancy: Vacancy,
        favoriteVacancyClickListener: FavoritesAdapter.FavoriteVacancyClickListener
    ) {

        if (vacancy.employer.logoUrls != null)
            Glide.with(itemView.context)
                .load(
                    when {
                        vacancy.employer.logoUrls.twoHundredAndForty != null -> vacancy.employer.logoUrls.twoHundredAndForty
                        else -> vacancy.employer.logoUrls.original
                    }
                )
                .placeholder(R.drawable.offer_placeholder)
                .error(R.drawable.offer_placeholder)
                .into(jobIconImageView)

        jobNameTextView.text = if (vacancy.address != null) {
            vacancy.name + ", " + vacancy.address.city
        } else {
            vacancy.name
        }
        jobEmployerTextview.text = vacancy.employer.name
        jobSalaryTextView.text = vacancy.salary?.pretty()
            ?: itemView.context.getString(R.string.no_salary)

        itemView.setOnClickListener {
            favoriteVacancyClickListener.onVacancyClick(vacancy)
        }
    }
}