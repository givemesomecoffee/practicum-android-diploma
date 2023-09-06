package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.core.data.models.LogoUrls

@Dao
interface EmployerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg employerEntity: EmployerEntity)

    @Query("SELECT * FROM employer_table")
    suspend fun select(): List<EmployerEntity>

    @Query("SELECT * FROM employer_table WHERE id = :id")
    suspend fun select(id: Int): List<EmployerEntity>

    @Query("SELECT id FROM employer_table WHERE requestId = :requestId AND logoUrls = :logoUrls AND name = :name AND url = :url")
    suspend fun select(requestId: String, logoUrls: Int, name: String, url: String): List<Int>

    @Query("DELETE FROM employer_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}