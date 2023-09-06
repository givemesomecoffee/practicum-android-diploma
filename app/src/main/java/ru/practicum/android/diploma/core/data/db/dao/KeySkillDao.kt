package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.KeySkillEntity

@Dao
interface KeySkillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg keySkillEntity: KeySkillEntity)

    @Query("SELECT * FROM key_skill_table")
    suspend fun select(): List<KeySkillEntity>

    @Query("SELECT * FROM key_skill_table WHERE id = :id")
    suspend fun select(id: Int): List<KeySkillEntity>

    @Query("SELECT id FROM key_skill_table WHERE name = :name")
    suspend fun select(name: String): List<Int>

    @Query("DELETE FROM key_skill_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}