package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "area_table")
data class AreaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val requestId: String,
    val name: String,
    val url: String
)
