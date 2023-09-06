package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key_skill_table")
data class KeySkillEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
