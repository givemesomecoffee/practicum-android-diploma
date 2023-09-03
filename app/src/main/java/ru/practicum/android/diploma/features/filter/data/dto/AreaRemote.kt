package ru.practicum.android.diploma.features.filter.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.features.filter.domain.model.Area

@Serializable
data class AreaRemote(
    val id: String,
    val name: String,
    @SerialName("parent_id")
    val parentId: String?,
    val areas: List<AreaRemote>?
) {
    fun toDomain(): Area {
        return Area(
            id = id,
            name = name,
            parentId = parentId
        )
    }
}