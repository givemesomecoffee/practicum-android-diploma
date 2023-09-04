package ru.practicum.android.diploma.features.search.domain.models

import ru.practicum.android.diploma.features.filter.domain.model.Filter

data class APIQuery(val text: String, val filter: Filter?, val limit: Int = 50){
    fun toMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map["text"] = text
        map["per_page"] = limit.toString()
        if (filter!=null){
            map["only_with_salary"] = (!filter.showNoSalaryItems).toString()
            if(filter.salary != null) map["salary"] = filter.salary.toString()
            if (filter.industry != null) map["industry"] = filter.industry.id
            if (filter.location?.city != null) map["area"] = filter.location.city.id
        }
        return map
    }
}
