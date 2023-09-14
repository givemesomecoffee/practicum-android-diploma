package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.SalaryEntity

@Dao
interface SalaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg salaryEntity: SalaryEntity)

    @Query("SELECT * FROM salary_table")
    suspend fun select(): List<SalaryEntity>

    @Query("SELECT * FROM salary_table WHERE id = :id")
    suspend fun select(id: Int): List<SalaryEntity>

    @Query("SELECT id FROM salary_table WHERE `from` = :from AND `to` = :to AND currency = :currency AND gross = :gross")
    suspend fun select(currency: String, from: Int, gross: Int, to: Int): List<Int>

    @Query("DELETE FROM salary_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}