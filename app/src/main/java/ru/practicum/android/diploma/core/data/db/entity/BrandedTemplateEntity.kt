package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "branded_template_table")
data class BrandedTemplateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val requestId: String,
    val name: String
)
