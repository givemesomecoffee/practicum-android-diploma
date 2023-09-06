package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.AreaEntity

@Dao
interface AreaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg areaEntity: AreaEntity)

    @Query("SELECT * FROM area_table")
    suspend fun select(): List<AreaEntity>

    @Query("SELECT * FROM area_table WHERE id = :id")
    suspend fun select(id: Int): List<AreaEntity>

    @Query("SELECT id FROM area_table WHERE requestId = :requestId AND name = :name AND url = :url")
    suspend fun select(requestId: String, name: String, url: String): List<Int>

    @Query("DELETE FROM area_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}