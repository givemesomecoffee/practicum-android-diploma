package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.EmploymentEntity

@Dao
interface EmploymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg employmentEntity: EmploymentEntity)

    @Query("SELECT * FROM employment_table WHERE id = :id")
    suspend fun select(id: Int): List<EmploymentEntity>

    @Query("SELECT * FROM employment_table")
    suspend fun select(): List<EmploymentEntity>

    @Query("SELECT id FROM employment_table WHERE requestId = :requestId AND name = :name")
    suspend fun select(requestId: String, name: String): List<Int>

    @Query("DELETE FROM employment_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}