package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.ExperienceEntity

@Dao
interface ExperienceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg experienceEntity: ExperienceEntity)

    @Query("SELECT * FROM experience_table")
    suspend fun select(): List<ExperienceEntity>

    @Query("SELECT * FROM experience_table WHERE id = :id")
    suspend fun select(id: Int): List<ExperienceEntity>

    @Query("SELECT id FROM experience_table WHERE requestId = :requestId AND name = :name")
    suspend fun select(requestId: String, name: String): List<Int>

    @Query("DELETE FROM experience_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}