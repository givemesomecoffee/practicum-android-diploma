package ru.practicum.android.diploma.features.filter.ui.model

import ru.practicum.android.diploma.features.filter.domain.model.Area
import ru.practicum.android.diploma.features.filter.domain.model.Industry

fun Area.toAreaResult(): AreaResult {
    return AreaResult(
        id = id,
        name = name
    )
}

fun AreaResult.toDomain(): Area {
    return Area(
        id = id,
        name = name,
        null,
        null
    )
}

fun Industry.toIndustryResult(): IndustryResult {
    return IndustryResult(
        id = id,
        name = name
    )
}

fun IndustryResult.toDomain(): Industry {
    return Industry(
        id = id,
        name = name,
        industries = null
    )
}