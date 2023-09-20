package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.ScheduleEntity

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg scheduleEntity: ScheduleEntity)

    @Query("SELECT * FROM schedule_table")
    suspend fun select(): List<ScheduleEntity>

    @Query("SELECT * FROM schedule_table WHERE id = :id")
    suspend fun select(id: Int): List<ScheduleEntity>

    @Query("SELECT id FROM schedule_table WHERE requestId = :requestId AND name = :name")
    suspend fun select(requestId: String, name: String): List<Int>

    @Query("DELETE FROM schedule_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}