package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logo_urls_table")
data class LogoUrlsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val twoHundredAndForty: String,
    val ninety: String,
    val original: String
)
