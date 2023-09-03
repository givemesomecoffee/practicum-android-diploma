package ru.practicum.android.diploma.features.filter.data.dto

import ru.practicum.android.diploma.features.filter.domain.model.Area
import ru.practicum.android.diploma.features.filter.domain.model.Filter

fun Area.toLocal(): AreaLocal {
    return AreaLocal(
        name = name,
        id = id,
        parentId = parentId
    )
}

fun AreaLocal.toDomain(): Area {
    return Area(id = id, name = name, parentId = parentId)
}

fun Filter.toLocal(): FilterLocal {
    return FilterLocal(
        showNoSalaryItems,
        salary,
        industry,
        FilterLocal.WorkLocationLocal(location?.country?.toLocal(), location?.city?.toLocal())
    )
}

fun FilterLocal.toDomain(): Filter {
    return Filter(
        showNoSalaryItems,
        salary,
        industry,
        Filter.WorkLocation(location?.country?.toDomain(), location?.city?.toDomain())
    )
}