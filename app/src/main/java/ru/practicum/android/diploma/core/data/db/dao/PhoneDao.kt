package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.PhoneEntity

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg phoneEntity: PhoneEntity)

    @Query("SELECT * FROM phone_table")
    suspend fun select(): List<PhoneEntity>

    @Query("SELECT * FROM phone_table WHERE id = :id")
    suspend fun select(id: Int): List<PhoneEntity>

    @Query("SELECT id FROM phone_table WHERE city = :city AND comment = :comment AND country = :country AND number = :number")
    suspend fun select(city: String, comment: String, country: String, number: String): List<Int>

    @Query("DELETE FROM phone_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}