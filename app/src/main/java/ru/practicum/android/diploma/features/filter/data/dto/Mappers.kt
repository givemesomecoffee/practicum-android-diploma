package ru.practicum.android.diploma.features.filter.data.dto

import ru.practicum.android.diploma.features.filter.domain.model.Area
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.filter.domain.model.Industry

fun Area.toLocal(): AreaLocal {
    return AreaLocal(
        name = name,
        id = id,
        parentId = parentId
    )
}

fun AreaLocal.toDomain(): Area {
    return Area(id = id, name = name, parentId = parentId, null)
}

fun Filter.toLocal(): FilterLocal {
    return FilterLocal(
        showNoSalaryItems,
        salary,
        industry?.toLocal(),
        FilterLocal.WorkLocationLocal(location?.country?.toLocal(), location?.city?.toLocal())
    )
}

fun FilterLocal.toDomain(): Filter {
    return Filter(
        showNoSalaryItems,
        salary,
        industry?.toDomain(),
        Filter.WorkLocation(location?.country?.toDomain(), location?.city?.toDomain())
    )
}

fun IndustryLocal.toDomain(): Industry {
    return Industry(name, id, null)
}

fun IndustryRemote.toDomain(): Industry = Industry(name, id, industries?.map { it.toDomain() })

fun Industry.toLocal(): IndustryLocal = IndustryLocal(name, id)