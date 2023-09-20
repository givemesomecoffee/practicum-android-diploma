package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.LogoUrlsEntity

@Dao
interface LogoUrlsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg logoUrlsEntity: LogoUrlsEntity)

    @Query("SELECT * FROM logo_urls_table")
    suspend fun select(): List<LogoUrlsEntity>

    @Query("SELECT * FROM logo_urls_table WHERE id = :id")
    suspend fun select(id: Int): List<LogoUrlsEntity>

    @Query("SELECT id FROM logo_urls_table WHERE twoHundredAndForty = :twoHundredAndForty AND ninety = :ninety AND original = :original")
    suspend fun select(twoHundredAndForty: String, ninety: String, original: String): List<Int>

    @Query("DELETE FROM logo_urls_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}