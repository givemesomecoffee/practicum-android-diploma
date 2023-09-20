package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_table")
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val comment: String,
    val country: String,
    val number: String
)
