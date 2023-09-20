package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employer_table")
data class EmployerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val requestId: String,
    val logoUrls: Int,
    val name: String,
    val url: String
)
