package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "professional_role_table")
data class ProfessionalRoleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val requestId: String,
    val name: String
)
