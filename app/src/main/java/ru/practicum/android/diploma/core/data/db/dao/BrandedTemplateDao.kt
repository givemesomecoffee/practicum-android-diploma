package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.BrandedTemplateEntity

@Dao
interface BrandedTemplateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg brandedTemplateEntity: BrandedTemplateEntity)

    @Query("SELECT * FROM branded_template_table")
    suspend fun select(): List<BrandedTemplateEntity>

    @Query("SELECT * FROM branded_template_table WHERE id = :id")
    suspend fun select(id: Int): List<BrandedTemplateEntity>

    @Query("SELECT id FROM branded_template_table WHERE requestId = :requestId AND name = :name")
    suspend fun select(requestId: String, name: String): List<Int>

    @Query("DELETE FROM branded_template_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}