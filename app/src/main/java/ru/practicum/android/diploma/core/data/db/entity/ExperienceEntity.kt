package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experience_table")
data class ExperienceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val requestId: String,
    val name: String
)
