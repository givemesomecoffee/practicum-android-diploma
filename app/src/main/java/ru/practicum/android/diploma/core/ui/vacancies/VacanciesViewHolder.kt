package ru.practicum.android.diploma.core.ui.vacancies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy

class VacanciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val icon: ImageView = itemView.findViewById(R.id.jobIcon)
    private val employer: TextView = itemView.findViewById(R.id.jobEmployer)
    private val name: TextView = itemView.findViewById(R.id.jobName)
    private val salary: TextView = itemView.findViewById(R.id.jobSalary)


    fun bind(vacancy: Vacancy) {
        name.text = vacancy.name
        employer.text = vacancy.employer.name
        salary.text = vacancy.salary?.pretty(itemView.context) ?: itemView.context.getString(R.string.no_salary)
        if (vacancy.employer.logoUrls != null)Glide.with(itemView.context)
            .load(
                when{
                    vacancy.employer.logoUrls.twoHundredAndForty != null -> vacancy.employer.logoUrls.twoHundredAndForty
                    else -> vacancy.employer.logoUrls.original
                }
            )
            .placeholder(R.drawable.offer_placeholder)
            .error(R.drawable.offer_placeholder)
            .into(icon)
    }
}