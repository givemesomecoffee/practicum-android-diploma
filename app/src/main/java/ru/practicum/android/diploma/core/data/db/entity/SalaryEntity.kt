package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salary_table")
data class SalaryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currency: String,
    val from: Int,
    val gross: Int,
    val to: Int
)
