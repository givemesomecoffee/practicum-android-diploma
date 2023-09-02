package ru.practicum.android.diploma.features.filter.ui.model

import ru.practicum.android.diploma.features.filter.domain.model.Area

fun Area.toAreaResult(): AreaResult {
    return AreaResult(
        id = id,
        name = name
    )
}

fun AreaResult.toDomain(): Area{
    return Area(
        id = id,
        name = name,
        null,
        null
    )
}